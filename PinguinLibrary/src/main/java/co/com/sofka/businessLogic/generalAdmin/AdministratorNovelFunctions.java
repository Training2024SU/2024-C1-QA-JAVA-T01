package co.com.sofka.businessLogic.generalAdmin;

import co.com.sofka.model.Novel;
import co.com.sofka.model.User;

import java.util.UUID;

import static co.com.sofka.menu.MenuConstant.*;
import static co.com.sofka.menu.MenuMessage.administratorNovelMenuMessage;
import static co.com.sofka.utils.CsvNovelManagement.exportNovelsData;
import static co.com.sofka.utils.Utils.getIntOption;
import static co.com.sofka.utils.Utils.getStringOption;

public class AdministratorNovelFunctions {
    private static final GeneralAdministrativeManagement generalAdministrativeManagement = new GeneralAdministrativeManagement();
    public static void administratorNovelMenuOptions(User user){
        boolean keepMenu = true;
        while (keepMenu){
            administratorNovelMenuMessage(user);
            System.out.print(enterYourOptionMessage);
            int option = getIntOption();
            switch (option){
                case 1:
                    insertNovel();
                    break;
                case 2:
                    seeAllNovels();
                    break;
                case 3:
                    seeNovelByTitle();
                    break;
                case 4:
                    updateNovel();
                    break;
                case 5:
                    deleteNovel();
                    break;
                case 6:
                    exportNovelsData();
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

    private static void insertNovel(){
        Novel novel = getNovelData();
        generalAdministrativeManagement.insertNovel(novel);
    }

    private static Novel getNovelData(){
        System.out.print("Enter novel title: ");
        String title = getStringOption();

        System.out.print("Enter author's name: ");
        String author = getStringOption();

        System.out.print("Enter category: ");
        String gender = getStringOption();

        System.out.print("Enter recommended age: ");
        int recommendedAge = getIntOption();

        System.out.print("Enter quantity: ");
        int quantity = getIntOption();

        System.out.print("Enter quantity loaned: ");
        int quantityLoaned = getIntOption();

        Novel novel = new Novel();
        novel.setId(UUID.randomUUID().toString());
        novel.setTitle(title);
        novel.setAuthor(generalAdministrativeManagement.getAuthorByName(author));
        novel.setRecommendedAge(recommendedAge);
        novel.setGender(gender);
        novel.setQuantity(quantity);
        novel.setQuantityLoaned(quantityLoaned);
        return novel;
    }

    private static void seeAllNovels(){
        generalAdministrativeManagement.getAllNovels().forEach(System.out::println);
    }

    private static void seeNovelByTitle(){
        System.out.print("Enter novel title: ");
        String title = getStringOption();
        System.out.println(generalAdministrativeManagement.getNovelByTitle(title));
    }

    private static void updateNovel(){
        System.out.print("Enter novel id: ");
        String id = getStringOption();
        Novel newNovel = getNovelData();
        Novel oldNovel = generalAdministrativeManagement.getNovelById(id);
        newNovel.setId(oldNovel.getId());
        generalAdministrativeManagement.updateNovel(newNovel);
    }

    private static void deleteNovel(){
        System.out.print("Enter novel title: ");
        String title = getStringOption();
        generalAdministrativeManagement.deleteNovel(generalAdministrativeManagement.getNovelByTitle(title));
    }
}
