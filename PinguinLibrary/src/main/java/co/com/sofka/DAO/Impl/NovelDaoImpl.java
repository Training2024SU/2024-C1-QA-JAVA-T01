package co.com.sofka.DAO.Impl;

import co.com.sofka.DAO.NovelDAO;
import co.com.sofka.model.Novel;
import co.com.sofka.model.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static co.com.sofka.businessLogic.Library.mySqlOperation;

public class NovelDaoImpl implements NovelDAO {
    private static final String insertIntoQuery = "INSERT INTO novel " +
            "(novel_id, title, recommend_age, quantity, quantity_loaned, gender, author_id) " +
            "VALUES ('%s', '%s', %d, %d, %d, '%s', '%s');";
    private static final String selectAllQuery = "SELECT novel_id, " +
            "title, " +
            "recommend_age, " +
            "quantity, " +
            "quantity_loaned, " +
            "quantity_available, " +
            "gender, " +
            "author_id " +
            "FROM novel;";
    private static final String updateQuery = "UPDATE novel SET " +
            "title = '%s', " +
            "recommend_age = %d, " +
            "quantity = %d, " +
            "quantity_loaned = %d, " +
            "gender = '%s', " +
            "author_id = '%s' " +
            "WHERE novel_id = '%s';";
    private static final String deleteQuery = "DELETE FROM novel WHERE novel_id = '%s';";

    @Override
    public void insertNovel(Novel novel) {
        String query = String.format(insertIntoQuery,
                novel.getId(),
                novel.getTitle(),
                novel.getRecommendedAge(),
                novel.getQuantity(),
                novel.getQuantityLoaned(),
                novel.getGender(),
                novel.getAuthor().getId());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
    }

    @Override
    public List<Novel> getAllNovels() {
        List<Novel> novels = new ArrayList<>();
        mySqlOperation.setSqlStatement(selectAllQuery);
        mySqlOperation.executeSqlStatement();
        try (ResultSet resultSet = mySqlOperation.getResultSet()) {
            while (resultSet.next()) {
                Novel novel = getNovelResultSet(resultSet);
                novels.add(novel);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching novels: " + e.getMessage(), e);
        }
        return novels;
    }

    private Novel getNovelResultSet(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("novel_id");
        String title = resultSet.getString("title");
        int recommendedAge = resultSet.getInt("recommend_age");
        int quantity = resultSet.getInt("quantity");
        int quantityLoaned = resultSet.getInt("quantity_loaned");
        int quantityAvailable = resultSet.getInt("quantity_available");
        String gender = resultSet.getString("gender");
        String authorId = resultSet.getString("author_id");
        AuthorDAOImpl authorDAOImpl = new AuthorDAOImpl();
        Author author = authorDAOImpl.getAuthorById(authorId);
        return new Novel(id,quantity,quantityLoaned,quantityAvailable,title,author,gender,recommendedAge);
    }

    @Override
    public Novel getNovelById(String id) {
        return getAllNovels()
                .stream()
                .filter(novel -> novel.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateNovel(Novel novel) {
        String query = String.format(updateQuery,
                novel.getTitle(),
                novel.getRecommendedAge(),
                novel.getQuantity(),
                novel.getQuantityLoaned(),
                novel.getGender(),
                novel.getAuthor().getId(),
                novel.getId());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
    }

    @Override
    public void deleteNovel(Novel novel) {
        String query = String.format(deleteQuery, novel.getId());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
    }

}
