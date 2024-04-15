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
import java.util.Locale;
import java.util.ResourceBundle;

import static com.davidbonelo.utils.UserInteractions.closeScanner;
import static com.davidbonelo.utils.Permissions.validMenuAccess;

public class MainMenu {
    private final UserService userService;
    private final LibraryManager libraryManager;
    private final BorrowingsService borrowingsService;
    private User user;
    private ResourceBundle messages = ResourceBundle.getBundle("messages");

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
        System.out.println(messages.getString("welcome"));
        while (true) {
            messages = ResourceBundle.getBundle("messages"); // Refresh if language changed
            user = userService.getLoggedUser();

            int menuChoice = showMenu();
            switch (menuChoice) {
                case 1 -> new LoginMenu(userService).menu();
                case 2 -> new BooksMenu(libraryManager, borrowingsService, user).menu();
                case 3 -> new NovelsMenu(libraryManager, borrowingsService, user).menu();
                case 4 -> new BorrowingMenu(borrowingsService, user).menu();
                case 5 -> new AdminMenu(userService, user).menu();
                case 8 -> Locale.setDefault(Locale.forLanguageTag("es"));
                case 9 -> logout(user);
                case 0 -> {
                    closeScanner();
                    return;
                }
                default -> System.out.println(messages.getString("unknownOption"));
            }
        }
    }

    private int showMenu() {
        String visitorChoices = messages.getString("main.choices.visitor");
        String readerChoices = messages.getString("main.choices.reader");
        String adminChoices = messages.getString("main.choices.admin");
        return new MenuChoices("Main", visitorChoices, readerChoices, "", adminChoices).showMenu(user);
    }

    private void logout(User user) {
        if (validMenuAccess(user, UserRole.READER)) {
            userService.logOut();
        }
    }
}
