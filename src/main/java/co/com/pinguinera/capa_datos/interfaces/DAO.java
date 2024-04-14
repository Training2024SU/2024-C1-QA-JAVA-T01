package co.com.pinguinera.capa_datos.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    /**
     * Obtiene todos los registros por tablas la base de datos.
     *
     * @return Una lista con todos los registros por tablas de la base de datos.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    List<T> obtenerTodos() throws SQLException;
}
