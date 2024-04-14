package com.davidbonelo.persistance;

import com.davidbonelo.models.LibraryItem;
import com.davidbonelo.models.Novel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NovelDAO {
    private final Connection connection;

    public NovelDAO(Connection connection) {
        this.connection = connection;
    }

    static Novel buildNovelFromResult(ResultSet rs) throws SQLException {
        return new Novel(rs.getInt("id"), rs.getString("title"), rs.getString("author"),
                rs.getInt("copies"), rs.getInt("copies_borrowed"), rs.getString("genre"),
                rs.getInt("recommended_age"));
    }

    public Novel getNovelById(int itemId) throws SQLException {
        String sql = "SELECT * FROM Novels WHERE is_deleted = 0 AND id= ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, itemId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return buildNovelFromResult(rs);
            }
            rs.close();
        }
        return null;
    }

    public List<Novel> getAllNovels() throws SQLException {
        List<Novel> novels = new ArrayList<>();
        String sql = "SELECT * FROM Novels WHERE is_deleted = 0";
        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet rs =
                statement.executeQuery()) {
            while (rs.next()) {
                novels.add(buildNovelFromResult(rs));
            }
        }
        return novels;
    }

    public void createNovel(Novel novel) throws SQLException {
        String sql = "INSERT INTO Novels ( title, author, copies, copies_borrowed, genre, " +
                "recommended_age ) VALUES ( ?, ?, ?, ?, ?, ? )";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, novel.getTitle());
            statement.setString(2, novel.getAuthor());
            statement.setInt(3, novel.getCopies());
            statement.setInt(4, novel.getCopiesBorrowed());
            statement.setString(5, novel.getGenre());
            statement.setInt(6, novel.getRecommendedAge());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted == 0) {
                throw new SQLException("Creating novel failed, no rows affected.");
            }
        }
    }

    public void updateNovel(Novel novel) throws SQLException {
        if (!containsId(novel)) {
            throw new IllegalArgumentException("Cant update a Novel without an id");
        }
        String sql =
                "UPDATE Novels b SET title= ?, author= ?, copies= ?, copies_borrowed= ?, " +
                        "genre= ?, recommended_age= ? WHERE is_deleted = 0 AND b.id= ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, novel.getTitle());
            statement.setString(2, novel.getAuthor());
            statement.setInt(3, novel.getCopies());
            statement.setInt(4, novel.getCopiesBorrowed());
            statement.setString(5, novel.getGenre());
            statement.setInt(6, novel.getRecommendedAge());
            statement.setInt(7, novel.getId()); // WHERE
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Update of novel with id " + novel.getId() + " failed.");
            }
        }
    }

    public void deleteNovel(int novelId) throws SQLException {
        String sql = "UPDATE Novels SET is_deleted = 1 WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, novelId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted == 0) {
                throw new SQLException("Novel deletion failed, no rows affected");
            }
        }
    }

    private boolean containsId(LibraryItem item) {
        return item.getId() != 0;
    }
}
