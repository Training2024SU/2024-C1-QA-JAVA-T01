package co.com.sofka.businessLogic.generalAdmin.interf;

import co.com.sofka.model.NovelLoan;

import java.util.List;

public interface NovelLoanManagement {
    public void insertNovelLoan(NovelLoan novelLoan);
    public List<NovelLoan> getAllNovelLoans();
    public NovelLoan getNovelLoanById(String id);
    public void updateNovelLoan(NovelLoan novelLoan);
    public void deleteNovelLoan(NovelLoan novelLoan);
    public void approveNovelLoan(NovelLoan novelLoan);
}
