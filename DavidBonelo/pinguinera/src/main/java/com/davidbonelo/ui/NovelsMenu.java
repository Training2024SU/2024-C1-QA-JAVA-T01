package com.davidbonelo.ui;

import com.davidbonelo.models.Novel;
import com.davidbonelo.models.User;
import com.davidbonelo.models.UserRole;
import com.davidbonelo.services.BorrowingsService;
import com.davidbonelo.services.LibraryManager;

import java.sql.SQLException;

import static com.davidbonelo.Utils.askNumber;
import static com.davidbonelo.Utils.askText;
import static com.davidbonelo.Utils.validMenuAccess;
import static com.davidbonelo.Utils.validPermission;

public class NovelsMenu {
    private final LibraryManager libraryManager;
    private final User user;
    private final BorrowingsService borrowingsService;

    public NovelsMenu(LibraryManager libraryManager, BorrowingsService borrowingsService,
                      User user) {
        this.libraryManager = libraryManager;
        this.user = user;
        this.borrowingsService = borrowingsService;
    }

    public void menu() {
        while (true) {
            String menuMessage = buildMenuMessage(user);

            int menuChoice = askNumber(menuMessage);
            switch (menuChoice) {
                case 1 -> listNovels();
                case 2 -> listAuthors();
                case 3 -> searchByAuthor();
                case 4 -> addToBorrowing();
                case 5 -> registerNovel();
                case 6 -> updateNovel();
                case 7 -> deleteNovel();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Unknown menu option");
            }
        }
    }

    private void searchByAuthor() {
        String author = askText("Type the name of the author you want to search: ");
        libraryManager.filterItemsByAuthor(libraryManager.getAllNovels(), author).forEach(System.out::println);
    }

    private void listNovels() {
        libraryManager.getAllNovels().forEach(System.out::println);
    }

    private void listAuthors() {
        libraryManager.getAuthorsList(libraryManager.getAllNovels()).forEach(System.out::println);
    }

    private void registerNovel() {
        if (!validMenuAccess(user, UserRole.EMPLOYEE)) {
            return;
        }
        libraryManager.registerItem(Novel.createNovelFromInput());
    }

    private void updateNovel() {
        if (!validMenuAccess(user, UserRole.EMPLOYEE)) {
            return;
        }
        int novelId = askNumber("Type the id of the novel you want to update: ");
        System.out.println("Type the new data for the novel: ");
        Novel novel = Novel.createNovelFromInput();
        novel.setId(novelId);
        libraryManager.updateItem(novel);
    }

    private void deleteNovel() {
        if (!validMenuAccess(user, UserRole.EMPLOYEE)) {
            return;
        }
        int novelId = askNumber("Type the id of the novel you want to delete: ");
        libraryManager.deleteNovel(novelId);
    }

    private void addToBorrowing() {
        if (!validMenuAccess(user, UserRole.READER)) {
            return;
        }
        int novelId = askNumber("Type the id of the novel you want to borrow: ");
        try {
            borrowingsService.addBorrowingNovel(novelId);
            System.out.println("Novel added, go to the Borrowings menu to complete the request");
        } catch (SQLException e) {
            System.out.println("Can't borrow this novel. " + e.getLocalizedMessage());
        }
    }

    private String buildMenuMessage(User user) {
        final StringBuilder menuMessage = new StringBuilder("Novels menu:");
        menuMessage.append(" 1. List Novels | 2. List authors | 3. Search by author |");

        if (user != null) {
            menuMessage.append(" 4. Borrow a Novel |");
            if (validPermission(user, UserRole.EMPLOYEE)) {
                menuMessage.append(" 5. Register Novel | 6. Update Novel | 7. Delete Novel |");
            }
        }
        menuMessage.append(" 0. Back");
        return menuMessage.toString();
    }
}
