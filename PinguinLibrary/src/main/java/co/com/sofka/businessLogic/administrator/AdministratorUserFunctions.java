package co.com.sofka.businessLogic.administrator;

import co.com.sofka.businessLogic.generalAdmin.GeneralAdministrativeManagement;
import co.com.sofka.enums.UserType;
import co.com.sofka.model.User;


import java.util.UUID;

import static co.com.sofka.menu.MenuConstant.*;
import static co.com.sofka.menu.MenuMessage.administratorUserMenuMessage;
import static co.com.sofka.utils.Utils.getIntOption;
import static co.com.sofka.utils.Utils.getStringOption;

public class AdministratorUserFunctions {
    private static final AdministratorManagement administratorManagement = new AdministratorManagement();
    private static final GeneralAdministrativeManagement generalAdministrativeManagement = new GeneralAdministrativeManagement();

    public static void administratorUserMenuOptions(User user){
        boolean keepMenu = true;
        while (keepMenu){
            administratorUserMenuMessage(user);
            System.out.print(enterYourOptionMessage);
            int option = getIntOption();
            switch (option){
                case 1:
                    insertUser();
                    break;
                case 2:
                    seeAllUsers();
                    break;
                case 3:
                    seeUserByEmail();
                    break;
                case 4:
                    updateUser();
                    break;
                case 5:
                    deleteUser();
                    break;
                case 6:
                    System.out.println(exitingMessage);
                    keepMenu = false;
                    break;
                default:
                    System.out.println(incorrectOptionMessage);
            }
        }
    }

    private static void deleteUser() {
        System.out.println("Enter user's email: ");
        String email = getStringOption();
        administratorManagement.deleteUser(generalAdministrativeManagement.getUserByEmail(email));
    }

    private static void updateUser() {
        System.out.println("Enter user's email: ");
        User oldUser = generalAdministrativeManagement.getUserByEmail(getStringOption());
        System.out.println("ENTER NEW DATA");
        User newUser = getUserData();
        newUser.setId(oldUser.getId());
        administratorManagement.updateUser(newUser);
    }

    private static User getUserData(){
        System.out.println("Enter name: ");
        String name = getStringOption();
        System.out.println("Enter email: ");
        String email = getStringOption();
        System.out.println("Enter password: ");
        String password = getStringOption();
        System.out.println("Enter user role: (ADMINISTRATOR/ASSISTANT/READER)");
        UserType role = UserType.valueOf(getStringOption());
        return new User(UUID.randomUUID().toString(), name, email, password, role);
    }

    private static void seeUserByEmail() {
        System.out.println("Enter user's email: ");
        System.out.println(generalAdministrativeManagement.getUserByEmail(getStringOption()));
    }

    private static void seeAllUsers() {
        administratorManagement.getAllUsers().forEach(user -> {
            System.out.println("User's name: " + user.getName());
            System.out.println("User's email: " + user.getEmail());
            System.out.println("User's password: " + user.getPassword());
            System.out.println("User's role: " + user.getRole());
            System.out.println();
        });
    }

    private static void insertUser() {
        administratorManagement.insertUser(getUserData());
    }

}
