package co.com.pinguinera.capa_datos.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    /**
     * Obtiene todos los registros de la tabla en la base de datos.
     *
     * @return Una lista con todos los registros de la tabla en la base de datos.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    List<T> obtenerTodos() throws SQLException;

    /**
     * Inserta un nuevo objeto de tipo T en la tabla correspondiente de la base de datos.
     *
     * @param objeto El objeto a insertar en la base de datos.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    void insertar(T objeto) throws SQLException;

    /**
     * Actualiza un objeto de tipo T en la tabla correspondiente de la base de datos.
     *
     * @param objeto El objeto a actualizar en la base de datos.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    void actualizar(T objeto) throws SQLException;

    /**
     * Elimina un objeto de tipo T de la tabla correspondiente en la base de datos.
     *
     * @param id El identificador del objeto a eliminar de la base de datos.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    void eliminar(int id) throws SQLException;
}
