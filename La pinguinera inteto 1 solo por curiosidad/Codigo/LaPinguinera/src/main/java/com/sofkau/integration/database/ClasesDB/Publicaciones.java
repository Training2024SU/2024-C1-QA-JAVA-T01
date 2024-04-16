package com.sofkau.integration.database.ClasesDB;
import com.sofkau.integration.database.mysql.MySqlOperation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Publicaciones {
    private static String autor;
    private static String titulo;
    private static int  cantidadDisponible ;
    private static int cantidadPrestado ;
    private static int cantidadEjemplares ;

    public Publicaciones(){};

    public Publicaciones(String titulo, String autor, String cantidadDisponible) {
    }

    public static int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public int getCantidadPrestado() {
        return cantidadPrestado;
    }

    public void setCantidadPrestado(int cantidadPrestado) {
        this.cantidadPrestado = cantidadPrestado;
    }

    public static String getAutor() {
        return autor;
    }

    public static void setAutor(String autor) {
        Publicaciones.autor = autor;
    }

    public static String getTitulo() {
        return titulo;
    }

    public static void setTitulo(String titulo) {
        Publicaciones.titulo = titulo;
    }

    public static int getCantidadEjemplares() {
        return cantidadEjemplares;
    }

    public static void setCantidadEjemplares(int cantidadEjemplares) {
        Publicaciones.cantidadEjemplares = cantidadEjemplares;
    }


    private static final MySqlOperation mySqlOperation = new MySqlOperation();

    public static void insertIntoPublicacion(String titulo, String autor, String cantidadDisponible, String cantidadPrestado, String cantidadEjemplares) {

        mySqlOperation.setSqlStatement("INSERT INTO publicacion (titulo, autor, cantidadDisponible, cantidadPrestado, cantidadEjemplares) VALUES (?, ?, ?, ?, ?)");
        try {
            mySqlOperation.executeSqlStatementWithParameters(titulo, autor, cantidadDisponible, cantidadPrestado, cantidadEjemplares);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void actualizarPublicacion(String titulo, String autor, String cantidadDisponible, String cantidadPrestado, String cantidadEjemplares) {

        mySqlOperation.setSqlStatement("UPDATE publicacion SET autor = ?, cantidadDisponible = ?, cantidadPrestado = ?, cantidadEjemplares = ? WHERE titulo = ?");
        try {
            mySqlOperation.executeSqlStatementWithParameters(autor, cantidadDisponible, cantidadPrestado, cantidadEjemplares, titulo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void eliminarPublicacion(String titulo) {

        mySqlOperation.setSqlStatement("DELETE FROM publicacion WHERE titulo = ?");
        try {
            mySqlOperation.executeSqlStatementWithParameters(titulo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("publicacion eliminada con titulo "+ titulo + " de la base de datos");
    }

    public static void conseguirCantidadDisponibleCantidadPrestado(String titulo){
        mySqlOperation.setSqlStatement("SELECT cantidadDisponible, cantidadPrestado FROM publicacion WHERE titulo = ?");
        try {
            mySqlOperation.executeSqlStatementWithParametersDisponibilidad(titulo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void nuevaDisponibilidadPrestadoEntrega(int cantidad, String titulo){

             int nuevaDisponibilidad = cantidadDisponible - cantidad ;
             int nuevaPrestado = cantidadPrestado + cantidad;

        mySqlOperation.setSqlStatement("UPDATE publicacion SET cantidadDisponible = ?, cantidadPrestado = ? WHERE titulo = ?");
        try {
            mySqlOperation.executeSqlStatementWithParameters(nuevaDisponibilidad, nuevaPrestado, titulo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void nuevaDisponibilidadPrestadoPrestamo(int cantidad, String titulo){

        int nuevaDisponibilidad = cantidadDisponible + cantidad ;
        int nuevaPrestado = cantidadPrestado - cantidad;

        mySqlOperation.setSqlStatement("UPDATE publicacion SET cantidadDisponible = ?, cantidadPrestado = ? WHERE titulo = ?");
        try {
            mySqlOperation.executeSqlStatementWithParameters(nuevaDisponibilidad, nuevaPrestado, titulo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void consultarTodasPublicaciones() throws SQLException {
        mySqlOperation.setSqlStatement("SELECT * FROM publicacion");
        mySqlOperation.executeSqlStatement();
        mySqlOperation.printResulset();
    }

    public static void obtenerPublicacionesDisponibles() throws SQLException {
        Map<String, Integer> publicaciones = new HashMap<>();

        try {
            mySqlOperation.setSqlStatement("SELECT * FROM publicacion");
            ResultSet resultSet = mySqlOperation.executeSqlStatementReturnResultSet();

            while (resultSet.next()) {
                String titulo = resultSet.getString("titulo");
                int cantidadDisponible = resultSet.getInt("cantidadDisponible");
                publicaciones.put(titulo, cantidadDisponible);
            }

            System.out.println(publicaciones);

            Map<String, Integer> publicacionesFiltradas = publicaciones.entrySet().stream()
                    .filter(entry -> entry.getValue() > 0)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            publicacionesFiltradas.forEach((titulo, cantidadDisponible) -> System.out.println(titulo + " - " + cantidadDisponible));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
