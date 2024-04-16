package co.com.sofka.businessLogic.administrator;

import co.com.sofka.model.User;

import static co.com.sofka.businessLogic.administrator.AdministratorUserFunctions.administratorUserMenuOptions;
import static co.com.sofka.businessLogic.generalAdmin.AdministratorAuthorFunctions.administratorAuthorMenuOptions;
import static co.com.sofka.businessLogic.generalAdmin.AdministratorBookFunctions.administratorBookMenuOptions;
import static co.com.sofka.businessLogic.generalAdmin.AdministratorBookLoanFunctions.administratorBookLoanMenuOptions;
import static co.com.sofka.businessLogic.generalAdmin.AdministratorNovelFunctions.administratorNovelMenuOptions;
import static co.com.sofka.businessLogic.generalAdmin.AdministratorNovelLoanFunctions.administratorNovelLoanMenuOptions;
import static co.com.sofka.menu.MenuConstant.*;
import static co.com.sofka.menu.MenuMessage.administratorMenuMessage;
import static co.com.sofka.utils.Utils.getIntOption;

public class AdministratorFunctions {
    public static void administratorMenu(User user){
        boolean keepMenu = true;
        while (keepMenu){
            administratorMenuMessage(user);
            System.out.print(enterYourOptionMessage);
            int option = getIntOption();
            switch (option){
                case 1:
                    administratorBookMenuOptions(user);
                    break;
                case 2:
                    administratorBookLoanMenuOptions(user);
                    break;
                case 3:
                    administratorNovelMenuOptions(user);
                    break;
                case 4:
                    administratorNovelLoanMenuOptions(user);
                    break;
                case 5:
                    administratorAuthorMenuOptions(user);
                    break;
                case 6:
                    // user
                    administratorUserMenuOptions(user);
                    break;
                case 7:
                    System.out.println(exitingMessage);
                    keepMenu = false;
                    break;
                default:
                    System.out.println(incorrectOptionMessage);
            }
        }
    }
}
