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

    public void registerBook(Book book) {
        try {
            bookDAO.createBook(book);
        } catch (SQLException e) {
            System.out.println("Couldn't register book, " + e.getLocalizedMessage());
        }
    }

    public void updateBook(Book book) {
        try {
            bookDAO.updateBook(book);
        } catch (SQLException e) {
            System.out.println("Couldn't update book, " + e.getLocalizedMessage());
        }
    }

    public void deleteBook(int bookId) {
        try {
            bookDAO.deleteBook(bookId);
        } catch (SQLException e) {
            System.out.println("Couldn't delete book, " + e.getLocalizedMessage());
        }
    }
}
