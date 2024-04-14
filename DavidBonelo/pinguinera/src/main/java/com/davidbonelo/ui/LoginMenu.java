package com.davidbonelo.ui;

import com.davidbonelo.models.User;
import com.davidbonelo.models.UserRole;
import com.davidbonelo.services.UserService;

import java.sql.SQLException;

import static com.davidbonelo.utils.UserInteractions.askNumber;
import static com.davidbonelo.utils.UserInteractions.askText;

public class LoginMenu {
    private final UserService userService;

    public LoginMenu(UserService userService) {
        this.userService = userService;
    }

    public void menu() {
        while (userService.getLoggedUser() == null) {
            int menuChoice = askNumber("Login menu: 1. Login with email | 2. Register | 0. Back");
            switch (menuChoice) {
                case 1 -> login();
                case 2 -> register();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Unknown menu option");
            }
        }
    }

    private void login() {
        String email = askText("email: ");
        String password = askText("password: ");

        User user = userService.login(email, password);
        if (user == null) {
            System.out.println("Couldn't login, invalid email or password");
        } else {
            System.out.println("Successful login as: " + user);
        }
    }

    private void register() {
        String name = askText("name: ");
        String email = askText("email: ");
        String password = askText("password: ");
        User user = new User(name, email, UserRole.READER);

        try {
            userService.register(user, password);
        } catch (SQLException e) {
            System.out.println("Couldn't register user, " + e.getLocalizedMessage());
        }
    }
}
