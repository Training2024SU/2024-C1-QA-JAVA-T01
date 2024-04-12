package org.moreno.cristian.servicios;

import org.moreno.cristian.modelos.Autor;
import org.moreno.cristian.modelos.Libro;
import org.moreno.cristian.modelos.Usuario;
import org.moreno.cristian.modelos.enums.AreaConocimiento;
import org.moreno.cristian.modelos.enums.Rol;
import org.moreno.cristian.repositorios.RepositorioLibro;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ServicioLibro implements RepositorioLibro {

    Connection conn;

    public ServicioLibro () {
        try {
            conn = ConexionBD.obtenerConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<ArrayList<Libro>> todosLibros() {

        String sqlConsulta = "SELECT l.id AS 'libro_id', p.titulo, l.paginas, l.area, p.nEjemplares, p.nDisponibles, p.nPrestados, p.autor_id, a.nombre AS 'nombre_autor' " +
                "FROM libro l " +
                "JOIN publicacion p ON l.id = p.id " +
                "JOIN autor a ON p.autor_id = a.id";

        ArrayList<Libro> libros = new ArrayList<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sqlConsulta);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Autor autor = new Autor(rs.getString("autor_id"), rs.getString("nombre_autor"));
                Libro libro = new Libro(
                        rs.getString("libro_id"),
                        rs.getString("titulo"),
                        rs.getInt("nEjemplares"),
                        rs.getInt("nDisponibles"),
                        rs.getInt("nPrestados"),
                        autor,
                        rs.getInt("paginas"),
                        AreaConocimiento.valueOf(rs.getString("area"))
                );
                libros.add(libro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!libros.isEmpty()) {
            return Optional.of(libros);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<ArrayList<Libro>> todosPorAutor(String nombreAutor) {

        ArrayList<Libro> libros = new ArrayList<>();
        String sqlConsulta = "SELECT l.id AS 'libro_id', p.titulo, l.paginas, l.area, p.nEjemplares, p.nDisponibles, p.nPrestados, p.autor_id, a.nombre AS 'nombre_autor' " +
                "FROM libro l " +
                "JOIN publicacion p ON l.id = p.id " +
                "JOIN autor a ON p.autor_id = a.id " +
                "WHERE a.nombre = ?";
        try {

            PreparedStatement pstmt = conn.prepareStatement(sqlConsulta);
            pstmt.setString(1, nombreAutor);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Autor autor = new Autor(rs.getString("autor_id"), rs.getString("nombre_autor"));
                Libro libro = new Libro(
                        rs.getString("libro_id"),
                        rs.getString("titulo"),
                        rs.getInt("nEjemplares"),
                        rs.getInt("nDisponibles"),
                        rs.getInt("nPrestados"),
                        autor,
                        rs.getInt("paginas"),
                        AreaConocimiento.valueOf(rs.getString("area"))
                );
                libros.add(libro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!libros.isEmpty()) {
            return Optional.of(libros);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<ArrayList<Libro>> todosDisponibles() {
        ArrayList<Libro> libros = new ArrayList<>();
        String sqlConsulta = "SELECT l.id AS 'libro_id', p.titulo, l.paginas, l.area, p.nEjemplares, p.nDisponibles, p.nPrestados, p.autor_id, a.nombre AS 'nombre_autor' " +
                "FROM libro l " +
                "JOIN publicacion p ON l.id = p.id " +
                "JOIN autor a ON p.autor_id = a.id " +
                "WHERE nDisponibles > 0";
        try {

            PreparedStatement pstmt = conn.prepareStatement(sqlConsulta);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Autor autor = new Autor(rs.getString("autor_id"), rs.getString("nombre_autor"));
                Libro libro = new Libro(
                        rs.getString("libro_id"),
                        rs.getString("titulo"),
                        rs.getInt("nEjemplares"),
                        rs.getInt("nDisponibles"),
                        rs.getInt("nPrestados"),
                        autor,
                        rs.getInt("paginas"),
                        AreaConocimiento.valueOf(rs.getString("area"))
                );
                libros.add(libro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!libros.isEmpty()) {
            return Optional.of(libros);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Libro> disponiblePorNombre(String nombreLibro) {

        Optional<Libro> optionalLibro = Optional.empty();

        String sqlConsulta = "SELECT l.id AS 'libro_id', p.titulo, l.paginas, l.area, p.nEjemplares, p.nDisponibles, p.nPrestados, p.autor_id, a.nombre AS 'nombre_autor' " +
                "FROM libro l " +
                "JOIN publicacion p ON l.id = p.id " +
                "JOIN autor a ON p.autor_id = a.id " +
                "WHERE nDisponibles > 0 " +
                "AND p.titulo = ?";
        try {

            PreparedStatement pstmt = conn.prepareStatement(sqlConsulta);
            pstmt.setString(1, nombreLibro);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Autor autor = new Autor(rs.getString("autor_id"), rs.getString("nombre_autor"));
                Libro libro = new Libro(
                        rs.getString("libro_id"),
                        rs.getString("titulo"),
                        rs.getInt("nEjemplares"),
                        rs.getInt("nDisponibles"),
                        rs.getInt("nPrestados"),
                        autor,
                        rs.getInt("paginas"),
                        AreaConocimiento.valueOf(rs.getString("area"))
                );
                optionalLibro = Optional.of(libro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return optionalLibro;
    }

    @Override
    public Optional<ArrayList<Libro>> disponiblesPorAutor(String nombreAutor) {
        ArrayList<Libro> libros = new ArrayList<>();
        String sqlConsulta = "SELECT l.id AS 'libro_id', p.titulo, l.paginas, l.area, p.nEjemplares, p.nDisponibles, p.nPrestados, p.autor_id, a.nombre AS 'nombre_autor' " +
                "FROM libro l " +
                "JOIN publicacion p ON l.id = p.id " +
                "JOIN autor a ON p.autor_id = a.id " +
                "WHERE nDisponibles > 0 " +
                "AND a.nombre = ?";
        try {

            PreparedStatement pstmt = conn.prepareStatement(sqlConsulta);
            pstmt.setString(1, nombreAutor);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Autor autor = new Autor(rs.getString("autor_id"), rs.getString("nombre_autor"));
                Libro libro = new Libro(
                        rs.getString("libro_id"),
                        rs.getString("titulo"),
                        rs.getInt("nEjemplares"),
                        rs.getInt("nDisponibles"),
                        rs.getInt("nPrestados"),
                        autor,
                        rs.getInt("paginas"),
                        AreaConocimiento.valueOf(rs.getString("area"))
                );
                libros.add(libro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!libros.isEmpty()) {
            return Optional.of(libros);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Libro> guardarLibro(Libro nuevoLibro) {
        String sqlInsertLibro = "INSERT INTO libro (id, paginas, area) VALUES (?, ?, ?)";
        String sqlInsertPublicacion = "INSERT INTO publicacion (id, titulo, nEjemplares, nDisponibles, nPrestados, autor_id) VALUES (?, ?, ?, ?, ?, ?)";

        try {

            PreparedStatement pstmtLibro = conn.prepareStatement(sqlInsertLibro);
            PreparedStatement pstmtPublicacion = conn.prepareStatement(sqlInsertPublicacion);
            // Insertar en tabla libro
            pstmtLibro.setString(1, nuevoLibro.getId());
            pstmtLibro.setInt(2, nuevoLibro.getNumeroPaginas());
            pstmtLibro.setString(3, nuevoLibro.getAreaConocimiento().toString());
            pstmtLibro.executeUpdate();

            // Insertar en tabla publicacion
            pstmtPublicacion.setString(1, nuevoLibro.getId());
            pstmtPublicacion.setString(2, nuevoLibro.getTitulo());
            pstmtPublicacion.setInt(3, nuevoLibro.getTotalEjemplares());
            pstmtPublicacion.setInt(4, nuevoLibro.getEjemplaresDisponibles());
            pstmtPublicacion.setInt(5, nuevoLibro.getEjemplaresPrestados());
            pstmtPublicacion.setString(6, nuevoLibro.getAutor().getId());
            pstmtPublicacion.executeUpdate();

            return Optional.of(nuevoLibro);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Libro> eliminarLibro(Libro libro) {
        String sqlEliminarPublicacion = "DELETE FROM publicacion WHERE id = ?";
        String sqlEliminarLibro = "DELETE FROM libro WHERE id = ?";

        try (PreparedStatement pstmtPublicacion = conn.prepareStatement(sqlEliminarPublicacion);
             PreparedStatement pstmtLibro = conn.prepareStatement(sqlEliminarLibro)) {

            // Eliminar de tabla publicacion
            pstmtPublicacion.setString(1, libro.getId());
            int rowsAffectedPublicacion = pstmtPublicacion.executeUpdate();

            // Eliminar de tabla libro
            pstmtLibro.setString(1, libro.getId());
            int rowsAffectedLibro = pstmtLibro.executeUpdate();

            if (rowsAffectedPublicacion > 0 && rowsAffectedLibro > 0) {
                return Optional.of(libro);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Libro> actualizarLibro(Libro libro) {
        String sqlActualizarPublicacion = "UPDATE publicacion SET titulo = ?, nEjemplares = ?, nDisponibles = ?, nPrestados = ?, autor_id = ? WHERE id = ?";
        String sqlActualizarLibro = "UPDATE libro SET paginas = ?, area = ? WHERE id = ?";

        try (PreparedStatement pstmtPublicacion = conn.prepareStatement(sqlActualizarPublicacion);
             PreparedStatement pstmtLibro = conn.prepareStatement(sqlActualizarLibro)) {

            // Actualizar tabla publicacion
            pstmtPublicacion.setString(1, libro.getTitulo());
            pstmtPublicacion.setInt(2, libro.getTotalEjemplares());
            pstmtPublicacion.setInt(3, libro.getEjemplaresDisponibles());
            pstmtPublicacion.setInt(4, libro.getEjemplaresPrestados());
            pstmtPublicacion.setString(5, libro.getAutor().getId());
            pstmtPublicacion.setString(6, libro.getId());
            pstmtPublicacion.executeUpdate();

            // Actualizar tabla libro
            pstmtLibro.setInt(1, libro.getNumeroPaginas());
            pstmtLibro.setString(2, libro.getAreaConocimiento().toString());
            pstmtLibro.setString(3, libro.getId());
            pstmtLibro.executeUpdate();

            return Optional.of(libro);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

}
