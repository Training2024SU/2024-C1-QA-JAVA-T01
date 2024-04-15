package com.davidbonelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class PinguDatabase {
    private static final Logger logger = Logger.getLogger(PinguDatabase.class.getName());

    private static final String DB_URL = System.getProperty("DB_URL");
    private static final String DB_USER = System.getProperty("DB_USER");
    private static final String DB_PASSWORD = System.getProperty("DB_PASSWORD");
    private static Connection connection;

    private PinguDatabase() {
        throw new IllegalStateException("Utility class");
    }

    private static void startConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            logger.warning("Database connection error: " + e.getMessage());
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
