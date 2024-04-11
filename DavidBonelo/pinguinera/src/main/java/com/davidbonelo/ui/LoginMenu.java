package com.davidbonelo.ui;

import com.davidbonelo.models.User;
import com.davidbonelo.services.UserService;

import static com.davidbonelo.Utils.askNumber;
import static com.davidbonelo.Utils.askText;

public class LoginMenu {
    private final UserService userService;

    public LoginMenu(UserService userService) {
        this.userService = userService;
    }

    public void menu() {
        while (userService.getLoggedUser() == null) {
            int menuChoice = askNumber("Menu: 1. Login | 2. Register");
            switch (menuChoice) {
                case 1 -> login();
//                case 2 -> register();
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
}
