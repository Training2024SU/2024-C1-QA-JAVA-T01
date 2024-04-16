package co.com.sofka.DAO.Impl;

import co.com.sofka.DAO.BookLoanDAO;
import co.com.sofka.model.Book;
import co.com.sofka.model.BookLoan;
import co.com.sofka.enums.LoanStatus;
import co.com.sofka.model.User;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static co.com.sofka.businessLogic.Library.mySqlOperation;

public class BookLoanDAOImpl implements BookLoanDAO {
    private static final String insertIntoQuery = "INSERT INTO book_loan (book_loan_id, user_id, book_id, loan_date, return_date, status)" +
            "VALUES ('%s', '%s', '%s', %s, %s, '%s')";

    private static final String selectAllQuery = "SELECT book_loan_id, user_id, book_id, loan_date, return_date, status FROM book_loan ";
    private static final String updateQuery = "UPDATE book_loan SET " +
            "user_id = '%s', " +
            "book_id = '%s', " +
            "loan_date = '%s', " +
            "return_date = '%s', " +
            "status = '%s' " +
            "WHERE book_loan_id = '%s'";

    private static final String deleteQuery = "DELETE FROM book_loan WHERE book_loan_id = '%s'";


    public void insertBookLoan(BookLoan bookLoan) {
        String query = String.format(insertIntoQuery,
                bookLoan.getId(),
                bookLoan.getUser().getId(),
                bookLoan.getBook().getId(),
                bookLoan.getLoanDate(),
                bookLoan.getReturnDate(),
                bookLoan.getLoanStatus());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
    }

    public List<BookLoan> getAllBookLoans() {
        List<BookLoan> bookLoans = new ArrayList<>();
        mySqlOperation.setSqlStatement(selectAllQuery);
        mySqlOperation.executeSqlStatement();
        try (ResultSet resultSet = mySqlOperation.getResultSet()) {
            while (resultSet.next()) {
                BookLoan bookLoan = getBookLoanResultSet(resultSet);
                bookLoans.add(bookLoan);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching book loans: " + e.getMessage(), e);
        }
        return bookLoans;
    }

    private BookLoan getBookLoanResultSet(ResultSet resultSet) throws SQLException {
        BookDAOImpl bookDAOImpl = new BookDAOImpl();
        UserDAOImpl userDAOImpl = new UserDAOImpl();
        Book book;
        User user;
        String id = resultSet.getString("book_loan_id");
        user = userDAOImpl.getUserById(resultSet.getString("user_id"));
        book = bookDAOImpl.getBookById(resultSet.getString("book_id"));
        Date loanDate = resultSet.getDate("loan_date");
        Date returnDate = resultSet.getDate("return_date");
        LoanStatus loanStatus = LoanStatus.valueOf(resultSet.getString("status"));
        return new BookLoan(id, user, book, loanDate, returnDate, loanStatus);
    }

    public BookLoan getBookLoanById(String id) {
        return getAllBookLoans()
                .stream()
                .filter(bookLoan -> bookLoan.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void updateBookLoan(BookLoan bookLoan) {
        String query = String.format(updateQuery,
                bookLoan.getUser().getId(),
                bookLoan.getBook().getId(),
                bookLoan.getLoanDate(),
                bookLoan.getReturnDate(),
                bookLoan.getLoanStatus(),
                bookLoan.getId());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
    }

    public void deleteBookLoan(BookLoan bookLoan) {
        String query = String.format(deleteQuery, bookLoan.getId());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
    }
}
