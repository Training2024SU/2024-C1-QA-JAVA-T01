package org.moreno.cristian;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/barberia";
    private static final String USER = "root";
    private static final String PASSWORD = "0000";

    private static Connection connection;

    public static void main(String[] args) throws SQLException {
        System.out.println("Hello world!");
        connection = connect();
        connection.close();
    }

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection ready!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}