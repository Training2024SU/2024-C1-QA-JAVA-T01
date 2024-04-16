package com.sofkau;

import com.sofkau.integration.database.mysql.MySqlOperation;

import java.sql.SQLException;
import java.util.Scanner;
import static com.sofkau.integration.database.mysql.Constantes.*;
import static com.sofkau.logica.control.MetodosMain.implementarLogica;

public class Main {

    private static final String DATA_BASE_NAME = "mydb";
    private static final String  USER = "root";
    private static final String PASSWORD = "spike1712";
   // private static final String SELECT_ALL_FROM_LIBROS = String.format("select * from %s.publicacion", DATA_BASE_NAME);
    public static final MySqlOperation mySqlOperation = new MySqlOperation();

    public static void main(String[] args) throws SQLException {
        openConnection();
        implementarLogica();
        closeConnection();

    }

    private static int preguntarAlUsuario() {
        Scanner scanner = new Scanner(System.in);
        int cantidad;
        System.out.println(MENSAJE_USUARIO);
        cantidad = Integer.parseInt(scanner.nextLine());
        return cantidad;
    }


    public static void openConnection() {
        mySqlOperation.setServer(SERVER);
        mySqlOperation.setDataBaseName(DATA_BASE_NAME);
        mySqlOperation.setUser(USER);
        mySqlOperation.setPassword(PASSWORD);
    }

   /* public static void selectAllFromLibro() throws SQLException {
        mySqlOperation.setSqlStatement(SELECT_ALL_FROM_LIBROS);
        mySqlOperation.executeSqlStatement();
        mySqlOperation.printColumnValue();
        //System.out.println(mySqlOperation.getResulset().getMetaData());
    }*/

    public static void insertIntoLibro() {
        mySqlOperation.setSqlStatement(INSERT_LIBRO);
        mySqlOperation.executeSqlStatementVoid();

    }

    public static void insertIntoLibros(String insert) {
        mySqlOperation.setSqlStatement(insert);
        mySqlOperation.executeSqlStatementVoid();

    }

    public static void closeConnection() {
        mySqlOperation.close();
    }
}