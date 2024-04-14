package co.com.pinguinera.capa_servicios.interfaces;

import java.util.List;
import java.sql.SQLException;

public interface SincronizacionBD<T> {
    /**
     * Sincroniza los datos entre la colección local y la base de datos.
     *
     * @param coleccionLocal La colección local a sincronizar con la base de datos.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    void sincronizar(List<T> coleccionLocal) throws SQLException;
}
