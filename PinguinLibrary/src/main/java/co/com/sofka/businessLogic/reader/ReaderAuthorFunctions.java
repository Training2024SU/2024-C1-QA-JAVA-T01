package co.com.sofka.businessLogic.reader;

import co.com.sofka.model.Author;
import co.com.sofka.model.Book;
import co.com.sofka.model.User;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static co.com.sofka.menu.MenuConstant.*;
import static co.com.sofka.menu.MenuMessage.readerAuthorMenuMessage;
import static co.com.sofka.utils.Utils.getIntOption;
import static co.com.sofka.utils.Utils.getStringOption;

public class ReaderAuthorFunctions {
    public static final ReaderManagement readerManagement = new ReaderManagement();

    public static void readerAuthorMenuOptions(User user){
        boolean keepMenu = true;
        while (keepMenu){
            readerAuthorMenuMessage(user);
            System.out.println(enterYourOptionMessage);
            int option = getIntOption();
            switch (option){
                case 1:
                    seeAuthorsAndTheirBooks();
                    break;
                case 2:
                    System.out.println("Enter author's name: ");
                    String name = getStringOption();
                    seeAuthorAndTheirBooks(name);
                    break;
                case 3:
                    keepMenu = false;
                    System.out.println(exitingMessage);
                    break;
                default:
                    System.out.println(incorrectOptionMessage);
            }
        }
    }

    private static void seeAuthorsAndTheirBooks() {
        Map<Author, List<Book>> authorsAndTheirBooks = readerManagement.getAllAvailableBooks()
                .stream()
                .collect(Collectors.groupingBy(Book::getAuthor));
        authorsAndTheirBooks.forEach(((author, books) -> {
            System.out.println("Author: " + author.getName());
            System.out.println("Books: ");
            books.forEach(book -> System.out.println("- " +  book.getTitle()));
            System.out.println();
        }));
    }

    private static void seeAuthorAndTheirBooks(String name) {
        Map<Author, List<Book>> authorsAndTheirBooks = readerManagement.getAllAvailableBooks()
                .stream()
                .filter(book -> book.getAuthor().getName().equals(name))
                .collect(Collectors.groupingBy(Book::getAuthor));
        authorsAndTheirBooks.forEach(((author, books) -> {
            System.out.println("Author: " + author.getName());
            System.out.println("Books: ");
            books.forEach(book -> System.out.println("- " +  book.getTitle()));
            System.out.println();
        }));
    }
}
