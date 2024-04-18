package co.com.training.logica;

import co.com.training.integration.database.mysql.MySqlOperation;
import co.com.training.modelo.Libro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudLibro {

    private final MySqlOperation mySqlOperation;

    public CrudLibro(MySqlOperation mySqlOperation) {
        this.mySqlOperation = mySqlOperation;
    }

    public void insertarLibro(Libro libro) {
        // Construir la consulta SQL para insertar un libro en la base de datos
        String consultaSQL = String.format("INSERT INTO la_pinguinera_biblioteca.libro VALUES ('%s', '%s', '%s', '%s', '%s', '%s');",
                libro.getTituloLibro(), libro.getAutor(), libro.getAreaConocimiento(),
                libro.getNumeroPaginas(), libro.getCantidadEjemplares(), libro.getCantidadPrestados());

        // Ejecutar la consulta SQL utilizando la operación MySQL
        mySqlOperation.setSqlStatement(consultaSQL);
        mySqlOperation.executeSqlStatementVoid();
    }

    public Libro consultarLibroPorTitulo(String titulo) throws SQLException {
        Libro libro = null;
        String sql = "SELECT * FROM libro WHERE tituloLibro = ?";

        try (Connection connection = mySqlOperation.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, titulo);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String autor = resultSet.getString("autorLibro");
                    String areaConocimiento = resultSet.getString("areaConocimiento");
                    int numeroPaginas = resultSet.getInt("numeroPaginas");
                    int cantidadEjemplares = resultSet.getInt("cantidadEjemplares");
                    int cantidadPrestados = resultSet.getInt("cantidadPrestados");

                    libro = new Libro(titulo, autor, areaConocimiento, numeroPaginas, cantidadEjemplares, cantidadPrestados);
                }
            }
        } catch (SQLException e) {
            // Manejar la excepción, imprimir el mensaje de error o lanzarla nuevamente
            System.out.println("Error al consultar el libro por título: " + e.getMessage());
            throw e; // Lanzar la excepción nuevamente para que sea manejada en otro lugar si es necesario
        }

        return libro; // Devolver el libro consultado (o null si no se encuentra)
    }

    public void eliminarLibro(String titulo) throws SQLException {
        String sql = "DELETE FROM libro WHERE tituloLibro = ?";

        try (Connection connection = mySqlOperation.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, titulo);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted == 0) {
                System.out.println("No se encontró ningún libro con el título: " + titulo);
            } else {
                System.out.println("Libro eliminado correctamente: " + titulo);
            }
        } catch (SQLException e) {
            // Manejar la excepción, imprimir el mensaje de error o lanzarla nuevamente
            System.out.println("Error al eliminar el libro: " + e.getMessage());
            throw e; // Lanzar la excepción nuevamente para que sea manejada en otro lugar si es necesario
        }
    }

    public void actualizarLibro(String titulo, String autor, String areaConocimiento, int numeroPaginas, int cantidadEjemplares, int cantidadPrestados) throws SQLException {
        String sql = "UPDATE libro SET autorLibro = ?, areaConocimiento = ?, numeroPaginas = ?, cantidadEjemplares = ?, cantidadPrestados = ? WHERE tituloLibro = ?";

        try (Connection connection = mySqlOperation.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, autor);
            statement.setString(2, areaConocimiento);
            statement.setInt(3, numeroPaginas);
            statement.setInt(4, cantidadEjemplares);
            statement.setInt(5, cantidadPrestados);
            statement.setString(6, titulo);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                System.out.println("No se encontró ningún libro con el título: " + titulo);
            } else {
                System.out.println("Libro actualizado correctamente: " + titulo);
            }
        } catch (SQLException e) {
            // Manejar la excepción, imprimir el mensaje de error o lanzarla nuevamente
            System.out.println("Error al actualizar el libro: " + e.getMessage());
            throw e; // Lanzar la excepción nuevamente para que sea manejada en otro lugar si es necesario
        }
    }
}
