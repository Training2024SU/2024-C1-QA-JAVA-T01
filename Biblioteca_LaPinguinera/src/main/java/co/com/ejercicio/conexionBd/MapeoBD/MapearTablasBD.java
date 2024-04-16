package co.com.ejercicio.conexionBd.MapeoBD;

import co.com.ejercicio.modelo.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapearTablasBD {

    // Mapeo Publicacion
    public static Publicacion mapearResultSetAPublicacion(ResultSet resultSet) throws SQLException {
        try {
            return new Publicacion(
                    resultSet.getString("titulo"),
                    resultSet.getString("tipo_publicacion"),
                    resultSet.getInt("numero_pagina"),
                    resultSet.getInt("cantidad_ejemplares"),
                    resultSet.getInt("cantidad_prestado"),
                    resultSet.getInt("cantidad_disponible"),
                    resultSet.getString("nombre_autor")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // Mapeo Usuario
    public static Usuario mapearResultSetAUsuario(ResultSet resultSet) throws SQLException {
        try {
            return new Usuario(
                    resultSet.getString("nombre"), resultSet.getString("correo"),
                    resultSet.getString("contrasenia")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Prestamo mapearResultSetAprestamo(ResultSet resultSet) throws SQLException {
        try {
            return new Prestamo(
                    resultSet.getInt("idPrestamo"),
                    resultSet.getDate("fecha_prestamo"),
                    resultSet.getDate("fecha_devolucion"),
                    resultSet.getString("Estado"),
                    resultSet.getString("usuario_correo"),
                    resultSet.getString("publicacion_titulo")

            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static Empleado mapearResultSetAEmpleado(ResultSet resultSet) throws SQLException {
        try {
            return new Empleado(
                    resultSet.getInt("idEmpleado"),
                    resultSet.getString("nombre"),
                    resultSet.getString("correo"),
                    resultSet.getString("contrasenia"),
                    resultSet.getString("rol")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
