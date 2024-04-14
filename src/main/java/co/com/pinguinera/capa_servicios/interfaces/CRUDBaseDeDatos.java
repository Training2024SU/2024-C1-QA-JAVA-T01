package co.com.pinguinera.capa_servicios.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface CRUDBaseDeDatos<T> {
    /**
     * Inserta un nuevo objeto en la base de datos.
     *
     * @param objeto El objeto a insertar.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    void insertar(T objeto) throws SQLException;

    /**
     * Actualiza un objeto existente en la base de datos.
     *
     * @param objeto El objeto a actualizar.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    void actualizar(T objeto) throws SQLException;

    /**
     * Elimina un objeto de la base de datos.
     *
     * @param objeto El objeto a eliminar.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    void eliminar(T objeto) throws SQLException;

    /**
     * Obtiene todos los objetos de la base de datos.
     *
     * @return Una lista con todos los objetos.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    List<T> obtenerTodos() throws SQLException;
}
