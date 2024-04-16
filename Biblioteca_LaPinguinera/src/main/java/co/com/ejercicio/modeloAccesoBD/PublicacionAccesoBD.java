package co.com.ejercicio.modeloAccesoBD;

import co.com.ejercicio.interfaceCRUD.IPublicacionCRUD;
import co.com.ejercicio.modelo.Publicacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static co.com.ejercicio.conexionBd.MapeoBD.MapearTablasBD.mapearResultSetAPublicacion;
import static co.com.ejercicio.conexionBd.constantesCRUD.QueryConstante.*;
import static co.com.ejercicio.menu.constantesMenu.OperacionExitosaOFallida.OPERACION_EXITOSA;
import static co.com.ejercicio.menu.constantesMenu.OperacionExitosaOFallida.OPERACION_FALLIDA;

public class PublicacionAccesoBD implements IPublicacionCRUD {
    private static Connection conexion;

    public PublicacionAccesoBD(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarPublicacion(Publicacion publicacion) throws SQLException {
        try {
            PreparedStatement statement = conexion.prepareStatement(INSERT_PUBLICACIONES);
            statement.setString(1, publicacion.getTitulo());
            statement.setString(2, publicacion.getTipo());
            statement.setInt(3, publicacion.getNumeroPaginas());
            statement.setInt(4, publicacion.getCantidadEjemplares());
            statement.setInt(5, publicacion.getCantidadPrestado());
            statement.setInt(6, publicacion.getCantidadDisponible());
            statement.setString(7, publicacion.getNombreAutor());
            statement.executeUpdate();
            System.out.println(OPERACION_EXITOSA);
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + e);
        }
    }

    public List<Publicacion> obtenerTodasLasPublicaciones() throws SQLException {
        List<Publicacion> publicaciones = new ArrayList<>();
        try {
            PreparedStatement statement = conexion.prepareStatement(SELECT_ALL_FROM_PUBLICACIONES);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                publicaciones.add(mapearResultSetAPublicacion(resultSet));
            }
            System.out.println(OPERACION_EXITOSA);
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + e);
        }
        return publicaciones;
    }

    public List<Publicacion> obtenerPublicacionesDeUnAutor() throws SQLException {
        List<Publicacion> publicaciones = new ArrayList<>();
        try {
            PreparedStatement statement = conexion.prepareStatement(SELECT_ALL_AUTOR_FROM_PUBLICACIONES);
            statement.setString(1, Publicacion.getNombreAutor());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                publicaciones.add(mapearResultSetAPublicacion(resultSet));
            }
            System.out.println(OPERACION_EXITOSA);
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + e);
        }
        return publicaciones;
    }

    @Override
    public void actualizarPublicacion(Publicacion publicacion) throws SQLException {
        try (PreparedStatement statement = conexion.prepareStatement(UPDATE_PUBLICACIONES)) {
            statement.setString(1, publicacion.getTipo());
            statement.setInt(2, publicacion.getNumeroPaginas());
            statement.setInt(3, publicacion.getCantidadEjemplares());
            statement.setInt(4, publicacion.getCantidadPrestado());
            statement.setInt(5, publicacion.getCantidadDisponible());
            statement.setString(6, publicacion.getNombreAutor());
            statement.setString(7, publicacion.getTitulo());

            statement.executeUpdate();
            System.out.println(OPERACION_EXITOSA);
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + (e));
        }
    }

    public void eliminarPublicacion(String titulo) throws SQLException, RuntimeException {
        try (PreparedStatement statement = conexion.prepareStatement(DELETE_FROM_PUBLICACIONES)) {
            statement.setString(1, titulo);
            statement.executeUpdate();
            System.out.println(OPERACION_EXITOSA);
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + (e));
        }
    }

    public static List<Publicacion> obtenerPublicacionesPorAutor(String nombreAutor) throws SQLException {
        List<Publicacion> publicaciones = new ArrayList<>();

        try (PreparedStatement statement = conexion.prepareStatement(SELECT_ALL_AUTOR_FROM_PUBLICACIONES)) {
            statement.setString(1, nombreAutor);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Publicacion publicacion = new Publicacion(
                            resultSet.getString("titulo"),
                            resultSet.getString("tipo_publicacion"),
                            resultSet.getInt("numero_pagina"),
                            resultSet.getInt("cantidad_ejemplares"),
                            resultSet.getInt("cantidad_prestado"),
                            resultSet.getInt("cantidad_disponible"),
                            resultSet.getString("nombre_autor")
                    );
                    publicaciones.add(publicacion);
                }
            }
        }

        return publicaciones;
    }
}



