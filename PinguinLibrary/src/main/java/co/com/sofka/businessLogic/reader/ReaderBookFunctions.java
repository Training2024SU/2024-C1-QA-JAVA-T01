package co.com.sofka.businessLogic.reader;

import co.com.sofka.model.Book;
import co.com.sofka.model.User;

import static co.com.sofka.menu.MenuConstant.*;
import static co.com.sofka.menu.MenuMessage.readerBookMenuMessage;
import static co.com.sofka.utils.Utils.getIntOption;
import static co.com.sofka.utils.Utils.getStringOption;

public class ReaderBookFunctions {
    private static final ReaderManagement readerManagement = new ReaderManagement();
    public static void readerBookMenuOptions(User user){

        boolean keepMenu = true;
        while (keepMenu){
            readerBookMenuMessage(user);
            System.out.print(enterYourOptionMessage);
            int option = getIntOption();
            switch (option){
                case 1:
                    seeAllBooks();
                    break;
                case 2:
                    seeBookByName();
                    break;
                case 3:
                    loanBook(user);
                    break;
                case 4:
                    seeAllUserLoan(user);
                    break;
                case 5:
                    returnBookLoan();
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

    private static void returnBookLoan() {
        System.out.println("Enter book loan id: ");
        String id = getStringOption();
        readerManagement.returnBookLoan(id);
    }

    private static void seeAllUserLoan(User user) {
        readerManagement.getAllUserBookLoan(user).forEach(bookLoan -> {
            System.out.println("Book loan id: " + bookLoan.getId());
            System.out.println("Book id: " + bookLoan.getBook().getTitle());
            System.out.println("Book loan user: " + bookLoan.getUser().getEmail());
            System.out.println("Book loan date: " + bookLoan.getLoanDate());
            System.out.println("Book return date: " + bookLoan.getReturnDate());
            System.out.println("Book loan status: " + bookLoan.getLoanStatus());
            System.out.println();
        });
    }

    private static void seeAllBooks() {
        readerManagement.getAllAvailableBooks().
                forEach(availableBook -> {
                    System.out.println("Book title: " + availableBook.getTitle());
                    System.out.println("Book author: " + availableBook.getAuthor().getName());
                    System.out.println("available quantity: " + availableBook.getQuantityAvailable());
                    System.out.println();
                });
    }

    private static void loanBook(User user) {
        System.out.println("Enter book name: ");
        String book = getStringOption();
        Book bookToLoan =  readerManagement.getAvailableBookByTitle(book);
        if(bookToLoan == null){
            System.out.println("There are not available options");
        }else{
            readerManagement.loanBook(user, bookToLoan);
        }
    }
    private static void seeBookByName() {
        System.out.print("Enter the title: ");
        String name = getStringOption();
        System.out.println(readerManagement.getAvailableBookByTitle(name));
    }
}
