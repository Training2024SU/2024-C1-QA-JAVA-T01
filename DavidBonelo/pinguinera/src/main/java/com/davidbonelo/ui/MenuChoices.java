package com.davidbonelo.ui;

import com.davidbonelo.models.User;
import com.davidbonelo.models.UserRole;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import static com.davidbonelo.utils.Permissions.validPermission;
import static com.davidbonelo.utils.UserInteractions.askNumber;

public class MenuChoices {
    private final String menuName;
    private final String visitorChoices;
    private final String readerChoices;
    private final String employeeChoices;
    private final String adminChoices;
    private final ResourceBundle messages = ResourceBundle.getBundle("messages");

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
        final StringBuilder menuMessage =
                new StringBuilder(MessageFormat.format(messages.getString("main.menu.name"),
                        menuName));

        menuMessage.append(visitorChoices);

        buildUserChoices(user, menuMessage);
        buildLoginChoices(user, menuMessage);

        menuMessage.append(isMainMenu() ? messages.getString("exit") : messages.getString("back"));
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
            menuMessage.insert(offset, messages.getString("login"));
        } else {
            menuMessage.append(messages.getString("logout"));
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
        String mainKeyword = messages.getString("main.keyword");
        return menuName.equals(mainKeyword);
    }
}
