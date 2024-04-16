package com.davidbonelo.services;

import com.davidbonelo.models.Book;
import com.davidbonelo.models.LibraryItem;
import com.davidbonelo.models.Novel;
import com.davidbonelo.persistance.BookDAO;
import com.davidbonelo.persistance.DataHandler;
import com.davidbonelo.persistance.NovelDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataService {
    private final BookDAO bookDAO;
    private final NovelDAO novelDAO;
    private final Connection connection;

    public DataService(BookDAO bookDAO, NovelDAO novelDAO, Connection connection) {
        this.bookDAO = bookDAO;
        this.novelDAO = novelDAO;
        this.connection = connection;
    }

    public void exportInventory(String fileName) throws SQLException, IOException {
        List<LibraryItem> items = new ArrayList<>();
        items.addAll(bookDAO.getAllBooks());
        items.addAll(novelDAO.getAllNovels());
        DataHandler.saveToCsv(fileName, items);
    }

    public void importInventory(String filename) throws SQLException, IOException {
        List<LibraryItem> items = DataHandler.readFromCsv(filename);
        try {
            connection.setAutoCommit(false);
            batchCreateItems(items);
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private void batchCreateItems(List<LibraryItem> items) throws SQLException {
        String sqlB =
                "INSERT INTO Books ( id, title, author, copies, copies_borrowed, field, " +
                        "pages ) VALUES ( ?, ?, ?, ?, ?, ?, ? )";
        String sqlN =
                "INSERT INTO Novels ( id, title, author, copies, copies_borrowed, genre, " +
                        "recommended_age ) VALUES ( ?, ?, ?, ?, ?, ?, ? )";
        try (PreparedStatement statementB = connection.prepareStatement(sqlB); PreparedStatement statementN = connection.prepareStatement(sqlN)) {
            for (LibraryItem item : items) {
                if (item instanceof Book book) {
                    bookDAO.prepareCreate(statementB, book);
                    statementB.addBatch();
                } else if (item instanceof Novel novel) {
                    novelDAO.prepareCreate(statementN, novel);
                    statementN.addBatch();
                }
            }
            statementB.executeBatch();
            statementN.executeBatch();
        }
    }
}
