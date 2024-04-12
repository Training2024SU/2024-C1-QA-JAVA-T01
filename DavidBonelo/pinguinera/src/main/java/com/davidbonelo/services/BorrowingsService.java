package com.davidbonelo.services;

import com.davidbonelo.models.Book;
import com.davidbonelo.models.Borrowing;
import com.davidbonelo.models.LibraryItem;
import com.davidbonelo.models.Novel;
import com.davidbonelo.models.User;
import com.davidbonelo.models.UserRole;
import com.davidbonelo.persistance.BookDAO;
import com.davidbonelo.persistance.BorrowingDAO;
import com.davidbonelo.persistance.NovelDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.davidbonelo.Utils.validPermission;

public class BorrowingsService {
    private final BookDAO bookDAO;
    private final NovelDAO novelDAO;
    private final BorrowingDAO borrowingDAO;
    private final Set<LibraryItem> itemsToBorrow;
    private final Connection connection;

    public BorrowingsService(BookDAO bookDAO, NovelDAO novelDAO, BorrowingDAO borrowingDAO,
                             Connection connection) {
        this.bookDAO = bookDAO;
        this.novelDAO = novelDAO;
        this.borrowingDAO = borrowingDAO;
        this.connection = connection;
        this.itemsToBorrow = new HashSet<>();
    }

    public Set<LibraryItem> getItemsToBorrow() {
        return this.itemsToBorrow;
    }

    public List<Borrowing> getBorrowingsByEmail(User employee, String email) {
        return getAllBorrowings(employee).stream().filter(b -> b.getBorrower().getEmail().equals(email)).toList();
    }

    public Borrowing getBorrowingDetails(User user, int borrowingId) throws SQLException {
        Borrowing borrowing = borrowingDAO.getBorrowingWithItems(borrowingId);
        if (validPermission(user, UserRole.EMPLOYEE)) {
            return borrowing;
        } else if (user.getId() == borrowing.getBorrower().getId()) { // Is the owner
            return borrowing;
        }
        return null;
    }

    public void addBorrowingBook(int itemId) throws SQLException {
        Book book = bookDAO.getBookById(itemId);
        if (book == null) {
            throw new SQLException("Book with id " + itemId + " not found");
        }
        this.itemsToBorrow.add(book);
        itemsToBorrow.forEach(System.out::println);
    }

    public void addBorrowingNovel(int itemId) throws SQLException {
        Novel novel = novelDAO.getNovelById(itemId);
        if (novel == null) {
            throw new SQLException("Novel with id " + itemId + " not found");
        }
        this.itemsToBorrow.add(novel);
        itemsToBorrow.forEach(System.out::println);
    }

    public List<Borrowing> getAllBorrowings(User user) {
        try {
            List<Borrowing> borrowings = borrowingDAO.getAllBorrowings();
            // Show all borrowings only if authorized
            if (validPermission(user, UserRole.EMPLOYEE)) {
                return borrowings;
            } else {
                // Filter if user isn't owner
                return borrowings.stream().filter(b -> b.getId() == user.getId()).toList();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createBorrowing(Borrowing borrowing) throws SQLException {
        try {
            connection.setAutoCommit(false); // start transaction
            verifyItemsAvailability(itemsToBorrow);
            borrowing.setBorrowedItems(itemsToBorrow.stream().toList());

            borrowingDAO.createBorrowing(borrowing);
            updateItemsCopies(borrowing.getBorrowedItems(), 1);

            connection.commit(); // commit transaction
            itemsToBorrow.clear(); // Clean this class set

        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private void verifyItemsAvailability(Set<LibraryItem> borrowingItems) {
        for (LibraryItem item : borrowingItems) {
            if (item.getAvailableCopies() < 1) {
                throw new IllegalArgumentException("Item: " + item.getTitle() + " has no " +
                        "copies available");
            }
        }
    }

    /**
     * @param factor the amount of copies to sum (or rest if negative)
     */
    private void updateItemsCopies(List<LibraryItem> items, int factor) throws SQLException {
        for (LibraryItem li : items) {
            li.setCopiesBorrowed(li.getCopiesBorrowed() + factor);
            if (li instanceof Book) {
                bookDAO.updateBook((Book) li);
            } else if (li instanceof Novel) {
                novelDAO.updateNovel((Novel) li);
            }
        }
    }

    public void confirmBorrowing(User user, int borrowingId) throws SQLException {
        Borrowing borrowing = getBorrowingDetails(user, borrowingId);
        borrowing.setStatusBorrowed();
        borrowingDAO.updateBorrowingStatus(borrowing);
    }

    public void deleteBorrowing(int borrowingId) {
        try {
            borrowingDAO.deleteBorrowing(borrowingId);
            System.out.println("Borrowing deleted successfully");
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
