package co.com.biblioteca.DAO;

import co.com.biblioteca.modelo.Libro;
import co.com.biblioteca.mysql.MySQLDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static co.com.biblioteca.mysql.properties.*;

public class LibroDAO {
    private static final String SELECT_LIBRO_POR_ID = "SELECT * FROM libro WHERE id=?";
    private static final String INSERT_LIBRO = "INSERT INTO libro (titulo, autor, area_conocimiento, numero_paginas, cantidad_ejemplares, cantidad_prestados, cantidad_disponible) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_LIBRO = "UPDATE libro SET titulo=?, autor=?, area_conocimiento=?, numero_paginas=?, cantidad_ejemplares=?, cantidad_prestados=?, cantidad_disponible=? WHERE id=?";
    private static final String DELETE_LIBRO_POR_ID = "DELETE FROM libro WHERE id=?";
    private static final String SELECT_TODOS_LIBROS = "SELECT * FROM libro";
    private static final String BUSCAR_LIBROS = "SELECT * FROM libro WHERE titulo LIKE ? OR autor LIKE ?";
    private static final String query = "SELECT * FROM libros WHERE id = ?";

    private static Connection conexion;

    public LibroDAO() {
        this.conexion = MySQLDB.obtenerConexion();
    }

    public Libro obtenerLibroPorId(int id) {
        try (PreparedStatement statement = conexion.prepareStatement(SELECT_LIBRO_POR_ID)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return crearLibroDesdeResultSet(rs);
                }
            }
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
        return null;
    }

    public void agregarLibro(Libro libro) {
        try (PreparedStatement statement = conexion.prepareStatement(INSERT_LIBRO)) {
            prepararStatementParaLibro(statement, libro);
            statement.executeUpdate();
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
    }

    public void actualizarLibro(Libro libro) {
        try (PreparedStatement statement = conexion.prepareStatement(UPDATE_LIBRO)) {
            prepararStatementParaLibro(statement, libro);
            statement.setInt(8, libro.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
    }

    public void eliminarLibroPorId(int id) {
        try (PreparedStatement statement = conexion.prepareStatement(DELETE_LIBRO_POR_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
    }

    public List<Libro> obtenerTodosLosLibros() {
        List<Libro> libros = new ArrayList<>();
        try (PreparedStatement statement = conexion.prepareStatement(SELECT_TODOS_LIBROS); ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                libros.add(crearLibroDesdeResultSet(rs));
            }
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
        return libros;
    }

    public static Libro obtenerLibro(int id) {
        Libro libro = null;

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    libro = new Libro();
                    libro.setId(rs.getInt("id"));
                    libro.setTitulo(rs.getString("titulo"));
                    libro.setAutor(rs.getString("autor"));
                    // Setear otros atributos...
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return libro;
    }

    public List<Libro> buscarLibros(String criterioBusqueda) {
        List<Libro> librosEncontrados = new ArrayList<>();
        try (PreparedStatement statement = conexion.prepareStatement(BUSCAR_LIBROS)) {
            String criterio = "%" + criterioBusqueda + "%";
            statement.setString(1, criterio);
            statement.setString(2, criterio);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Libro libro = crearLibroDesdeResultSet(resultSet);
                    librosEncontrados.add(libro);
                }
            }
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
        return librosEncontrados;
    }

    private void prepararStatementParaLibro(PreparedStatement statement, Libro libro) throws SQLException {
        statement.setString(1, libro.getTitulo());
        statement.setString(2, libro.getAutor());
        statement.setString(3, libro.getAreaConocimiento());
        statement.setInt(4, libro.getNumeroPaginas());
        statement.setInt(5, libro.getCantidadEjemplares());
        statement.setInt(6, libro.getCantidadPrestados());
        statement.setInt(7, libro.getCantidadDisponible());
    }

    private void manejarExcepcionSQL(SQLException e) {
        e.printStackTrace();
    }

    private Libro crearLibroDesdeResultSet(ResultSet rs) throws SQLException {
        return new Libro(
                rs.getInt("id"),
                rs.getString("titulo"),
                rs.getString("autor"),
                rs.getString("area_conocimiento"),
                rs.getInt("numero_paginas"),
                rs.getInt("cantidad_ejemplares"),
                rs.getInt("cantidad_prestados"),
                rs.getInt("cantidad_disponible")
        );
    }
}






