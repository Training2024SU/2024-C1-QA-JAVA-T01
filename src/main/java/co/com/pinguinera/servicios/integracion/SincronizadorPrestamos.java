package co.com.pinguinera.servicios.integracion;

import co.com.pinguinera.datos.DAO.PrestamoDAO;
import co.com.pinguinera.datos.crud_local.CRUDPrestamosLocales;
import co.com.pinguinera.datos.model.Prestamo;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SincronizadorPrestamos {
    private final PrestamoDAO prestamoDAO;
    private final CRUDPrestamosLocales crudPrestamosLocales;

    public SincronizadorPrestamos(PrestamoDAO prestamoDAO, CRUDPrestamosLocales crudPrestamosLocales) {
        this.prestamoDAO = prestamoDAO;
        this.crudPrestamosLocales = crudPrestamosLocales;
    }

    public void sincronizarPrestamos() throws SQLException {
        List<Prestamo> prestamosBD = prestamoDAO.obtenerTodos();
        List<Prestamo> prestamosLocales = crudPrestamosLocales.obtenerTodos();

        Map<Integer, Prestamo> mapaPrestamosLocales = new HashMap<>();
        for (Prestamo prestamoLocal : prestamosLocales) {
            mapaPrestamosLocales.put(prestamoLocal.getIdPrestamo(), prestamoLocal);
        }

        for (Prestamo prestamoBD : prestamosBD) {
            Prestamo prestamoLocal = mapaPrestamosLocales.get(prestamoBD.getIdPrestamo());
            if (prestamoLocal != null) {
                if (!prestamoLocal.equals(prestamoBD)) {
                    prestamoDAO.actualizar(prestamoLocal);
                }
                mapaPrestamosLocales.remove(prestamoLocal.getIdPrestamo());
            }
        }

        for (Prestamo prestamoLocal : mapaPrestamosLocales.values()) {
            prestamoDAO.insertar(prestamoLocal);
        }
    }
}
