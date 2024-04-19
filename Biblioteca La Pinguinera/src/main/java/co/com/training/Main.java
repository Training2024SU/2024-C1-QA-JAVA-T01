package co.com.training;

import co.com.training.integration.database.mysql.MySqlOperation;
import co.com.training.logica.control.MetodosMain;

import java.sql.SQLException;

public class Main {
    private static final String SERVER = "localhost";
    private static final String DATA_BASE_NAME = "biblioteca_pinguinera";
    private static final String USER = "root";
    private static final String PASSWORD = "Janet1976";
    private static final MySqlOperation mySqlOperation = new MySqlOperation();

    public static void main(String[] args) {
        try {
            openConnection();
            MetodosMain.insertarItems();
            closeConnection();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void openConnection() {
        mySqlOperation.setServer(SERVER);
        mySqlOperation.setDataBaseName(DATA_BASE_NAME);
        mySqlOperation.setUser(USER);
        mySqlOperation.setPassword(PASSWORD);
    }

    public static void closeConnection() {
        mySqlOperation.close();
    }
}
