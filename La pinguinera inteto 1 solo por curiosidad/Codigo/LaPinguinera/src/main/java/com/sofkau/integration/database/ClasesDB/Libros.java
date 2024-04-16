package com.sofkau.integration.database.ClasesDB;
import com.sofkau.integration.database.mysql.MySqlOperation;
import com.sofkau.integration.database.mysql.MySqlConstants;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Libros {

    private static final MySqlOperation mySqlOperation = new MySqlOperation();


    public Libros() {
    }

    public static void insertLibroPublicacion() {


        String titulo = "Alicia y las reliquias de la muerte";
        String autor = "Lewis Carroll";
        String cantidadDisponible = "0";
        String cantidadPrestado = "100";
        String cantidadEjemplares = "100";
        String numeroPaginas = "300";
        String areaConocimiento = "Fantasia";

        Publicaciones.insertIntoPublicacion(titulo, autor, cantidadDisponible, cantidadPrestado, cantidadEjemplares);
        insertIntoLibro(titulo, numeroPaginas, areaConocimiento);
    }

    public static void insertIntoLibro(String publicacion_titulo, String numeroPaginas, String areaConocimiento) {

        mySqlOperation.setSqlStatement("INSERT INTO libros (publicacion_titulo, numeroPaginas, areaConocimiento) VALUES (?, ?, ?)");
        try {
            mySqlOperation.executeSqlStatementWithParameters(publicacion_titulo, numeroPaginas, areaConocimiento);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void consultarTodosLibros() throws SQLException {
        mySqlOperation.setSqlStatement("SELECT * FROM lapinguinera.vista_publicacion_libro");
        mySqlOperation.executeSqlStatement();
        mySqlOperation.printResulset();
    }


    public static void ActualizarLibroPublicacion(String titulo) {


        String autor = "Lewis Carroll";
        String cantidadDisponible = "50";
        String cantidadPrestado = "50";
        String cantidadEjemplares = "100";
        String numeroPaginas = "300";
        String areaConocimiento = "Fantasia";

        Publicaciones.actualizarPublicacion(titulo, autor, cantidadDisponible, cantidadPrestado, cantidadEjemplares);
        actualizarLibro(titulo, numeroPaginas, areaConocimiento);
    }

    public static void actualizarLibro(String publicacion_titulo, String numeroPaginas, String areaConocimiento) {

        mySqlOperation.setSqlStatement("UPDATE libros SET numeroPaginas = ?, areaConocimiento = ? WHERE publicacion_titulo = ?");
        try {
            mySqlOperation.executeSqlStatementWithParameters(numeroPaginas, areaConocimiento, publicacion_titulo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("libro actualizado con titulo "+ publicacion_titulo + " de la base de datos");
    }

    public static void EliminarLibroPublicacion(String titulo) {

        Publicaciones.eliminarPublicacion(titulo);
        eliminarLibro(titulo);
    }

    public static void eliminarLibro(String titulo) {

        mySqlOperation.setSqlStatement("DELETE FROM libros WHERE publicacion_titulo = ?");
        try {
            mySqlOperation.executeSqlStatementWithParameters(titulo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("libro eliminado con titulo "+ titulo + " de la base de datos");

    }

    public static void consultarLibro(String autor) throws SQLException {

        mySqlOperation.setSqlStatement("SELECT * FROM libros " +
                "INNER JOIN publicacion ON libros.publicacion_titulo = publicacion.titulo " +
                "WHERE publicacion.autor = ?");
        try {
            mySqlOperation.executeSqlStatementWithParametersConsult(autor);
            mySqlOperation.printResulset();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
