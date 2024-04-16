package co.com.sofka.businessLogic.generalAdmin.interf;

import co.com.sofka.model.Book;

import java.util.List;

public interface BookManagement {
    public void insertBook(Book book);
    public List<Book> getAllBooks();
    public Book getBookByTitle(String title);
    public void updateBook(Book book);
    public void deleteBook(Book book);
}
