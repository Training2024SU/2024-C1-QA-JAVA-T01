package co.com.sofka.DAO;

import co.com.sofka.model.NovelLoan;

import java.util.List;

public interface NovelLoanDAO {
    public void insertNovelLoan(NovelLoan novelLoan);
    public List<NovelLoan> getAllNovelLoans();
    public NovelLoan getNovelLoanById(String id);
    public void updateNovelLoan(NovelLoan novelLoan);
    public void deleteNovelLoan(NovelLoan novelLoan);
}
