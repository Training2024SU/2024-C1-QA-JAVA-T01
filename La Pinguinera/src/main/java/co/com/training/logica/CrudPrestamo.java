package co.com.training.logica;

import co.com.training.integration.database.mysql.MySqlOperation;
import co.com.training.modelo.Prestamo;
import co.com.training.util.enums.EstadoPrestamo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CrudPrestamo {

    private final MySqlOperation mySqlOperation;

    public CrudPrestamo(MySqlOperation mySqlOperation) {
        this.mySqlOperation = mySqlOperation;
    }

    public void realizarPrestamo(String correoUsuario, List<String> titulosLibros, List<String> titulosNovelas, String fechaPrestamo, String fechaDevolucion) throws SQLException {
        // Confirmación del usuario
        // Aquí deberías implementar la lógica para pedir la confirmación al usuario

        // Si el usuario confirma el préstamo
        // Descontar 1 a la cantidad de unidades disponibles en todos los libros y novelas seleccionados
        for (String tituloLibro : titulosLibros) {
            descontarUnidadDisponibleLibro(tituloLibro);
        }
        for (String tituloNovela : titulosNovelas) {
            descontarUnidadDisponibleNovela(tituloNovela);
        }

        // Registrar el préstamo en la base de datos
        insertarPrestamo(correoUsuario, titulosLibros, titulosNovelas, fechaPrestamo, fechaDevolucion);
    }

    private void descontarUnidadDisponibleLibro(String tituloLibro) throws SQLException {
        String sql = "UPDATE libro SET cantidadEjemplares = cantidadEjemplares - 1 WHERE tituloLibro = ?";

        try (Connection connection = mySqlOperation.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, tituloLibro);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al descontar la unidad disponible del libro: " + e.getMessage());
            throw e;
        }
    }

    private void descontarUnidadDisponibleNovela(String tituloNovela) throws SQLException {
        String sql = "UPDATE novela SET cantidadEjemplares = cantidadEjemplares - 1 WHERE tituloLibro = ?";

        try (Connection connection = mySqlOperation.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, tituloNovela);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al descontar la unidad disponible de la novela: " + e.getMessage());
            throw e;
        }
    }

    private void insertarPrestamo(String correoUsuario, List<String> titulosLibros, List<String> titulosNovelas, Date fechaPrestamo, Date fechaDevolucion) throws SQLException {
        String sql = "INSERT INTO prestamo (correoUsuario, tituloLibro, TipoPublicacion tipoPublicacion, fechaPrestamo, fechaDevolucion) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = mySqlOperation.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (String tituloLibro : titulosLibros) {
                statement.setString(1, correoUsuario);
                statement.setString(2, tituloLibro);
                statement.setString(2, tipoPublicacion);
                statement.setString(4, "Libro");
                statement.setDate(5, fechaPrestamo);
                statement.setDate(6, fechaDevolucion);
                statement.addBatch();
            }
            for (String tituloNovela : titulosNovelas) {
                statement.setString(1, correoUsuario);
                statement.setString(2, tituloNovela);
                statement.setString(3, "Novela");
                statement.setString(4, fechaPrestamo);
                statement.setString(5, fechaDevolucion);
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            System.out.println("Error al registrar el préstamo en la base de datos: " + e.getMessage());
            throw e;
        }
    }

    public List<Prestamo> consultarPrestamosPorCorreo(String correoUsuario) throws SQLException {
        List<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT * FROM prestamo WHERE correoUsuario = ?";

        try (Connection connection = mySqlOperation.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, correoUsuario);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String titulo = resultSet.getString("tituloLibro");
                    String tipo = resultSet.getString("tipo");
                    String fechaPrestamo = resultSet.getString("fechaPrestamo");
                    String fechaDevolucion = resultSet.getString("fechaDevolucion");

                    Prestamo prestamo = new Prestamo(titulo, tipo, fechaPrestamo, fechaDevolucion);
                    prestamos.add(prestamo);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar los préstamos del usuario: " + e.getMessage());
            throw e;
        }

        return prestamos;
    }
}
