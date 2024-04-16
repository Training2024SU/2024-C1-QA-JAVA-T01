package co.com.sofka.businessLogic.reader;

import co.com.sofka.model.Novel;
import co.com.sofka.model.User;

import static co.com.sofka.menu.MenuConstant.exitingMessage;
import static co.com.sofka.menu.MenuConstant.incorrectOptionMessage;
import static co.com.sofka.menu.MenuMessage.readerNovelMenuMessage;
import static co.com.sofka.utils.Utils.getIntOption;
import static co.com.sofka.utils.Utils.getStringOption;

public class ReaderNovelFunctions {
    private static final ReaderManagement readerManagement = new ReaderManagement();
    public static void readerNovelMenuOptions(User user){
        boolean keepMenu = true;
        while (keepMenu){
            readerNovelMenuMessage(user);
            System.out.print("Enter your option: ");
            int option = getIntOption();
            switch (option){
                case 1:
                    System.out.println(readerManagement.getAllAvailableNovels());
                    break;
                case 2:
                    seeNovelByNameOption();
                    break;
                case 3:
                    loanNovelOption(user);
                    break;
                case 4:
                    System.out.println(readerManagement.getAllUserNovelLoan(user));
                    break;
                case 5:
                    returnNovelLoan();
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

    private static void returnNovelLoan() {
        System.out.println("Enter novel loan id: ");
        String id = getStringOption();
        readerManagement.returnNovelLoan(id);
    }

    private static void loanNovelOption(User user) {
        System.out.println("Enter novel name: ");
        String novel = getStringOption();
        Novel novelToLoan =  readerManagement.getAvailableNovelByTitle(novel);
        if(novelToLoan == null){
            System.out.println("There are not available options");
        }else{
            readerManagement.loanNovel(user, novelToLoan);
        }
    }
    private static void seeNovelByNameOption() {
        System.out.print("Enter the title: ");
        String name = getStringOption();
        System.out.println(readerManagement.getAvailableNovelByTitle(name));
    }
}
