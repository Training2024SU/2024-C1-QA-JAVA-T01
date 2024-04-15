package com.davidbonelo;

import com.davidbonelo.ui.MainMenu;

import java.sql.Connection;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        // setup variables from the .env file to System properties
        Environment.setup();

        Connection connection = PinguDatabase.getConnection();

        try {
            new MainMenu(connection).menu();
        } catch (Exception e) {
            logger.severe("General error, please contact the developer with the following error " + "info: " + e.getMessage());
        }

        PinguDatabase.close();
    }
}