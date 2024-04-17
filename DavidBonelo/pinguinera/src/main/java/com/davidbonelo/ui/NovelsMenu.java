package com.davidbonelo.ui;

import com.davidbonelo.models.Novel;
import com.davidbonelo.models.User;
import com.davidbonelo.models.UserRole;
import com.davidbonelo.services.BorrowingsService;
import com.davidbonelo.services.LibraryManager;

import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.davidbonelo.utils.Permissions.validMenuAccess;
import static com.davidbonelo.utils.UserInteractions.askNumber;
import static com.davidbonelo.utils.UserInteractions.askText;

public class NovelsMenu {
    private final LibraryManager libraryManager;
    private final User user;
    private final BorrowingsService borrowingsService;
    private final ResourceBundle messages = ResourceBundle.getBundle("messages");

    public NovelsMenu(LibraryManager libraryManager, BorrowingsService borrowingsService,
                      User user) {
        this.libraryManager = libraryManager;
        this.user = user;
        this.borrowingsService = borrowingsService;
    }

    public void menu() {
        while (true) {
            int menuChoice = showMenu();
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
                default -> System.out.println(messages.getString("unknownOption"));
            }
        }
    }

    private int showMenu() {
        String visitorChoices = messages.getString("novels.choices.visitor");
        String readerChoices = messages.getString("novels.choices.reader");
        String employeeChoices = messages.getString("novels.choices.employee");
        MenuChoices menu = new MenuChoices("Novels", visitorChoices, readerChoices,
                employeeChoices, "");
        return menu.showMenu(user);
    }

    private void searchByAuthor() {
        String author = askText(messages.getString("item.req.author"));
        libraryManager.filterItemsByAuthor(libraryManager.getAllNovels(user), author).forEach(System.out::println);
    }

    private void listNovels() {
        libraryManager.getAllNovels(user).forEach(System.out::println);
    }

    private void listAuthors() {
        libraryManager.getAuthorsList(libraryManager.getAllNovels(user)).forEach(System.out::println);
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
        int novelId = askNumber(messages.getString("novels.req.updateId"));
        System.out.println(messages.getString("novels.req.data"));
        Novel novel = Novel.createNovelFromInput();
        novel.setId(novelId);
        libraryManager.updateItem(novel);
    }

    private void deleteNovel() {
        if (!validMenuAccess(user, UserRole.EMPLOYEE)) {
            return;
        }
        int novelId = askNumber(messages.getString("novels.req.deleteId"));
        libraryManager.deleteNovel(novelId);
    }

    private void addToBorrowing() {
        if (!validMenuAccess(user, UserRole.READER)) {
            return;
        }
        int novelId = askNumber(messages.getString("novels.req.borrowId"));
        try {
            borrowingsService.addBorrowingNovel(novelId);
            System.out.println(messages.getString("novels.res.borrowOk"));
        } catch (SQLException e) {
            System.out.println(messages.getString("novels.res.borrowBad") + e.getLocalizedMessage());
        }
    }
}
