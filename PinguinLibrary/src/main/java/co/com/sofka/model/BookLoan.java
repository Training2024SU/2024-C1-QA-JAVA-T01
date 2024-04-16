package co.com.sofka.model;

import co.com.sofka.enums.LoanStatus;

import java.sql.Date;

public class BookLoan {
    private String id;
    private User user;
    private Book book;
    private Date loanDate;
    private Date returnDate;
    private LoanStatus loanStatus;

    public BookLoan() {
    }

    public BookLoan(String id, User user, Book book, Date loanDate, Date returnDate, LoanStatus loanStatus) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.loanStatus = loanStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }

    @Override
    public String toString() {
        return "BookLoan{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", book=" + book +
                ", loanDate=" + loanDate +
                ", returnDate=" + returnDate +
                ", loanStatus=" + loanStatus +
                '}';
    }
}
