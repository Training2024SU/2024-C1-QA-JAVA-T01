package co.com.sofka.DAO.Impl;

import co.com.sofka.DAO.NovelLoanDAO;
import co.com.sofka.enums.LoanStatus;
import co.com.sofka.model.Novel;
import co.com.sofka.model.NovelLoan;
import co.com.sofka.model.User;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static co.com.sofka.businessLogic.Library.mySqlOperation;

public class NovelLoanDAOImpl implements NovelLoanDAO {
    private static final String insertIntoQuery = "INSERT INTO novel_loan (novel_loan_id, user_id, novel_id, loan_date, return_date, status)" +
            "VALUES ('%s', '%s', '%s', %s, %s, '%s')";

    private static final String selectAllQuery = "SELECT novel_loan_id, user_id, novel_id, loan_date, return_date, status FROM novel_loan ";
    private static final String updateQuery = "UPDATE novel_loan SET " +
            "user_id = '%s', " +
            "novel_id = '%s', " +
            "loan_date = '%s', " +
            "return_date = '%s', " +
            "status = '%s' " +
            "WHERE novel_loan_id = '%s'";

    private static final String deleteQuery = "DELETE FROM novel_loan WHERE novel_loan_id = '%s'";

    @Override
    public void insertNovelLoan(NovelLoan novelLoan) {
        String query = String.format(insertIntoQuery,
                novelLoan.getId(),
                novelLoan.getUser().getId(),
                novelLoan.getNovel().getId(),
                novelLoan.getLoanDate(),
                novelLoan.getReturnDate(),
                novelLoan.getLoanStatus());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
    }

    @Override
    public List<NovelLoan> getAllNovelLoans() {
        List<NovelLoan> novelLoans = new ArrayList<>();
        mySqlOperation.setSqlStatement(selectAllQuery);
        mySqlOperation.executeSqlStatement();
        try (ResultSet resultSet = mySqlOperation.getResultSet()){
            while (resultSet.next()){
                NovelLoan novelLoan = getNovelLoanResultSet(resultSet);
                novelLoans.add(novelLoan);
            }
        }catch (SQLException e){
            throw new RuntimeException("Error fetching novel loans: " + e.getMessage(), e);
        }
        return novelLoans;
    }

    @Override
    public NovelLoan getNovelLoanById(String id) {
        return getAllNovelLoans()
                .stream()
                .filter(novelLoan -> novelLoan.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private NovelLoan getNovelLoanResultSet(ResultSet resultSet) throws SQLException {
        NovelDaoImpl novelDaoImpl = new NovelDaoImpl();
        UserDAOImpl userDAOImpl = new UserDAOImpl();
        Novel novel;
        User user;
        String id = resultSet.getString("novel_loan_id");
        user = userDAOImpl.getUserById(resultSet.getString("user_id"));
        novel = novelDaoImpl.getNovelById(resultSet.getString("novel_id"));
        Date loanDate = resultSet.getDate("loan_date");
        Date returnDate = resultSet.getDate("return_date");
        LoanStatus loanStatus = LoanStatus.valueOf(resultSet.getString("status"));
        return new NovelLoan(id, user, novel, loanDate, returnDate, loanStatus);
    }


    @Override
    public void updateNovelLoan(NovelLoan novelLoan) {
        String query = String.format(updateQuery,
                novelLoan.getUser().getId(),
                novelLoan.getNovel().getId(),
                novelLoan.getLoanDate(),
                novelLoan.getReturnDate(),
                novelLoan.getLoanStatus(),
                novelLoan.getId());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
    }
    @Override
    public void deleteNovelLoan(NovelLoan novelLoan) {
        String query = String.format(deleteQuery, novelLoan.getId());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
    }
}

