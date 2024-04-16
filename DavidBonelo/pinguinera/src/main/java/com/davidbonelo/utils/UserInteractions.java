package com.davidbonelo.utils;

import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Scanner;

public class UserInteractions {
    private static final Scanner scanner = new Scanner(System.in);

    private UserInteractions() {
        throw new IllegalStateException("Utility Class");
    }

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
        ResourceBundle messages = ResourceBundle.getBundle("ask");
        prompt += "\n> ";
        System.out.print(prompt);
        // Invalid integer handling
        while (!scanner.hasNextInt()) {
            System.out.println(messages.getString("ask.invalid.int"));
            System.out.print(prompt);
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
        ResourceBundle messages = ResourceBundle.getBundle("ask");
        prompt += "\n> ";
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        // Invalid input handling
        while (input.isEmpty()) {
            System.out.print(messages.getString("ask.invalid.empty"));
            System.out.print(prompt);
            input = scanner.nextLine().trim();
        }
        return input;
    }

    public static LocalDate askDate(String prompt) {
        ResourceBundle messages = ResourceBundle.getBundle("ask");
        prompt += " (YYYY-MM-DD)\n> ";
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        LocalDate date = null;
        // Invalid input handling
        while (input.isEmpty() || date == null) {
            try {
                date = LocalDate.parse(input);
            } catch (Exception ignored) {
                System.out.println(messages.getString("ask.invalid.date"));
                System.out.print(prompt);
                input = scanner.nextLine().trim();
            }
        }
        return date;
    }
}