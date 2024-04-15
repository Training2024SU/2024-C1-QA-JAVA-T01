package org.moreno.cristian.servicios;

import org.moreno.cristian.modelos.Autor;
import org.moreno.cristian.modelos.Libro;
import org.moreno.cristian.modelos.enums.AreaConocimiento;
import org.moreno.cristian.repositorios.RepositorioLibro;
import org.moreno.cristian.repositorios.RepositorioPublicacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ServicioLibro implements RepositorioLibro {

    private final RepositorioPublicacion servicioPublicacion;
    private final Connection conn;

    public ServicioLibro(RepositorioPublicacion servicioPublicacion, Connection conn) {
        this.servicioPublicacion = servicioPublicacion;
        this.conn = conn;
    }

    @Override
    public Optional<List<Libro>> todosLibros() {
        String sqlConsulta = "SELECT l.id AS 'libro_id', p.titulo, l.paginas, l.area, p.nEjemplares, p.nDisponibles, p.nPrestados, p.autor_id, a.nombre AS 'nombre_autor' " +
                "FROM libro l " +
                "JOIN publicacion p ON l.id = p.id " +
                "JOIN autor a ON p.autor_id = a.id";

        return ejecutarQuery(sqlConsulta);
    }

    @Override
    public Optional<List<Libro>> todosPorAutor(String nombreAutor) {
        return todosLibros()
                .map(libros -> filtrarPorAutor(libros, nombreAutor))
                .filter(libros -> !libros.isEmpty());
    }

    @Override
    public Optional<Libro> libroPorNombre(String nombre) {
        return todosLibros()
                .flatMap(libros -> libros.stream()
                        .filter(libro -> libro.getTitulo().equalsIgnoreCase(nombre))
                        .findFirst());
    }


    @Override
    public Optional<List<Libro>> todosDisponibles() {
        return todosLibros()
                .map(libros -> libros.stream()
                        .filter(libro -> libro.getEjemplaresDisponibles() > 0)
                        .collect(Collectors.toList())
                )
                .filter(libros -> !libros.isEmpty());
    }

    @Override
    public Optional<List<Libro>> disponiblePorNombreLibro(String nombreLibro) {
        return todosLibros()
                .map(libros -> libros.stream()
                        .filter(libro -> libro.getEjemplaresDisponibles() > 0)
                        .filter(libro -> libro.getTitulo().equals(nombreLibro))
                        .collect(Collectors.toList())
                )
                .filter(libros -> !libros.isEmpty());
    }

    @Override
    public Optional<List<Libro>> disponiblesPorAutor(String nombreAutor) {
        return todosLibros()
                .map(libros -> filtrarPorAutorYDisponibles(libros, nombreAutor))
                .filter(libros -> !libros.isEmpty());
    }

    @Override
    public boolean guardarLibro(Libro nuevoLibro) {
        String sqlInsertLibro = "INSERT INTO libro (id, paginas, area) VALUES (?, ?, ?)";
        if (libroPorNombre(nuevoLibro.getTitulo()).isPresent()) {
            return servicioPublicacion.guardarPublicacion(nuevoLibro)  &&
                    ejecutarActualizacion(sqlInsertLibro, nuevoLibro.getId(), nuevoLibro.getNumeroPaginas(), nuevoLibro.getAreaConocimiento().toString());
        }
        return false;
    }

    @Override
    public boolean eliminarLibro(String libroId) {
        String sqlEliminarLibro = "DELETE FROM libro WHERE id = ?";
        return ejecutarBorrado(sqlEliminarLibro, libroId) && servicioPublicacion.eliminarPublicacion(libroId);
    }

    @Override
    public boolean actualizarLibro(Libro libro) {
        String sqlActualizarLibro = "UPDATE libro SET paginas = ?, area = ? WHERE id = ?";
        return ejecutarActualizacion(sqlActualizarLibro, libro.getNumeroPaginas(), libro.getAreaConocimiento().toString(), libro.getId())
                && servicioPublicacion.actualizarPublicacion(libro);
    }

    private Optional<List<Libro>> ejecutarQuery(String sql) {
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            List<Libro> libros = new ArrayList<>();
            while (rs.next()) {
                Autor autor = new Autor(rs.getString("autor_id"), rs.getString("nombre_autor"));
                Libro libro = new Libro(
                        rs.getString("libro_id"),
                        rs.getString("titulo"),
                        rs.getInt("nEjemplares"),
                        rs.getInt("nPrestados"),
                        rs.getInt("nDisponibles"),
                        autor,
                        rs.getInt("paginas"),
                        AreaConocimiento.valueOf(rs.getString("area"))
                );
                libros.add(libro);
            }

            return libros.isEmpty() ? Optional.empty() : Optional.of(libros);

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private List<Libro> filtrarPorAutor(List<Libro> libros, String nombreAutor) {
        return libros.stream()
                .filter(libro -> libro.getAutor().getNombre().equals(nombreAutor))
                .collect(Collectors.toList());
    }

    private List<Libro> filtrarPorAutorYDisponibles(List<Libro> libros, String nombreAutor) {
        return libros.stream()
                .filter(libro -> libro.getEjemplaresDisponibles() > 0)
                .filter(libro -> libro.getAutor().getNombre().equals(nombreAutor))
                .collect(Collectors.toList());
    }

    private boolean ejecutarActualizacion(String sql, Object... params) {
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            setearParametros(pstmt, params);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean ejecutarBorrado(String sql, Object... params) {
        return ejecutarActualizacion(sql, params);
    }

    private void setearParametros(PreparedStatement pstmt, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            pstmt.setObject(i + 1, params[i]);
        }
    }
}
