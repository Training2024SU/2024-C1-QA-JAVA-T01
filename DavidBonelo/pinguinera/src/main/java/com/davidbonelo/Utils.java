package com.davidbonelo;

import com.davidbonelo.models.User;
import com.davidbonelo.models.UserRole;

import java.util.Scanner;

public class Utils {
    private static final Scanner scanner = new Scanner(System.in);

    public static void closeScanner() {
        scanner.close();
    }

    /**
     * Simple method to show a prompt and read an integer from std
     *
     * @param prompt a string to show in stdout
     * @return an Integer read from stdin.
     */
    public static int askNumber(String prompt) {
        prompt += "\n> ";
        System.out.print(prompt);
        // Invalid integer handling
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter an integer.\n>");
            scanner.next(); // clear invalid input
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // clear buffer
        return input;
    }

    /**
     * Simple method to show a prompt and read a string from std
     *
     * @param prompt a string to show in stdout
     * @return a String read from stdin
     */
    public static String askText(String prompt) {
        prompt += "\n> ";
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        // Invalid input handling
        while (input.isEmpty()) {
            System.out.print("Invalid input. Please enter a non-empty string.\n>");
            System.out.print(prompt);
            input = scanner.nextLine().trim();
        }
        return input;
    }

    public static boolean validMenuAccess(User user, UserRole requiredRole) {
        boolean valid = validPermission(user, requiredRole);
        if (!valid) {
            System.out.println("Unknown menu option");
        }
        return valid;
    }

    public static boolean validPermission(User user, UserRole requiredRole) {
        if (user == null) {
            return false;
        }
        if (user.getRole().equals(UserRole.ADMINISTRATOR)) {
            return true;
        }
        return user.getRole().equals(requiredRole);
    }
}