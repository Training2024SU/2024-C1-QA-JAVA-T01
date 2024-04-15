package org.moreno.cristian.servicios;

import org.moreno.cristian.modelos.Autor;
import org.moreno.cristian.modelos.Novela;

import org.moreno.cristian.modelos.enums.AreaConocimiento;

import org.moreno.cristian.modelos.enums.Genero;
import org.moreno.cristian.repositorios.RepositorioNovela;
import org.moreno.cristian.repositorios.RepositorioPublicacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ServicioNovela implements RepositorioNovela {

    private final RepositorioPublicacion servicioPublicacion;
    private final Connection conn;

    public ServicioNovela(RepositorioPublicacion servicioPublicacion, Connection conn) {
        this.servicioPublicacion = servicioPublicacion;
        this.conn = conn;
    }

    @Override
    public Optional<List<Novela>> todasNovelas() {
        String sqlConsulta = "SELECT n.id AS 'novela_id', p.titulo, n.edadLectura, n.genero, p.nEjemplares, " +
                "p.nPrestados, p.nDisponibles, p.autor_id, a.nombre AS 'nombre_autor' " +
                "FROM novela n " +
                "JOIN publicacion p ON n.id = p.id " +
                "JOIN autor a ON p.autor_id = a.id";

        return ejecutarQuery(sqlConsulta);
    }

    @Override
    public Optional<List<Novela>> todasPorAutor(String nombreAutor) {
        return todasNovelas()
                .map(novelas -> filtrarPorAutor(novelas, nombreAutor))
                .filter(novelas -> !novelas.isEmpty());
    }

    @Override
    public Optional<List<Novela>> todasDisponibles() {
        return todasNovelas()
                .map(novelas -> novelas.stream()
                        .filter(novela -> novela.getEjemplaresDisponibles() > 0)
                        .collect(Collectors.toList())
                )
                .filter(novelas -> !novelas.isEmpty());
    }

    @Override
    public Optional<List<Novela>> disponiblePorNombreNovela(String nombreNovela) {
        return todasNovelas()
                .map(novelas -> novelas.stream()
                        .filter(novela -> novela.getEjemplaresDisponibles() > 0)
                        .filter(novela -> novela.getTitulo().equals(nombreNovela))
                        .collect(Collectors.toList())
                )
                .filter(novelas -> !novelas.isEmpty());
    }

    @Override
    public Optional<List<Novela>> disponiblesPorAutor(String nombreAutor) {
        return todasNovelas()
                .map(novelas -> filtrarPorAutorYDisponibles(novelas, nombreAutor))
                .filter(novelas -> !novelas.isEmpty());
    }

    @Override
    public boolean guardarNovela(Novela nuevaNovela) {
        String sqlInsertNovela = "INSERT INTO novela (id, edadLectura, genero) VALUES (?, ?, ?)";
        return ejecutarActualizacion(sqlInsertNovela, nuevaNovela.getId(), nuevaNovela.getEdadLectura(), nuevaNovela.getGenero().toString());
    }

    @Override
    public boolean eliminarNovela(Novela novela) {
        String sqlEliminarNovela = "DELETE FROM novela WHERE id = ?";
        return ejecutarBorrado(sqlEliminarNovela, novela.getId()) && servicioPublicacion.eliminarPublicacion(novela.getId());
    }

    @Override
    public boolean actualizarNovela(Novela novela) {
        String sqlActualizarNovela = "UPDATE novela SET edadLectura = ?, genero = ? WHERE id = ?";
        return ejecutarActualizacion(sqlActualizarNovela, novela.getEdadLectura(), novela.getGenero().toString(), novela.getId())
                && servicioPublicacion.actualizarPublicacion(novela);
    }

    private Optional<List<Novela>> ejecutarQuery(String sql) {
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            List<Novela> novelas = new ArrayList<>();
            while (rs.next()) {
                Autor autor = new Autor(rs.getString("autor_id"), rs.getString("nombre_autor"));
                Novela novela = new Novela(
                        rs.getString("novela_id"),
                        rs.getString("titulo"),
                        rs.getInt("nEjemplares"),
                        rs.getInt("nPrestados"),
                        rs.getInt("nDisponibles"),
                        autor,
                        rs.getInt("edadLectura"),
                        Genero.valueOf(rs.getString("genero"))
                );
                novelas.add(novela);
            }

            return novelas.isEmpty() ? Optional.empty() : Optional.of(novelas);

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private List<Novela> filtrarPorAutor(List<Novela> novelas, String nombreAutor) {
        return novelas.stream()
                .filter(novela -> novela.getAutor().getNombre().equals(nombreAutor))
                .collect(Collectors.toList());
    }

    private List<Novela> filtrarPorAutorYDisponibles(List<Novela> novelas, String nombreAutor) {
        return novelas.stream()
                .filter(novela -> novela.getEjemplaresDisponibles() > 0)
                .filter(novela -> novela.getAutor().getNombre().equals(nombreAutor))
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
