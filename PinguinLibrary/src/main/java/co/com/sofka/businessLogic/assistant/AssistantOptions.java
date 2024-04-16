package co.com.sofka.businessLogic.assistant;

import co.com.sofka.model.User;

import static co.com.sofka.businessLogic.administrator.AdministratorUserFunctions.administratorUserMenuOptions;
import static co.com.sofka.businessLogic.generalAdmin.AdministratorAuthorFunctions.administratorAuthorMenuOptions;
import static co.com.sofka.businessLogic.generalAdmin.AdministratorBookFunctions.administratorBookMenuOptions;
import static co.com.sofka.businessLogic.generalAdmin.AdministratorBookLoanFunctions.administratorBookLoanMenuOptions;
import static co.com.sofka.businessLogic.generalAdmin.AdministratorNovelFunctions.administratorNovelMenuOptions;
import static co.com.sofka.businessLogic.generalAdmin.AdministratorNovelLoanFunctions.administratorNovelLoanMenuOptions;
import static co.com.sofka.menu.MenuConstant.*;
import static co.com.sofka.menu.MenuMessage.assistantMenuMessage;
import static co.com.sofka.utils.Utils.getIntOption;

public class AssistantOptions {
    public static void assistantMenu(User user){
        boolean keepMenu = true;
        while (keepMenu){
            assistantMenuMessage(user);
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
