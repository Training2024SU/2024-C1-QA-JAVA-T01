package co.com.sofka.businessLogic.generalAdmin;

import co.com.sofka.enums.LoanStatus;
import co.com.sofka.model.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

import static co.com.sofka.menu.MenuConstant.*;
import static co.com.sofka.menu.MenuMessage.administratorNovelLoanMessage;
import static co.com.sofka.utils.Utils.getIntOption;
import static co.com.sofka.utils.Utils.getStringOption;

public class AdministratorNovelLoanFunctions {
    private static final GeneralAdministrativeManagement generalAdministrativeManagement = new GeneralAdministrativeManagement();
    public static void administratorNovelLoanMenuOptions(User user){
        boolean keepMenu = true;
        while (keepMenu){
            administratorNovelLoanMessage(user);
            System.out.print(enterYourOptionMessage);
            int option = getIntOption();
            switch (option){
                case 1:
                    createNovelLoan();
                    break;
                case 2:
                    seeNovelLoans();
                    break;
                case 3:
                    editAcceptNovelLoan();
                    break;
                case 4:
                    deleteNovelLoan();
                    break;
                case 5:
                    System.out.println(exitingMessage);
                    keepMenu = false;
                    break;
                default:
                    System.out.println(incorrectOptionMessage);
            }
        }
    }

    public static void createNovelLoan(){
        NovelLoan novelLoan = getNovelLoanData();
        generalAdministrativeManagement.insertNovelLoan(novelLoan);
    }
    private static NovelLoan getNovelLoanData(){
        System.out.print("Enter user's email: ");
        String email = getStringOption();

        System.out.print("Enter novel's title: ");
        String novelTitle = getStringOption();

        System.out.print("Enter loan date format(yyyy-mm-dd): ");
        LocalDate loanDate = LocalDate.parse(getStringOption());
        Date loanDateD = Date.valueOf(loanDate);

        System.out.print("Enter return date format(yyyy-mm-dd): ");
        LocalDate returnDate = LocalDate.parse(getStringOption());
        Date returnDateD = Date.valueOf(returnDate);

        System.out.print("Enter loan status (REQUESTED/COMPLETED/FINISHED): ");
        LoanStatus status = LoanStatus.valueOf(getStringOption());

        return new NovelLoan(UUID.randomUUID().toString(),
                generalAdministrativeManagement.getUserByEmail(email),
                generalAdministrativeManagement.getNovelByTitle(novelTitle),
                loanDateD,
                returnDateD,
                status);
    }

    private static void seeNovelLoans(){
        generalAdministrativeManagement.getAllNovelLoans().forEach(novelLoan -> {
            System.out.println("Novel loan id: " + novelLoan.getId());
            System.out.println("Novel title: " + novelLoan.getNovel().getTitle());
            System.out.println("Novel loan date: " + novelLoan.getLoanDate());
            System.out.println("Novel loan return date: " + novelLoan.getReturnDate());
            System.out.println("Novel loan status: " + novelLoan.getLoanStatus());
            System.out.println();
        });
    }

    private static void editAcceptNovelLoan(){
        System.out.println("Enter novel loan id: ");
        String id = getStringOption();
        NovelLoan oldNovelLoan = generalAdministrativeManagement.getNovelLoanById(id);
        System.out.println("Edit/Accept");
        String option = getStringOption();
        switch (option.toLowerCase()){
            case "edit":
                editNovelLoan(oldNovelLoan);
                break;
            case "accept":
                acceptNovelLoan(oldNovelLoan);
                break;
            default:
                System.out.println(incorrectOptionMessage);
        }
    }

    private static void editNovelLoan(NovelLoan oldNovelLoan){
        NovelLoan newNovelLoan = getNovelLoanData();
        newNovelLoan.setId(oldNovelLoan.getId());
        generalAdministrativeManagement.updateNovelLoan(newNovelLoan);
    }
    private static void acceptNovelLoan(NovelLoan novelLoan){
        generalAdministrativeManagement.approveNovelLoan(novelLoan);
    }
    private static void deleteNovelLoan(){
        System.out.println("Enter novel title: ");
        String title = getStringOption();
        Novel novel = generalAdministrativeManagement.getNovelByTitle(title);
        generalAdministrativeManagement.deleteNovel(novel);
    }

}
