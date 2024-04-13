package co.com.pinguinera.capa_servicios.interfaces;

import java.sql.PreparedStatement;
import java.sql.SQLException;

// Interfaz que define las operaciones para interactuar con la base de datos
public interface GestorBD {

    /**
     * Prepara una consulta SQL y devuelve un PreparedStatement.
     *
     * @param consulta La consulta SQL a preparar.
     * @return Un objeto PreparedStatement para ejecutar la consulta.
     * @throws SQLException Si ocurre un error al preparar la consulta.
     */
    PreparedStatement prepararConsulta(String consulta) throws SQLException;

    /**
     * Cierra la conexión con la base de datos.
     *
     * @throws SQLException Si ocurre un error al cerrar la conexión.
     */
    void cerrarConexion() throws SQLException;

    // Otros métodos necesarios para interactuar con la base de datos
}
