package co.com.training.logica;

import co.com.training.integration.database.mysql.MySqlOperation;
import co.com.training.modelo.Novela;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudNovela {

    private final MySqlOperation mySqlOperation;

    public CrudNovela(MySqlOperation mySqlOperation) {
        this.mySqlOperation = mySqlOperation;
    }

    public void insertarNovela(Novela novela) {
        // Construir la consulta SQL para insertar una novela en la base de datos
        String consultaSQL = String.format("INSERT INTO la_pinguinera_biblioteca.novela VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s');",
                novela.getTituloNovela(), novela.getAutor(), novela.getGenero(),
                novela.getEdadLecturaSugerida(), novela.getCantidadEjemplares(), novela.getCantidadPrestados(),
                novela.getGenero());

        // Ejecutar la consulta SQL utilizando la operación MySQL
        mySqlOperation.setSqlStatement(consultaSQL);
        mySqlOperation.executeSqlStatementVoid();
    }

    public Novela consultarNovelaPorTitulo(String titulo) throws SQLException {
        Novela novela = null;
        String sql = "SELECT * FROM novela WHERE tituloNovela = ?";

        try (Connection connection = mySqlOperation.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, titulo);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String tituloNovela = resultSet.getString("tituloNovela");
                    String autor = resultSet.getString("autor");
                    String genero = resultSet.getString("genero");
                    int edadLecturaSugerida = resultSet.getInt("edadLecturaSugerida");
                    int cantidadEjemplares = resultSet.getInt("cantidadEjemplares");
                    int cantidadPrestados = resultSet.getInt("cantidadPrestados");


                    novela = new Novela(tituloNovela, autor, genero, edadLecturaSugerida, cantidadEjemplares, cantidadPrestados);
                }
            }
        } catch (SQLException e) {
            // Manejar la excepción, imprimir el mensaje de error o lanzarla nuevamente
            System.out.println("Error al consultar la novela por título: " + e.getMessage());
            throw e; // Lanzar la excepción nuevamente para que sea manejada en otro lugar si es necesario
        }

        return novela; // Devolver la novela consultada (o null si no se encuentra)
    }

    public void eliminarNovela(String tituloNovela) throws SQLException {
        String sql = "DELETE FROM novela WHERE tituloNovela = ?";

        try (Connection connection = mySqlOperation.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, tituloNovela);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted == 0) {
                System.out.println("No se encontró ninguna novela con el título: " + tituloNovela);
            } else {
                System.out.println("Novela eliminada correctamente: " + tituloNovela);
            }
        } catch (SQLException e) {
            // Manejar la excepción, imprimir el mensaje de error o lanzarla nuevamente
            System.out.println("Error al eliminar la novela: " + e.getMessage());
            throw e; // Lanzar la excepción nuevamente para que sea manejada en otro lugar si es necesario
        }
    }

    public void actualizarNovela(String tituloNOvela, String autor, String genero, int edadLecturaSugerida, int cantidadEjemplares, int cantidadPrestados) throws SQLException {
        String sql = "UPDATE novela SET autor = ?, genero = ?, numeroPaginas = ?, cantidadEjemplares = ?, cantidadPrestados = ?, WHERE tituloLibro = ?";

        try (Connection connection = mySqlOperation.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, tituloNOvela);
            statement.setString(2, autor);
            statement.setString(3, genero);
            statement.setInt(4, edadLecturaSugerida);
            statement.setInt(5, cantidadEjemplares);
            statement.setInt(6, cantidadPrestados);


            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                System.out.println("No se encontró ninguna novela con el título: " + tituloNOvela);
            } else {
                System.out.println("Novela actualizada correctamente: " + tituloNOvela);
            }
        } catch (SQLException e) {
            // Manejar la excepción, imprimir el mensaje de error o lanzarla nuevamente
            System.out.println("Error al actualizar la novela: " + e.getMessage());
            throw e; // Lanzar la excepción nuevamente para que sea manejada en otro lugar si es necesario
        }
    }
}

