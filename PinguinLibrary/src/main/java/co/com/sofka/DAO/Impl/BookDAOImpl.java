package co.com.sofka.DAO.Impl;

import co.com.sofka.DAO.BookDAO;
import co.com.sofka.model.Author;
import co.com.sofka.model.Book;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static co.com.sofka.businessLogic.Library.mySqlOperation;

public class BookDAOImpl implements BookDAO {
    private static final String insertIntoQuery = "INSERT INTO BOOK " +
            "(book_id, title, quantity, quantity_loaned, category, author_id) " +
            "VALUES ('%s', '%s', %d, %d, '%s', '%s');";
    private static final String selectAllQuery = "SELECT book_id, " +
            "title, " +
            "quantity, " +
            "quantity_loaned, " +
            "quantity_available, " +
            "category, " +
            "author_id " +
            "FROM book;";
    private static final String updateQuery = "UPDATE BOOK SET " +
            "title = '%s', " +
            "quantity = %d, " +
            "quantity_loaned = %d, " +
            "category = '%s', " +
            "author_id = '%s' " +
            "WHERE book_id = '%s';";
    private static final String deleteQuery = "DELETE FROM BOOK WHERE book_id = '%s';";
    @Override
    public void insertBook(Book book) {
        String query = String.format(insertIntoQuery,
                book.getId(),
                book.getTitle(),
                book.getQuantity(),
                book.getQuantityLoaned(),
                book.getCategory(),
                book.getAuthor().getId());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
    }
    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        mySqlOperation.setSqlStatement(selectAllQuery);
        mySqlOperation.executeSqlStatement();
        try (ResultSet resultSet = mySqlOperation.getResultSet()) {
            while (resultSet.next()) {
                Book book = getBookResultSet(resultSet);
                books.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching books: " + e.getMessage(), e);
        }

        return books;
    }

    private Book getBookResultSet(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("book_id");
        String title = resultSet.getString("title");
        String category = resultSet.getString("category");
        String authorId = resultSet.getString("author_id");
        int quantity = resultSet.getInt("quantity");
        int quantityLoaned = resultSet.getInt("quantity_loaned");
        int quantityAvailable = resultSet.getInt("quantity_available");

        AuthorDAOImpl authorDAOImpl = new AuthorDAOImpl();
        Author author = authorDAOImpl.getAuthorById(authorId);

        return new Book(id, quantity, quantityLoaned, quantityAvailable, title, author, category);
    }

    @Override
    public Book getBookById(String id) {
        return getAllBooks()
                .stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateBook(Book book) {
        String query = String.format(updateQuery,
                book.getTitle(),
                book.getQuantity(),
                book.getQuantityLoaned(),
                book.getCategory(),
                book.getAuthor().getId(),
                book.getId());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
    }
    @Override
    public void deleteBook(Book book) {
        String query = String.format(deleteQuery, book.getId());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
    }
}
