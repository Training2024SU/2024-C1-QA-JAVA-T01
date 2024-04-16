package co.com.sofka.DAO.Impl;

import co.com.sofka.DAO.AuthorDAO;
import co.com.sofka.model.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static co.com.sofka.businessLogic.Library.mySqlOperation;

public class AuthorDAOImpl implements AuthorDAO {

    private static final String insertIntoQuery = "INSERT INTO author (id, name) VALUES ('%s', '%s');";
    private static final String selectAllQuery = "SELECT id, name FROM author";
    private static final String updateQuery = "UPDATE author SET " +
            "name = '%s' " +
            "WHERE id = '%s';";
    private static final String deleteQuery = "DELETE FROM author WHERE id = '%s';";



    @Override
    public void insertAuthor(Author author) {
        String query = String.format(insertIntoQuery, author.getId(), author.getName());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
    }

    @Override
    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();

        mySqlOperation.setSqlStatement(selectAllQuery);
        mySqlOperation.executeSqlStatement();
        ResultSet resultSet = mySqlOperation.getResultSet();

        try {
            while(resultSet.next()){
                String id   = resultSet.getString("id");
                String name = resultSet.getString("name");
                authors.add(new Author(id, name));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return authors;
    }

    @Override
    public Author getAuthorById(String id) {
        return getAllAuthors()
                .stream()
                .filter(author -> author.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    @Override
    public void updateAuthor(Author author) {
        String query = String.format(updateQuery, author.getName(), author.getId());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
    }

    @Override
    public void deleteAuthor(Author author) {
        String query = String.format(deleteQuery, author.getId());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
    }
}
