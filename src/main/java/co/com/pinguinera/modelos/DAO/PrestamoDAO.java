package co.com.pinguinera.modelos.DAO;

import co.com.pinguinera.modelos.EstadoPrestamo;
import co.com.pinguinera.modelos.Prestamo;
import co.com.pinguinera.modelos.interfaces.PrestamoRepositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PrestamoDAO implements PrestamoRepositorio {

    private Connection conexion;

    // Constructor que recibe la conexión a la base de datos
    public PrestamoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void realizarPrestamo(Prestamo prestamo) {
        String sql = "INSERT INTO Prestamos (UsuarioID, LibroID, FechaPrestamo, FechaDevolucion, Estado) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, prestamo.getUsuarioID());
            statement.setInt(2, prestamo.getLibroID());
            statement.setDate(3, new java.sql.Date(prestamo.getFechaPrestamo().getTime()));
            statement.setDate(4, new java.sql.Date(prestamo.getFechaDevolucion().getTime()));
            statement.setString(5, prestamo.getEstado());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void confirmarPrestamo(int prestamoId) {
        String sql = "UPDATE Prestamos SET Estado = ? WHERE PrestamoID = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, EstadoPrestamo.REALIZADO.name());
            statement.setInt(2, prestamoId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void devolverPrestamo(int prestamoId) {
        String sql = "UPDATE Prestamos SET Estado = ? WHERE PrestamoID = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, EstadoPrestamo.FINALIZADO.name());
            statement.setInt(2, prestamoId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Prestamo> listarPrestamosPorUsuario(int usuarioId) {
        List<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT * FROM Prestamos WHERE UsuarioID = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, usuarioId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Prestamo prestamo = new Prestamo();
                    prestamo.setPrestamoID(resultSet.getInt("PrestamoID"));
                    prestamo.setUsuarioID(resultSet.getInt("UsuarioID"));
                    prestamo.setLibroID(resultSet.getInt("LibroID"));
                    prestamo.setFechaPrestamo(resultSet.getDate("FechaPrestamo"));
                    prestamo.setFechaDevolucion(resultSet.getDate("FechaDevolucion"));
                    prestamo.setEstado(resultSet.getString("Estado"));
                    prestamos.add(prestamo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prestamos;
    }

    @Override
    public List<Prestamo> listarPrestamosPorEstado(EstadoPrestamo estado) {
        List<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT * FROM Prestamos WHERE Estado = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, estado.name());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Prestamo prestamo = new Prestamo();
                    prestamo.setPrestamoID(resultSet.getInt("PrestamoID"));
                    prestamo.setUsuarioID(resultSet.getInt("UsuarioID"));
                    prestamo.setLibroID(resultSet.getInt("LibroID"));
                    prestamo.setFechaPrestamo(resultSet.getDate("FechaPrestamo"));
                    prestamo.setFechaDevolucion(resultSet.getDate("FechaDevolucion"));
                    prestamo.setEstado(resultSet.getString("Estado"));
                    prestamos.add(prestamo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prestamos;
    }

    @Override
    public List<Prestamo> listarPrestamosPorFecha(Date fechaInicio, Date fechaFin) {
        List<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT * FROM Prestamos WHERE FechaPrestamo BETWEEN ? AND ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(fechaInicio.getTime()));
            statement.setDate(2, new java.sql.Date(fechaFin.getTime()));
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Prestamo prestamo = new Prestamo();
                    prestamo.setPrestamoID(resultSet.getInt("PrestamoID"));
                    prestamo.setUsuarioID(resultSet.getInt("UsuarioID"));
                    prestamo.setLibroID(resultSet.getInt("LibroID"));
                    prestamo.setFechaPrestamo(resultSet.getDate("FechaPrestamo"));
                    prestamo.setFechaDevolucion(resultSet.getDate("FechaDevolucion"));
                    prestamo.setEstado(resultSet.getString("Estado"));
                    prestamos.add(prestamo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prestamos;
    }
}
