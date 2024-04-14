package com.sofkau;

import com.sofkau.integration.database.ConexionDatabase;
import com.sofkau.integration.database.mysql.MySqlOperation;

import java.sql.SQLException;
import java.util.Scanner;
import static com.sofkau.integration.database.mysql.Constantes.*;
import static com.sofkau.logica.control.MetodosMain.implementarLogica;
import static com.sofkau.logica.publicacion.CrudLibro.crearLibro;
import static com.sofkau.logica.publicacion.CrudLibro.crearLibro;
public class Main {

    public static void main(String[] args) throws SQLException {

        //Se abre la conexion
        ConexionDatabase.openConnection();


    }

    private static int preguntarAlUsuario() {
        Scanner scanner = new Scanner(System.in);
        int cantidad;
        System.out.println(MENSAJE_USUARIO);
        cantidad = Integer.parseInt(scanner.nextLine());
        return cantidad;
    }



/*    private static void insertarLibrosEnBd(int cantidadLibros) {
        for (int i = 0; i < cantidadLibros; i++) {
            insertIntoLibros(crearLibro());
        }
    }*/

/*
    public static void openConnection() {
        mySqlOperation.setServer(SERVER);
        mySqlOperation.setDataBaseName(DATA_BASE_NAME);
        mySqlOperation.setUser(USER);
        mySqlOperation.setPassword(PASSWORD);
    }

    public static void selectAllFromLibro() throws SQLException {
        mySqlOperation.setSqlStatement(SELECT_ALL_FROM_LIBROS);
        mySqlOperation.executeSqlStatement();
        mySqlOperation.printColumnValue();
        //System.out.println(mySqlOperation.getResulset().getMetaData());
    }

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
    }*/
}