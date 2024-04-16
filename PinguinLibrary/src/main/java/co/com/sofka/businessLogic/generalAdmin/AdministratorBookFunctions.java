package co.com.sofka.businessLogic.generalAdmin;


import co.com.sofka.model.Book;
import co.com.sofka.model.User;

import java.util.UUID;


import static co.com.sofka.menu.MenuConstant.*;
import static co.com.sofka.menu.MenuMessage.administratorBookMenuMessage;
import static co.com.sofka.utils.CsvBookManagement.exportBooksData;
import static co.com.sofka.utils.Utils.getIntOption;
import static co.com.sofka.utils.Utils.getStringOption;

public class AdministratorBookFunctions {
    private static final GeneralAdministrativeManagement generalAdministrativeManagement = new GeneralAdministrativeManagement();

    public static void administratorBookMenuOptions(User user){
        boolean keepMenu = true;
        while (keepMenu){
            administratorBookMenuMessage(user);
            System.out.print(enterYourOptionMessage);
            int option = getIntOption();
            switch (option){
                case 1:
                    insertBook();
                    break;
                case 2:
                    seeAllBooks();
                    break;
                case 3:
                    seeBookByTitle();
                    break;
                case 4:
                    updateBook();
                    break;
                case 5:
                    deleteBook();
                    break;
                case 6:
                    exportBooksData();
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

    private static void insertBook(){
        Book book = getBookData();
        generalAdministrativeManagement.insertBook(book);
    }

    private static Book getBookData(){
        System.out.print("Enter book title: ");
        String title = getStringOption();

        System.out.print("Enter author's name: ");
        String author = getStringOption();

        System.out.print("Enter category: ");
        String category = getStringOption();

        System.out.print("Enter quantity: ");
        int quantity = getIntOption();

        System.out.print("Enter quantity loaned: ");
        int quantityLoaned = getIntOption();

        Book book = new Book();
        book.setId(UUID.randomUUID().toString());
        book.setTitle(title);
        book.setAuthor(generalAdministrativeManagement.getAuthorByName(author));
        book.setCategory(category);
        book.setQuantity(quantity);
        book.setQuantityLoaned(quantityLoaned);
        return book;
    }

    private static void seeAllBooks(){
        generalAdministrativeManagement.getAllBooks().forEach(System.out::println);
    }

    private static void seeBookByTitle(){
        System.out.print("Enter book title: ");
        String title = getStringOption();
        System.out.println(generalAdministrativeManagement.getBookByTitle(title));
    }

    private static void updateBook(){
        System.out.print("Enter book id: ");
        String id = getStringOption();
        Book newBook = getBookData();
        Book oldBook = generalAdministrativeManagement.getBookById(id);
        newBook.setId(oldBook.getId());
        generalAdministrativeManagement.updateBook(newBook);
    }

    private static void deleteBook(){
        System.out.print("Enter book title: ");
        String title = getStringOption();
        generalAdministrativeManagement.deleteBook(generalAdministrativeManagement.getBookByTitle(title));
    }
}
