package co.com.sofka.businessLogic.generalAdmin;

import co.com.sofka.model.Author;
import co.com.sofka.model.User;

import java.util.UUID;

import static co.com.sofka.menu.MenuConstant.*;
import static co.com.sofka.menu.MenuMessage.administratorAuthorLoanMessage;
import static co.com.sofka.utils.Utils.getIntOption;
import static co.com.sofka.utils.Utils.getStringOption;

public class AdministratorAuthorFunctions {
    private static final GeneralAdministrativeManagement generalAdministrativeManagement = new GeneralAdministrativeManagement();
    public static void administratorAuthorMenuOptions(User user){
        boolean keepMenu = true;
        while (keepMenu){
            administratorAuthorLoanMessage(user);
            System.out.print(enterYourOptionMessage);
            int option = getIntOption();
            switch (option){
                case 1:
                    createAuthor();
                    break;
                case 2:
                    seeAllAuthors();
                    break;
                case 3:
                    seeAuthorByName();
                    break;
                case 4:
                    updateAuthor();
                    break;
                case 5:
                    deleteAuthor();
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
    private static void createAuthor(){
        String name = getAuthorData();
        generalAdministrativeManagement.insertAuthor(new Author(UUID.randomUUID().toString(), name));
    }

    private static String getAuthorData() {
        System.out.print("Enter author's name: ");
        return getStringOption();
    }

    private static void seeAllAuthors(){
        generalAdministrativeManagement.getAllAuthors().forEach(author -> {
            System.out.println("Author's name: " + author.getName());
        });
        System.out.println();
    }
    private static void seeAuthorByName(){
        System.out.println(generalAdministrativeManagement.getAuthorByName(getAuthorData()));
    }

    private static void updateAuthor(){
        String oldName = getAuthorData();
        Author author = generalAdministrativeManagement.getAuthorByName(oldName);
        System.out.println("Enter new data: ");
        String newName = getAuthorData();
        author.setName(newName);
        generalAdministrativeManagement.updateAuthor(author);
    }

    private static void deleteAuthor(){
        String name = getAuthorData();
        generalAdministrativeManagement.deleteAuthor(generalAdministrativeManagement.getAuthorByName(name));
    }

}
