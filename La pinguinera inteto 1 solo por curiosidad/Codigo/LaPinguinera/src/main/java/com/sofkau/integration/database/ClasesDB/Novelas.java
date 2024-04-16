package com.sofkau.integration.database.ClasesDB;

import com.sofkau.integration.database.mysql.MySqlOperation;

import java.sql.SQLException;

public class Novelas {

    private static final MySqlOperation mySqlOperation = new MySqlOperation();

    public static void insertNovelaPublicacion() {


        String titulo = "Alicia 4";
        String autor = "Lewis Carroll";
        String cantidadDisponible = "5";
        String cantidadPrestado = "20";
        String cantidadEjemplares = "25";
        String edadLecturaSugerida = "16";
        String genero = "Fantasia";

        Publicaciones.insertIntoPublicacion(titulo, autor, cantidadDisponible, cantidadPrestado, cantidadEjemplares);
        insertIntoNovela(titulo, edadLecturaSugerida, genero);
    }

    public static void insertIntoNovela(String publicacion_titulo, String edadLecturaSugerida, String genero) {

        mySqlOperation.setSqlStatement("INSERT INTO novelas (publicacion_titulo, edadLecturaSugerida, genero) VALUES (?, ?, ?)");
        try {
            mySqlOperation.executeSqlStatementWithParameters(publicacion_titulo, edadLecturaSugerida, genero);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void consultarTodasNovelas() throws SQLException {
        mySqlOperation.setSqlStatement("SELECT * FROM lapinguinera.vista_publicacion_novela;");
        mySqlOperation.executeSqlStatement();
        mySqlOperation.printResulset();
    }

    public static void ActualizarNovelaPublicacion(String titulo) {


        String autor = "Lewis Carroll";
        String cantidadDisponible = "50";
        String cantidadPrestado = "50";
        String cantidadEjemplares = "100";
        String edadLecturaSugerida = "14";
        String genero = "Fantasia";

        Publicaciones.actualizarPublicacion(titulo, autor, cantidadDisponible, cantidadPrestado, cantidadEjemplares);
        actualizarNovela(titulo, edadLecturaSugerida, genero);
    }

    public static void actualizarNovela(String publicacion_titulo, String edadLecturaSugerida, String genero) {

        mySqlOperation.setSqlStatement("UPDATE novelas SET edadLecturaSugerida = ?, genero = ? WHERE publicacion_titulo = ?");
        try {
            mySqlOperation.executeSqlStatementWithParameters(edadLecturaSugerida, genero, publicacion_titulo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("novela actualizada con titulo "+ publicacion_titulo + " de la base de datos");
    }

    public static void EliminarNovelaPublicacion(String titulo) {

        Publicaciones.eliminarPublicacion(titulo);
        eliminarNovela(titulo);
    }

    public static void eliminarNovela(String titulo) {

        mySqlOperation.setSqlStatement("DELETE FROM novelas WHERE publicacion_titulo = ?");
        try {
            mySqlOperation.executeSqlStatementWithParameters(titulo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("novela eliminado con titulo "+ titulo + " de la base de datos");

    }
    public static void consultarNovela(String autor) throws SQLException {

        mySqlOperation.setSqlStatement("SELECT * FROM novelas " +
                "INNER JOIN publicacion ON novelas.publicacion_titulo = publicacion.titulo " +
                "WHERE publicacion.autor = ?");
        try {
            mySqlOperation.executeSqlStatementWithParametersConsult(autor);
            mySqlOperation.printResulset();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
