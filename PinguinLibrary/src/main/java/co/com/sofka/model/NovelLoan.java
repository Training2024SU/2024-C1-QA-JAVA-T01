package co.com.sofka.model;

import co.com.sofka.enums.LoanStatus;

import java.sql.Date;

public class NovelLoan {
    private String id;
    private User user;
    private Novel novel;
    private Date loanDate;
    private Date returnDate;
    private LoanStatus loanStatus;

    public NovelLoan() {
    }

    public NovelLoan(String id, User user, Novel novel, Date loanDate, Date returnDate, LoanStatus loanStatus) {
        this.id = id;
        this.user = user;
        this.novel = novel;
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

    public Novel getNovel() {
        return novel;
    }

    public void setNovel(Novel novel) {
        this.novel = novel;
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
        return "NovelLoan{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", novel=" + novel +
                ", loanDate=" + loanDate +
                ", returnDate=" + returnDate +
                ", loanStatus=" + loanStatus +
                '}';
    }
}
