package org.moreno.cristian.servicios;

import org.moreno.cristian.modelos.Autor;
import org.moreno.cristian.modelos.Libro;
import org.moreno.cristian.modelos.Publicacion;
import org.moreno.cristian.repositorios.RepositorioAutor;
import org.moreno.cristian.repositorios.RepositorioPublicacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class ServicioPublicacion implements RepositorioPublicacion {

    private final Connection conn;
    private final RepositorioAutor servicioAutor;

    public ServicioPublicacion(Connection conn, RepositorioAutor servicioAutor) {
        this.conn = conn;
        this.servicioAutor = servicioAutor;
    }

    @Override
    public boolean guardarPublicacion(Publicacion nuevaPublicacion) {
        String sqlInsertPublicacion = "INSERT INTO publicacion (id, titulo, nEjemplares, nPrestados, autor_id) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmtPublicacion = conn.prepareStatement(sqlInsertPublicacion)) {
            pstmtPublicacion.setString(1, nuevaPublicacion.getId());
            pstmtPublicacion.setString(2, nuevaPublicacion.getTitulo());
            pstmtPublicacion.setInt(3, nuevaPublicacion.getTotalEjemplares());
            pstmtPublicacion.setInt(5, nuevaPublicacion.getEjemplaresPrestados());

            Autor autor = nuevaPublicacion.getAutor();

            if (servicioAutor.autorPorNombre(autor.getNombre()).isPresent()) {
                pstmtPublicacion.setString(6, autor.getId());
            } else {
                servicioAutor.guardarAutor(autor);
                pstmtPublicacion.setString(6, autor.getId());
            }

            int rowsAffected = pstmtPublicacion.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarPublicacion(String publicacionId) {
        String sqlEliminarPublicacion = "DELETE FROM publicacion WHERE id = ?";

        try (PreparedStatement pstmtPublicacion = conn.prepareStatement(sqlEliminarPublicacion)) {
            pstmtPublicacion.setString(1, publicacionId);

            int rowsAffected = pstmtPublicacion.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizarPublicacion(Publicacion publicacion) {
        String sqlActualizarPublicacion = "UPDATE publicacion SET titulo = ?, nEjemplares = ?, nPrestados = ?, autor_id = ? WHERE id = ?";

        try (PreparedStatement pstmtPublicacion = conn.prepareStatement(sqlActualizarPublicacion)) {
            pstmtPublicacion.setString(1, publicacion.getTitulo());
            pstmtPublicacion.setInt(2, publicacion.getTotalEjemplares());
            pstmtPublicacion.setInt(3, publicacion.getEjemplaresPrestados());
            pstmtPublicacion.setString(4, publicacion.getAutor().getId());
            pstmtPublicacion.setString(5, publicacion.getId());

            int rowsAffected = pstmtPublicacion.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
            return false;
        }
    }
}
