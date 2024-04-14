package com.davidbonelo.ui;

import com.davidbonelo.models.User;
import com.davidbonelo.models.UserRole;

import static com.davidbonelo.utils.UserInteractions.askNumber;
import static com.davidbonelo.utils.Permissions.validPermission;

public class MenuChoices {
    private final String menuName;
    private final String visitorChoices;
    private final String readerChoices;
    private final String employeeChoices;
    private final String adminChoices;

    public MenuChoices(String menuName, String visitorChoices, String readerChoices,
                       String employeeChoices, String adminChoices) {
        this.menuName = menuName;
        this.visitorChoices = visitorChoices;
        this.readerChoices = readerChoices;
        this.employeeChoices = employeeChoices;
        this.adminChoices = adminChoices;
    }

    public int showMenu(User user) {
        String menuMessage = buildMenuMessage(user);
        return askNumber(menuMessage);
    }

    private String buildMenuMessage(User user) {
        final StringBuilder menuMessage = new StringBuilder(menuName + " menu:");

        menuMessage.append(visitorChoices);

        buildUserChoices(user, menuMessage);
        buildLoginChoices(user, menuMessage);

        menuMessage.append(isMainMenu() ? " 0. Exit" : " 0. Back");
        return menuMessage.toString();
    }

    private void buildLoginChoices(User user, StringBuilder menuMessage) {
        // add these choices only if it's the main menu
        if (!isMainMenu()) {
            return;
        }

        if (user == null) {
            int offset = menuName.length() + 6;
            // Inserts as first option after the menu name
            menuMessage.insert(offset, " 1. Login |");
        } else {
            menuMessage.append(" 9. Log out |");
        }
    }

    private void buildUserChoices(User user, StringBuilder menuMessage) {
        if (user != null) {
            // Logged in user
            menuMessage.append(readerChoices);

            if (validPermission(user, UserRole.EMPLOYEE)) {
                menuMessage.append(employeeChoices);
            }
            if (validPermission(user, UserRole.ADMINISTRATOR)) {
                menuMessage.append(adminChoices);
            }
        }
    }

    private boolean isMainMenu() {
        String MAIN_KEYWORD = "Main";
        return menuName.equals(MAIN_KEYWORD);
    }
}
