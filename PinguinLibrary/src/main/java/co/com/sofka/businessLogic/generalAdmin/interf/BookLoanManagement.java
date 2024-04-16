package co.com.sofka.businessLogic.generalAdmin.interf;

import co.com.sofka.model.BookLoan;

import java.util.List;

public interface BookLoanManagement {
    public void insertBookLoan(BookLoan bookLoan);
    public List<BookLoan> getAllBookLoans();
    public BookLoan getBookLoanById(String id);
    public void updateBookLoan(BookLoan bookLoan);
    public void deleteBookLoan(BookLoan bookLoan);
    public void approveBookLoan(BookLoan bookLoan);

}
