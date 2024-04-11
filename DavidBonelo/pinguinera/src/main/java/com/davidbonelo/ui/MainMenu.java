package com.davidbonelo.ui;

import com.davidbonelo.models.User;
import com.davidbonelo.models.UserRole;
import com.davidbonelo.services.UserService;

import static com.davidbonelo.Utils.askNumber;
import static com.davidbonelo.Utils.closeScanner;

public class MainMenu {
    private final UserService userService;

    public MainMenu(UserService userService) {
        this.userService = userService;
    }

    public void menu() {
        System.out.println("Welcome to La Pingüinera library!!");
        while (true) {
            String menuMessage = buildMenuMessage(userService.getLoggedUser());
            int menuChoice = askNumber(menuMessage);
            switch (menuChoice) {
                case 1 -> new LoginMenu(userService).menu();
//                case 3 -> novelsMenu();
                case 9 -> logout();
                case 0 -> {
                    closeScanner();
                    return;
                }
                default -> System.out.println("Unknown menu option");
            }
        }
    }

    private void logout() {
        if (userService.getLoggedUser() == null) {
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
            UserRole role = userService.getLoggedUser().getRole();
//            menuMessage.append("Reader options");
            if (role == UserRole.EMPLOYEE) {
//                menuMessage.append("Employee options");
            }
            if (role == UserRole.ADMINISTRATOR) {
//                menuMessage.append(" 3. Admin options");
            }
            menuMessage.append(" 9. Log out |");
        }
        menuMessage.append(" 0. Exit");
        return menuMessage.toString();
    }
}
