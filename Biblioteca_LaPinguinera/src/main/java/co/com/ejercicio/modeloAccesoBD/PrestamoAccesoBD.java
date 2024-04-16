package co.com.ejercicio.modeloAccesoBD;

import co.com.ejercicio.modelo.Prestamo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static co.com.ejercicio.conexionBd.MapeoBD.MapearTablasBD.mapearResultSetAprestamo;
import static co.com.ejercicio.conexionBd.constantesCRUD.QueryConstante.*;
import static co.com.ejercicio.menu.constantesMenu.OperacionExitosaOFallida.OPERACION_EXITOSA;
import static co.com.ejercicio.menu.constantesMenu.OperacionExitosaOFallida.OPERACION_FALLIDA;

public class PrestamoAccesoBD {
    private Connection conexion;

    public PrestamoAccesoBD(Connection conexion) {
        this.conexion = conexion;
    }



    // Método para insertar un nuevo préstamo
    public void insertarPrestamo(Prestamo prestamo) throws SQLException {
        try (PreparedStatement stmt = conexion.prepareStatement(INSERT_PRESTAMO)) {
            stmt.setInt(1, prestamo.getIdPrestamo());
            stmt.setDate(2, prestamo.getFechaPrestamo());
            stmt.setDate(3, prestamo.getFechaDevolucion());
            stmt.setString(4, String.valueOf(prestamo.getEstado()));
            stmt.setString(5,prestamo.getUsuarioCorreo());
            stmt.setString(6,prestamo.getPublicacionTitulo());
            stmt.executeUpdate();
            System.out.println(OPERACION_EXITOSA);
        } catch (SQLException e){
            System.out.println(OPERACION_FALLIDA + e);
        }
    }

    public void actualizarPrestamo(Prestamo prestamo) throws SQLException {
        try (PreparedStatement statement = conexion.prepareStatement(UPDATE_PRESTAMO)) {
            statement.setDate(1, prestamo.getFechaPrestamo());
            statement.setDate(2, prestamo.getFechaDevolucion());
            statement.setString(3, prestamo.getEstado());
            statement.setString(4, prestamo.getUsuarioCorreo());
            statement.setString(5, prestamo.getPublicacionTitulo());
            statement.setInt(6, prestamo.getIdPrestamo()
            );

            statement.executeUpdate();
            System.out.println(OPERACION_EXITOSA);
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + (e));
        }
    }


    public void eliminarPrestamo(int idPrestamo) throws SQLException {
        try (PreparedStatement statement = conexion.prepareStatement(DELETE_FROM_PRESTAMO)) {
            statement.setInt(1, idPrestamo);
            statement.executeUpdate();
            System.out.println(OPERACION_EXITOSA);
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + e );
        }
    }

    public List<Prestamo> obtenerTodosLosPrestamos() throws SQLException {
        List<Prestamo> prestamos = new ArrayList<>();
        try {
            PreparedStatement statement = conexion.prepareStatement(SELECT_ALL_FROM_PRESTAMO);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                prestamos.add(mapearResultSetAprestamo(resultSet));
            }
            System.out.println(OPERACION_EXITOSA);

        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + e);
        }
        return prestamos;
    }
}

