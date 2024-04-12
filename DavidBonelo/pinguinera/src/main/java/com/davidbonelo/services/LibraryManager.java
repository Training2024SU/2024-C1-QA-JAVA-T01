package com.davidbonelo.services;

import com.davidbonelo.models.Book;
import com.davidbonelo.models.LibraryItem;
import com.davidbonelo.models.Novel;
import com.davidbonelo.persistance.BookDAO;
import com.davidbonelo.persistance.BorrowingDAO;
import com.davidbonelo.persistance.NovelDAO;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LibraryManager {

    private final BookDAO bookDAO;
    private final NovelDAO novelDAO;
    private final BorrowingDAO borrowingDAO;
    private final Set<LibraryItem> borrowingItems;

    public LibraryManager(BookDAO bookDAO, NovelDAO novelDAO, BorrowingDAO borrowingDAO) {
        this.bookDAO = bookDAO;
        this.novelDAO = novelDAO;
        this.borrowingDAO = borrowingDAO;
        this.borrowingItems = new HashSet<>();
    }

    public List<Book> getAllBooks() {
        try {
            return bookDAO.getAllBooks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Novel> getAllNovels() {
        try {
            return novelDAO.getAllNovels();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Set<String> getAuthorsList(List<? extends LibraryItem> items) {
        return items.stream().map(LibraryItem::getAuthor).collect(Collectors.toSet());
    }

    public List<? extends LibraryItem> filterItemsByAuthor(List<? extends LibraryItem> items,
                                                           String author) {
        return items.stream().filter(item -> item.getAuthor().equalsIgnoreCase(author)).toList();
    }

    public void registerItem(LibraryItem item) {
        try {
            if (item.getClass().equals(Book.class)) {
                bookDAO.createBook((Book) item);
            } else if (item.getClass().equals(Novel.class)) {
                novelDAO.createNovel((Novel) item);
            }
            System.out.println("Successful item registration " + item);
        } catch (SQLException e) {
            System.out.println("Couldn't register item, " + e.getLocalizedMessage());
        }
    }

    public void updateItem(LibraryItem item) {
        try {
            if (item.getClass().equals(Book.class)) {
                bookDAO.updateBook((Book) item);
            } else if (item.getClass().equals(Novel.class)) {
                novelDAO.updateNovel((Novel) item);
            }
            System.out.println("Successful item update " + item);
        } catch (SQLException e) {
            System.out.println("Couldn't update item, " + e.getLocalizedMessage());
        }
    }

    public void deleteBook(int bookId) {
        try {
            bookDAO.deleteBook(bookId);
        } catch (SQLException e) {
            System.out.println("Couldn't delete book, " + e.getLocalizedMessage());
        }
    }

    public void deleteNovel(int novelId) {
        try {
            novelDAO.deleteNovel(novelId);
        } catch (SQLException e) {
            System.out.println("Couldn't delete novel, " + e.getLocalizedMessage());
        }
    }

    public void addBorrowingBook(int itemId) throws SQLException {
        Book book = bookDAO.getBookById(itemId);
        if (book == null) {
            throw new SQLException("Book with id " + itemId + " not found");
        }
        this.borrowingItems.add(book);
        borrowingItems.forEach(System.out::println);
    }

    public void addBorrowingNovel(int itemId) throws SQLException {
        Novel novel = novelDAO.getNovelById(itemId);
        if (novel == null) {
            throw new SQLException("Novel with id " + itemId + " not found");
        }
        this.borrowingItems.add(novel);
        borrowingItems.forEach(System.out::println);
    }
}
