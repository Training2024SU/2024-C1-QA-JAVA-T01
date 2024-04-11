package com.davidbonelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.davidbonelo.Secrets.DB_PASSWORD;
import static com.davidbonelo.Secrets.DB_URL;
import static com.davidbonelo.Secrets.DB_USER;

public class PinguDatabase {
    private static Connection connection;

    private static void startConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//            connection.setAutoCommit(false);
        } catch (Exception e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            startConnection();
        }
        return connection;
    }

    public static void close() {
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
