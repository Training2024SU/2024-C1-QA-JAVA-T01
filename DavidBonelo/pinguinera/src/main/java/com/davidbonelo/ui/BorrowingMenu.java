package com.davidbonelo.ui;

import com.davidbonelo.models.Borrowing;
import com.davidbonelo.models.LibraryItem;
import com.davidbonelo.models.User;
import com.davidbonelo.models.UserRole;
import com.davidbonelo.services.BorrowingsService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static com.davidbonelo.Utils.askDate;
import static com.davidbonelo.Utils.askNumber;
import static com.davidbonelo.Utils.askText;
import static com.davidbonelo.Utils.validMenuAccess;
import static com.davidbonelo.Utils.validPermission;

public class BorrowingMenu {
    private final User user;
    private final BorrowingsService borrowingsService;

    public BorrowingMenu(BorrowingsService borrowingsService, User user) {
        this.borrowingsService = borrowingsService;
        this.user = user;
    }


    public void menu() {
        while (true) {
            String menuMessage = buildMenuMessage(user);

            int menuChoice = askNumber(menuMessage);
            switch (menuChoice) {
                case 1 -> listBorrowingItems();
                case 2 -> listBorrowings();
                case 3 -> showDetails();
                case 4 -> createRequest();
                case 5 -> searchByEmail();
                case 6 -> confirmBorrowing();
                case 8 -> deleteBorrowing();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Unknown menu option");
            }
        }
    }

    private void listBorrowings() {
        System.out.println("Borrowings list: ");
        borrowingsService.getAllBorrowings(user).forEach(System.out::println);
    }

    private void listBorrowingItems() {
        Set<LibraryItem> items = borrowingsService.getItemsToBorrow();
        System.out.println("Selected items pending to confirm request: " + items.size());
        items.forEach(System.out::println);
    }

    private void showDetails() {
        int borrowingId = askNumber("Type the id of the borrowing you want to see its details");
        try {
            Borrowing borrowing = borrowingsService.getBorrowingDetails(user, borrowingId);
            System.out.println("Borrowing details: ");
            System.out.println(borrowing.toStringWithItems());
        } catch (SQLException e) {
            System.out.println("Couldn't get borrowing details," + e.getLocalizedMessage());
        }
    }

    private void createRequest() {
        System.out.println("Requesting selected items");
        LocalDate returnDate = askDate("Type the due date before all the items must be returned:");
        try {
            borrowingsService.createBorrowing(new Borrowing(returnDate, user));
            System.out.println("Successful request, find an employee to borrow the items from");
        } catch (SQLException e) {
            System.out.println("Couldn't request the borrowing, " + e.getLocalizedMessage());
        }
    }

    private void searchByEmail() {
        if (!validMenuAccess(user, UserRole.EMPLOYEE)) {
            return;
        }
        String email = askText("Type the email of the user you want to list its borrowings");
        List<Borrowing> borrowings = borrowingsService.getBorrowingsByEmail(user, email);
        System.out.println("List of borrowings made by: " + email);
        borrowings.forEach(System.out::println);
    }

    private void confirmBorrowing() {
        if (!validMenuAccess(user, UserRole.EMPLOYEE)) {
            return;
        }
        int borrowingId = askNumber("Type the id of the borrowing you want to confirm as " +
                "delivered");
        try {
            borrowingsService.confirmBorrowing(user, borrowingId);
            System.out.println("Borrowing confirmed successfully");
        } catch (SQLException e) {
            System.out.println("Couldn't confirm this borrowing, " + e.getLocalizedMessage());
        }
    }

    private void deleteBorrowing() {
        if (!validMenuAccess(user, UserRole.EMPLOYEE)) {
            return;
        }
        int borrowingId = askNumber("Type the id of the borrowing you want to delete");
        borrowingsService.deleteBorrowing(borrowingId);
    }

    private String buildMenuMessage(User user) {
        final StringBuilder menuMessage = new StringBuilder("\nBorrowings menu:");

        if (user != null) {
            menuMessage.append(" 1. List selected items | 2. List all borrowings | 3. Show " +
                    "borrowing details | 4. Confirm request |");
            if (validPermission(user, UserRole.EMPLOYEE)) {
                menuMessage.append("\n5. Search by email | 6. Confirm borrowing | 7. Finalize " + "borrowing | 8. Delete borrowing |");
            }
        }
        menuMessage.append(" 0. Back");
        return menuMessage.toString();
    }
}
