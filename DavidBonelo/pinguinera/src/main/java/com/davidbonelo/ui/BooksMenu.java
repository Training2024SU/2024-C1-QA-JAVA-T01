package com.davidbonelo.ui;

import com.davidbonelo.models.Book;
import com.davidbonelo.models.User;
import com.davidbonelo.models.UserRole;
import com.davidbonelo.services.BorrowingsService;
import com.davidbonelo.services.LibraryManager;

import java.sql.SQLException;

import static com.davidbonelo.utils.Permissions.validMenuAccess;
import static com.davidbonelo.utils.UserInteractions.askNumber;
import static com.davidbonelo.utils.UserInteractions.askText;

public class BooksMenu {
    private final LibraryManager libraryManager;
    private final BorrowingsService borrowingsService;
    private final User user;

    public BooksMenu(LibraryManager libraryManager, BorrowingsService borrowingsService,
                     User user) {
        this.libraryManager = libraryManager;
        this.borrowingsService = borrowingsService;
        this.user = user;
    }

    public void menu() {
        while (true) {
            int menuChoice = showMenu();
            switch (menuChoice) {
                case 1 -> listBooks();
                case 2 -> listAuthors();
                case 3 -> searchByAuthor();
                case 4 -> addToBorrowing();
                case 5 -> registerBook();
                case 6 -> updateBook();
                case 7 -> deleteBook();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Unknown menu option");
            }
        }
    }

    private int showMenu() {
        String visitorChoices = " 1. List Books | 2. List authors | 3. Search by author |";
        String readerChoices = "4. Borrow a Book |";
        String employeeChoices = "\n5. Register Book | 6. Update Book | 7. Delete Book |";
        MenuChoices menu = new MenuChoices("Books", visitorChoices, readerChoices,
                employeeChoices, "");
        return menu.showMenu(user);
    }

    private void searchByAuthor() {
        String author = askText("Type the name of the author you want to search: ");
        libraryManager.filterItemsByAuthor(libraryManager.getAllBooks(user), author).forEach(System.out::println);
    }

    private void listBooks() {
        libraryManager.getAllBooks(user).forEach(System.out::println);
    }

    private void listAuthors() {
        libraryManager.getAuthorsList(libraryManager.getAllBooks(user)).forEach(System.out::println);
    }

    private void registerBook() {
        if (!validMenuAccess(user, UserRole.EMPLOYEE)) {
            return;
        }
        libraryManager.registerItem(Book.createBookFromInput());
    }

    private void updateBook() {
        if (!validMenuAccess(user, UserRole.EMPLOYEE)) {
            return;
        }
        int bookId = askNumber("Type the id of the book you want to update: ");
        System.out.println("Type the new data for the book: ");
        Book book = Book.createBookFromInput();
        book.setId(bookId);
        libraryManager.updateItem(book);
    }

    private void deleteBook() {
        if (!validMenuAccess(user, UserRole.EMPLOYEE)) {
            return;
        }
        int bookId = askNumber("Type the id of the book you want to delete: ");
        libraryManager.deleteBook(bookId);
    }

    private void addToBorrowing() {
        if (!validMenuAccess(user, UserRole.READER)) {
            return;
        }
        int bookId = askNumber("Type the id of the book you want to borrow: ");
        try {
            borrowingsService.addBorrowingBook(bookId);
            System.out.println("Book added, go to the Borrowings menu to complete the request");
        } catch (SQLException e) {
            System.out.println("Can't borrow this book. " + e.getLocalizedMessage());
        }
    }
}
