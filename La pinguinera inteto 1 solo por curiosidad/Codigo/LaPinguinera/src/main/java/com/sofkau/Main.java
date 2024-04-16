package com.sofkau;
import com.sofkau.integration.database.DBConnection;
import com.sofkau.integration.database.mysql.MySqlOperation;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        DBConnection.ConnectDB();
    }
}