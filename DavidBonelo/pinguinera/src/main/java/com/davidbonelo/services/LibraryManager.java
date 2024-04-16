package com.davidbonelo.services;

import com.davidbonelo.models.Book;
import com.davidbonelo.models.LibraryItem;
import com.davidbonelo.models.Novel;
import com.davidbonelo.models.User;
import com.davidbonelo.models.UserRole;
import com.davidbonelo.persistance.BookDAO;
import com.davidbonelo.persistance.NovelDAO;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.davidbonelo.utils.Permissions.validPermission;

public class LibraryManager {

    private final BookDAO bookDAO;
    private final NovelDAO novelDAO;

    public LibraryManager(BookDAO bookDAO, NovelDAO novelDAO) {
        this.bookDAO = bookDAO;
        this.novelDAO = novelDAO;
    }

    public List<Book> getAllBooks(User user) {
        try {
            List<Book> books = bookDAO.getAllBooks();
            if (validPermission(user, UserRole.EMPLOYEE)) {
                return books;
            } else {
                // Filter unavailable items for users
                return books.stream().filter(b -> b.getAvailableCopies() >= 1).toList();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<Novel> getAllNovels(User user) {
        try {
            List<Novel> novels = novelDAO.getAllNovels();
            if (validPermission(user, UserRole.EMPLOYEE)) {
                return novels;
            } else {
                return novels.stream().filter(n -> n.getAvailableCopies() >= 1).toList();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
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
            if (item instanceof Book book) {
                bookDAO.createBook(book);
            } else if (item instanceof Novel novel) {
                novelDAO.createNovel(novel);
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
}
