package co.com.sofka.businessLogic.generalAdmin.interf;

import co.com.sofka.model.Author;

import java.util.List;

public interface AuthorManagement {
    public void insertAuthor(Author author);
    public List<Author> getAllAuthors();
    public Author getAuthorByName(String name);
    public void updateAuthor(Author author);
    public void deleteAuthor(Author author);

}
