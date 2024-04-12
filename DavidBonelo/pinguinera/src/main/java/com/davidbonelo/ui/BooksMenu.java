package com.davidbonelo.ui;

import com.davidbonelo.models.Book;
import com.davidbonelo.models.User;
import com.davidbonelo.models.UserRole;
import com.davidbonelo.services.LibraryManager;

import static com.davidbonelo.Utils.askNumber;
import static com.davidbonelo.Utils.askText;
import static com.davidbonelo.Utils.validMenuAccess;

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

    private String buildMenuMessage(User user) {
        final StringBuilder menuMessage = new StringBuilder("Books menu:");
        menuMessage.append(" 1. List Books | 2. List authors | 3. Search by author |");

        if (user != null) {
            UserRole role = user.getRole();
            if (role == UserRole.EMPLOYEE) {
                menuMessage.append(" 5. Register Book | 6. Update Book | 7. Delete Book |");
            }
        }
        menuMessage.append(" 0. Back");
        return menuMessage.toString();
    }
}
