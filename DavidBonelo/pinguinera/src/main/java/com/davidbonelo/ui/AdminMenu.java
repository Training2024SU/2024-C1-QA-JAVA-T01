package com.davidbonelo.ui;

import com.davidbonelo.models.User;
import com.davidbonelo.models.UserRole;
import com.davidbonelo.services.UserService;

import java.sql.SQLException;

import static com.davidbonelo.Utils.askNumber;
import static com.davidbonelo.Utils.askText;
import static com.davidbonelo.Utils.validMenuAccess;
import static com.davidbonelo.Utils.validPermission;

public class AdminMenu {
    private final UserService userService;
    private final User user;

    public AdminMenu(UserService userService, User user) {
        this.userService = userService;
        this.user = user;
    }

    public void menu() {
        while (true) {
            if (!validMenuAccess(user, UserRole.ADMINISTRATOR)) {
                return;
            }
            String menuMessage = buildMenuMessage(user);

            int menuChoice = askNumber(menuMessage);
            switch (menuChoice) {
                case 1 -> listUsers();
                case 2 -> createEmployeeUser();
                case 3 -> updateUser();
                case 4 -> deleteUser();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Unknown menu option");
            }
        }
    }

    private String buildMenuMessage(User user) {
        final StringBuilder menuMessage = new StringBuilder("\nAdmin menu:");
        if (validPermission(user, UserRole.ADMINISTRATOR)) {
            menuMessage.append(" 1. List users | 2. Create employee user | 3. Update user | 4. " + "Delete user |");
        }
        menuMessage.append(" 0. Back");
        return menuMessage.toString();
    }

    private void listUsers() {
        System.out.println("List of users: ");
        userService.getAllUsers().forEach(System.out::println);
    }

    private void createEmployeeUser() {
        System.out.println("Enter the employee user data: ");
        User user = User.createUserFromInput();
        user.setRole(UserRole.EMPLOYEE);
        String password = askText("Password: ");
        try {
            userService.register(user, password);
            System.out.println("User created successfully");
        } catch (SQLException e) {
            System.out.println("Couldn't register employee user, " + e.getLocalizedMessage());
        }
    }

    private void updateUser() {
        int userId = askNumber("Type the id of the user you want to update: ");
        User user = User.createUserFromInput();
        user.setRole(askRole());
        user.setId(userId);
        try {
            userService.updateUser(user);
            System.out.println("User updated successfully");
        } catch (SQLException e) {
            System.out.println("Couldn't update the user, " + e.getLocalizedMessage());
        }
    }

    private UserRole askRole() {
        UserRole role = null;
        while (role == null) {
            int roleChoice = askNumber("Choose a role: 1. READER | 2. EMPLOYEE | 3. ADMIN");
            switch (roleChoice) {
                case 1 -> role = UserRole.READER;
                case 2 -> role = UserRole.EMPLOYEE;
                case 3 -> role = UserRole.ADMINISTRATOR;
                default -> System.out.println("Unknown role choice");
            }
        }
        return role;
    }

    private void deleteUser() {
        int userId = askNumber("Type the id of the user you want to delete: ");
        try {
            userService.deleteUser(userId);
            System.out.println("User deleted successfully");
        } catch (SQLException e) {
            System.out.println("Couldn't delete the user, " + e.getLocalizedMessage());
        }
    }
}
