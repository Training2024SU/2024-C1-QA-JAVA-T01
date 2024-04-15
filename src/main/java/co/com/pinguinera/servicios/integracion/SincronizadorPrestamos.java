package co.com.pinguinera.servicios.integracion;

import co.com.pinguinera.datos.crud_base_datos.PrestamoPersistencia;
import co.com.pinguinera.datos.crud_local.CRUDPrestamosLocales;
import co.com.pinguinera.datos.model.Prestamo;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SincronizadorPrestamos {

    private final PrestamoPersistencia prestamoPersistencia;
    private final CRUDPrestamosLocales crudPrestamosLocales;

    public SincronizadorPrestamos(PrestamoPersistencia prestamoPersistencia, CRUDPrestamosLocales crudPrestamosLocales) {
        this.prestamoPersistencia = prestamoPersistencia;
        this.crudPrestamosLocales = crudPrestamosLocales;
    }

    public void sincronizarPrestamos() throws SQLException {
        // Obtén la lista de préstamos de la base de datos
        List<Prestamo> prestamosBD = prestamoPersistencia.obtenerTodos();

        // Obtén la lista de préstamos locales
        List<Prestamo> prestamosLocales = crudPrestamosLocales.obtenerTodos();

        // Crea un mapa de préstamos locales para búsquedas rápidas
        Map<Integer, Prestamo> mapaPrestamosLocales = new HashMap<>();
        for (Prestamo prestamoLocal : prestamosLocales) {
            mapaPrestamosLocales.put(prestamoLocal.getIdPrestamo(), prestamoLocal);
        }

        // Actualiza préstamos en la base de datos basados en la lista local
        for (Prestamo prestamoBD : prestamosBD) {
            Prestamo prestamoLocal = mapaPrestamosLocales.get(prestamoBD.getIdPrestamo());
            if (prestamoLocal != null) {
                // Si el préstamo local está en la base de datos, actualízalo si hay diferencias
                if (!prestamoLocal.equals(prestamoBD)) {
                    prestamoPersistencia.actualizar(prestamoLocal);
                }
                // Elimina el préstamo del mapa para que al final sepamos cuáles no estaban en la base de datos
                mapaPrestamosLocales.remove(prestamoLocal.getIdPrestamo());
            }
        }

        // Inserta préstamos locales que no están en la base de datos
        for (Prestamo prestamoLocal : mapaPrestamosLocales.values()) {
            prestamoPersistencia.insertar(prestamoLocal);
        }
    }
}
