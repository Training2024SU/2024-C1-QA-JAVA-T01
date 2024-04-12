package com.davidbonelo.ui;

import com.davidbonelo.models.Book;
import com.davidbonelo.models.User;
import com.davidbonelo.models.UserRole;
import com.davidbonelo.services.LibraryManager;

import java.sql.SQLException;

import static com.davidbonelo.Utils.askNumber;
import static com.davidbonelo.Utils.askText;
import static com.davidbonelo.Utils.validMenuAccess;
import static com.davidbonelo.Utils.validPermission;

public class BooksMenu {
    private final LibraryManager libraryManager;
    private final User user;

    public BooksMenu(LibraryManager libraryManager, User user) {
        this.libraryManager = libraryManager;
        this.user = user;
    }

    public void menu() {
        while (true) {
            String menuMessage = buildMenuMessage(user);

            int menuChoice = askNumber(menuMessage);
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

    private void searchByAuthor() {
        String author = askText("Type the name of the author you want to search: ");
        libraryManager.filterItemsByAuthor(libraryManager.getAllBooks(), author).forEach(System.out::println);
    }

    private void listBooks() {
        libraryManager.getAllBooks().forEach(System.out::println);
    }

    private void listAuthors() {
        libraryManager.getAuthorsList(libraryManager.getAllBooks()).forEach(System.out::println);
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
            libraryManager.addBorrowingBook(bookId);
            System.out.println("Book added, go to the Borrowings menu to complete the request");
        } catch (SQLException e) {
            System.out.println("Can't borrow this book. " + e.getLocalizedMessage());
        }
    }

    private String buildMenuMessage(User user) {
        final StringBuilder menuMessage = new StringBuilder("Books menu:");
        menuMessage.append(" 1. List Books | 2. List authors | 3. Search by author |");

        if (user != null) {
            menuMessage.append(" 4. Borrow a Book |");
            if (validPermission(user, UserRole.EMPLOYEE)) {
                menuMessage.append(" 5. Register Book | 6. Update Book | 7. Delete Book |");
            }
        }
        menuMessage.append(" 0. Back");
        return menuMessage.toString();
    }
}
