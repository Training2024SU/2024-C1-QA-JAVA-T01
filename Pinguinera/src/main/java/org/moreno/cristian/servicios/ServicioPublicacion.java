package org.moreno.cristian.servicios;

import org.moreno.cristian.modelos.Libro;
import org.moreno.cristian.repositorios.RepositorioPublicacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class ServicioPublicacion implements RepositorioPublicacion {

    private final Connection conn;

    public ServicioPublicacion(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean guardarPublicacion(Libro nuevoLibro) {
        String sqlInsertPublicacion = "INSERT INTO publicacion (id, titulo, nEjemplares, nDisponibles, nPrestados, autor_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmtPublicacion = conn.prepareStatement(sqlInsertPublicacion)) {
            pstmtPublicacion.setString(1, nuevoLibro.getId());
            pstmtPublicacion.setString(2, nuevoLibro.getTitulo());
            pstmtPublicacion.setInt(3, nuevoLibro.getTotalEjemplares());
            pstmtPublicacion.setInt(4, nuevoLibro.getEjemplaresDisponibles());
            pstmtPublicacion.setInt(5, nuevoLibro.getEjemplaresPrestados());
            pstmtPublicacion.setString(6, nuevoLibro.getAutor().getId());

            int rowsAffected = pstmtPublicacion.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarPublicacion(Libro libro) {
        String sqlEliminarPublicacion = "DELETE FROM publicacion WHERE id = ?";

        try (PreparedStatement pstmtPublicacion = conn.prepareStatement(sqlEliminarPublicacion)) {
            pstmtPublicacion.setString(1, libro.getId());

            int rowsAffected = pstmtPublicacion.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizarPublicacion(Libro libro) {
        String sqlActualizarPublicacion = "UPDATE publicacion SET titulo = ?, nEjemplares = ?, nDisponibles = ?, nPrestados = ?, autor_id = ? WHERE id = ?";

        try (PreparedStatement pstmtPublicacion = conn.prepareStatement(sqlActualizarPublicacion)) {
            pstmtPublicacion.setString(1, libro.getTitulo());
            pstmtPublicacion.setInt(2, libro.getTotalEjemplares());
            pstmtPublicacion.setInt(3, libro.getEjemplaresDisponibles());
            pstmtPublicacion.setInt(4, libro.getEjemplaresPrestados());
            pstmtPublicacion.setString(5, libro.getAutor().getId());
            pstmtPublicacion.setString(6, libro.getId());

            int rowsAffected = pstmtPublicacion.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
            return false;
        }
    }
}
