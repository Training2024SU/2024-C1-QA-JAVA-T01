package com.sofka.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import static com.sofka.BibliotecaLaPinguinera.mySqlOperation;
import static com.sofka.constants.ConectorConstants.*;
import static com.sofka.constants.ConectorConstants.PASSWORD;

public class ControlStament {


    public static void ejecutarMostrarSQL(String insert) throws SQLException {
        mySqlOperation.setSqlStatement(insert);
        mySqlOperation.executeSqlStatement();
        mySqlOperation.printResulset();
        System.out.println();
    }

    public static void insertarLista(ArrayList<String> lista) {
        for (String elemento : lista) {
            insertIntoBd(elemento);
        }
    }

    public static void insertIntoBd(String insert) {
        mySqlOperation.setSqlStatement(insert);
        mySqlOperation.executeSqlStatementVoid();
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
