package co.com.pinguinera.modelos.DAO;

import co.com.pinguinera.modelos.Libro;
import co.com.pinguinera.modelos.repositorios.LibroRepositorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO implements LibroRepositorio {

    private Connection conexion;

    // Constructor que recibe la conexión a la base de datos
    public LibroDAO(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void agregarLibro(Libro libro) {
        String sql = "INSERT INTO Libros (Titulo, Autor, AreaConocimiento, NumPaginas, CantEjemplares) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, libro.getTitulo());
            statement.setString(2, libro.getAutor());
            statement.setString(3, libro.getAreaConocimiento());
            statement.setInt(4, libro.getNumPaginas());
            statement.setInt(5, libro.getCantEjemplares());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarLibro(Libro libro) {
        String sql = "UPDATE Libros SET Titulo = ?, Autor = ?, AreaConocimiento = ?, NumPaginas = ?, CantEjemplares = ? WHERE LibroID = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, libro.getTitulo());
            statement.setString(2, libro.getAutor());
            statement.setString(3, libro.getAreaConocimiento());
            statement.setInt(4, libro.getNumPaginas());
            statement.setInt(5, libro.getCantEjemplares());
            statement.setInt(6, libro.getLibroID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarLibro(int libroID) {
        String sql = "DELETE FROM Libros WHERE LibroID = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, libroID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Libro> obtenerTodosLosLibros() {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM Libros";
        try (Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Libro libro = new Libro();
                libro.setLibroID(resultSet.getInt("LibroID"));
                libro.setTitulo(resultSet.getString("Titulo"));
                libro.setAutor(resultSet.getString("Autor"));
                libro.setAreaConocimiento(resultSet.getString("AreaConocimiento"));
                libro.setNumPaginas(resultSet.getInt("NumPaginas"));
                libro.setCantEjemplares(resultSet.getInt("CantEjemplares"));
                libros.add(libro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libros;
    }

    @Override
    public Libro buscarLibroPorTitulo(String titulo) {
        String sql = "SELECT * FROM Libros WHERE Titulo LIKE ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, "%" + titulo + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Libro libro = new Libro();
                    libro.setLibroID(resultSet.getInt("LibroID"));
                    libro.setTitulo(resultSet.getString("Titulo"));
                    libro.setAutor(resultSet.getString("Autor"));
                    libro.setAreaConocimiento(resultSet.getString("AreaConocimiento"));
                    libro.setNumPaginas(resultSet.getInt("NumPaginas"));
                    libro.setCantEjemplares(resultSet.getInt("CantEjemplares"));
                    return libro;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> listarAutoresDeLibros() {
        List<String> autores = new ArrayList<>();
        String sql = "SELECT DISTINCT Autor FROM Libros"; // Consulta para obtener autores únicos
        try (Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                autores.add(resultSet.getString("Autor"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autores;
    }
}

