package co.com.pinguinera.capa_servicios.interfaces;

import java.util.List;

public interface CRUDLocal<T> {
    /**
     * Agrega un nuevo objeto a la colección local.
     *
     * @param objeto El objeto a agregar.
     */
    void agregar(T objeto);

    /**
     * Actualiza un objeto existente en la colección local.
     *
     * @param objeto El objeto actualizado.
     */
    void actualizar(T objeto);

    /**
     * Elimina un objeto de la colección local.
     *
     * @param objeto El objeto a eliminar.
     */
    void eliminar(T objeto);

    /**
     * Obtiene todos los objetos de la colección local.
     *
     * @return Una lista con todos los objetos.
     */
    List<T> obtenerTodos();
}
