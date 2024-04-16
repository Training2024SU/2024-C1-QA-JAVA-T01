package co.com.sofka.DAO;

import co.com.sofka.model.Author;

import java.util.List;

public interface AuthorDAO {
    public void insertAuthor(Author author);
    public List<Author> getAllAuthors();
    public Author getAuthorById(String id);
    public void updateAuthor(Author author);
    public void deleteAuthor(Author author);
}
