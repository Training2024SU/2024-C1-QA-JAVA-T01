package com.davidbonelo;

import com.davidbonelo.persistance.UserDAO;
import com.davidbonelo.services.UserService;
import com.davidbonelo.ui.MainMenu;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection connection = PinguDatabase.getConnection();

        new MainMenu(connection).menu();

        PinguDatabase.close();
    }
}