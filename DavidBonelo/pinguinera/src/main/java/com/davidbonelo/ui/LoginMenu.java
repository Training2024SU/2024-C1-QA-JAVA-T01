package com.davidbonelo.ui;

import com.davidbonelo.models.User;
import com.davidbonelo.services.UserService;

import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.davidbonelo.utils.UserInteractions.askNumber;
import static com.davidbonelo.utils.UserInteractions.askText;

public class LoginMenu {
    private final UserService userService;
    private final ResourceBundle messages = ResourceBundle.getBundle("messages");

    public LoginMenu(UserService userService) {
        this.userService = userService;
    }

    public void menu() {
        while (userService.getLoggedUser() == null) {
            int menuChoice = askNumber(messages.getString("login.choices.visitor"));
            switch (menuChoice) {
                case 1 -> login();
                case 2 -> register();
                case 0 -> {
                    return;
                }
                default -> System.out.println(messages.getString("unknownOption"));
            }
        }
    }

    private void login() {
        String email = askText(messages.getString("user.req.email"));
        String password = askText(messages.getString("login.req.password"));

        User user = userService.login(email, password);
        if (user == null) {
            System.out.println(messages.getString("login.res.loginBad"));
        } else {
            System.out.println(messages.getString("login.res.loginOk") + user);
        }
    }

    private void register() {
        User user = User.createUserFromInput();
        String password = askText(messages.getString("login.req.password"));

        try {
            userService.register(user, password);
        } catch (SQLException e) {
            System.out.println(messages.getString("login.res.registerBad") + e.getLocalizedMessage());
        }
    }
}
