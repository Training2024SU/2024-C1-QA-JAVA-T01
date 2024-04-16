package com.sofkau.logica.publicacion;

import com.github.javafaker.Faker;
import com.sofkau.model.Prestamo;
import com.sofkau.model.Publicacion;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.sofkau.Main.mySqlOperation;
import static com.sofkau.integration.database.mysql.Constantes.INSERT_LIBRO;

public class CrudLibro {

    private static final String SELECT_LIBROS_BY_AUTOR = "select * from mydb.publicacion where autor = '%s';";

    private static final String SELECT_LIBROS_BY_TITULO = "select * from mydb.publicacion where titulo = '%s';";
    private static final String UPTDATE_LIBRO_ESTADO = "update mydb.publicacion SET cant_ejemplares = %s, cant_prestados = %s where Titulo = '%s';";

    private static final String DELETE_LIBRO_BY_TITULO = "delete from mydb.publicacion where Titulo = '%s'";

    private static final String INSERT_LIBRO = "insert into mydb.publicacion (Titulo, tipo_publicacion, autor, num_paginas, cant_ejemplares, cant_prestados) values('%s', '%s', '%s', '%s', '%s', '%s');";


    public static List<Publicacion> getAllPublicacionesByAutor(String autor) throws SQLException {
        List<Publicacion> publicaciones = new ArrayList<>();
        try {
            mySqlOperation.setSqlStatement(String.format(SELECT_LIBROS_BY_AUTOR, autor));
            mySqlOperation.executeSqlStatement();
            ResultSet resultSet = mySqlOperation.getResulset();
            while (resultSet.next()) {
                Publicacion publicacion = new Publicacion();
                publicacion.setTitulo(resultSet.getString(1));
                publicacion.setTipo(resultSet.getString(2));
                publicacion.setAutor(resultSet.getString(3));
                publicacion.setNumeroPaginas(Integer.parseInt(resultSet.getString(4)));
                publicacion.setCantidadEjemplares(Integer.parseInt(resultSet.getString(5)));
                publicacion.setCantidadPrestado(Integer.parseInt(resultSet.getString(6)));
                publicacion.setCantidadDisponible(Integer.parseInt(resultSet.getString(7)));
                publicaciones.add(publicacion);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener prestamo: " + e.getMessage());
        }
        return publicaciones;
    }

    public static Publicacion findPublicacionByTitulo(String titulo) throws SQLException {
        Publicacion publicacion = new Publicacion();
        try {
            mySqlOperation.setSqlStatement(String.format(SELECT_LIBROS_BY_TITULO, titulo));
            mySqlOperation.executeSqlStatement();
            ResultSet resultSet = mySqlOperation.getResulset();
            while (resultSet.next()) {
                publicacion.setTitulo(resultSet.getString(1));
                publicacion.setTipo(resultSet.getString(2));
                publicacion.setAutor(resultSet.getString(3));
                publicacion.setNumeroPaginas(Integer.parseInt(resultSet.getString(4)));
                publicacion.setCantidadEjemplares(Integer.parseInt(resultSet.getString(5)));
                publicacion.setCantidadPrestado(Integer.parseInt(resultSet.getString(6)));
                publicacion.setCantidadDisponible(Integer.parseInt(resultSet.getString(7)));


            }
        } catch (SQLException e) {
            System.out.println("Error al obtener prestamo: " + e.getMessage());
        }
        return publicacion;
    }

    public static void updatePublicacionByTitulo(int cantidadEjemplares, int cantidadPrestados, String titulo) {
        mySqlOperation.setSqlStatement(String.format(UPTDATE_LIBRO_ESTADO, cantidadEjemplares, cantidadPrestados, titulo));
        mySqlOperation.executeSqlStatementVoid();
    }

    public static void deletePublicacion(String titulo) {
        mySqlOperation.setSqlStatement(String.format(DELETE_LIBRO_BY_TITULO, titulo));
        mySqlOperation.executeSqlStatementVoid();


    }

    public static void createPublicacion(Publicacion publicacion) {
        mySqlOperation.setSqlStatement(String.format(INSERT_LIBRO, publicacion.getTitulo(), publicacion.getTipo(), publicacion.getAutor(), publicacion.getNumeroPaginas(), publicacion.getCantidadEjemplares(), publicacion.getCantidadPrestado()));
        mySqlOperation.executeSqlStatementVoid();


    }
}
