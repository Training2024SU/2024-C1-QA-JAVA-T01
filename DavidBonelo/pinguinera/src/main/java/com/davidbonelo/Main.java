package com.davidbonelo;

import com.davidbonelo.models.Book;
import com.davidbonelo.persistance.BookDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Connection connection = PinguDatabase.getConnection();

        Book bookToSave = new Book(5, "Sample book", "John Doe", 10, 0, "Fiction", 200);

        BookDAO bookDAO = new BookDAO(connection);

        try {
            bookDAO.createBook(bookToSave); // Create ignores id OK
            System.out.println("Book saved successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PinguDatabase.close();
    }
}