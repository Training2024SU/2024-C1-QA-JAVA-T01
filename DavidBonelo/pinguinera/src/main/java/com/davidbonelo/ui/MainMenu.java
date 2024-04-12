package com.davidbonelo.ui;

import com.davidbonelo.models.User;
import com.davidbonelo.models.UserRole;
import com.davidbonelo.persistance.BookDAO;
import com.davidbonelo.persistance.BorrowingDAO;
import com.davidbonelo.persistance.NovelDAO;
import com.davidbonelo.persistance.UserDAO;
import com.davidbonelo.services.BorrowingsService;
import com.davidbonelo.services.LibraryManager;
import com.davidbonelo.services.UserService;

import java.sql.Connection;

import static com.davidbonelo.Utils.askNumber;
import static com.davidbonelo.Utils.closeScanner;
import static com.davidbonelo.Utils.validPermission;

public class MainMenu {
    private final UserService userService;
    private final LibraryManager libraryManager;
    private final BorrowingsService borrowingsService;

    public MainMenu(Connection connection) {
        UserDAO userDAO = new UserDAO(connection);
        BookDAO bookDAO = new BookDAO(connection);
        NovelDAO novelDAO = new NovelDAO(connection);
        BorrowingDAO borrowingDAO = new BorrowingDAO(connection);
        this.userService = new UserService(userDAO);
        this.libraryManager = new LibraryManager(bookDAO, novelDAO);
        this.borrowingsService = new BorrowingsService(bookDAO, novelDAO, borrowingDAO, connection);
    }

    public void menu() {
        System.out.println("Welcome to La Pingüinera library!!");
        while (true) {
            User user = userService.getLoggedUser();
            String menuMessage = buildMenuMessage(user);

            int menuChoice = askNumber(menuMessage);
            switch (menuChoice) {
                case 1 -> new LoginMenu(userService).menu();
                case 2 -> new BooksMenu(libraryManager, borrowingsService, user).menu();
                case 3 -> new NovelsMenu(libraryManager, borrowingsService, user).menu();
                case 4 -> new BorrowingMenu(borrowingsService, user).menu();
                case 5 -> new AdminMenu(userService, user).menu();
                case 9 -> logout(user);
                case 0 -> {
                    closeScanner();
                    return;
                }
                default -> System.out.println("Unknown menu option");
            }
        }
    }

    private void logout(User user) {
        if (user == null) {
            System.out.println("Unknown menu option");
        } else {
            userService.logOut();
        }
    }

    private String buildMenuMessage(User user) {
        final StringBuilder menuMessage = new StringBuilder("Menu:");
        menuMessage.append(" 2. Books | 3. Novels |");

        if (user == null) {
            menuMessage.insert(5, " 1. Login |");
        } else {
            // Logged in user
            menuMessage.append(" 4. Borrowings |");
            if (validPermission(user, UserRole.ADMINISTRATOR)) {
                menuMessage.append(" 5. Admin menu |");
            }
            menuMessage.append(" 9. Log out |");
        }
        menuMessage.append(" 0. Exit");
        return menuMessage.toString();
    }
}
