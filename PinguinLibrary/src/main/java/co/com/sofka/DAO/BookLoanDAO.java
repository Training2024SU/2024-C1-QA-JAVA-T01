package co.com.sofka.DAO;

import co.com.sofka.model.BookLoan;

import java.util.List;

public interface BookLoanDAO {
    public void insertBookLoan(BookLoan bookLoan);
    public List<BookLoan> getAllBookLoans();
    public BookLoan getBookLoanById(String id);
    public void updateBookLoan(BookLoan bookLoan);
    public void deleteBookLoan(BookLoan bookLoan);
}
