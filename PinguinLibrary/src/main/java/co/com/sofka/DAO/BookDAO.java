package co.com.sofka.DAO;

import co.com.sofka.model.Book;

import java.util.List;

public interface BookDAO {
    public void insertBook(Book book);
    public List<Book> getAllBooks();
    public Book getBookById(String id);
    public void updateBook(Book book);
    public void deleteBook(Book book);

}
