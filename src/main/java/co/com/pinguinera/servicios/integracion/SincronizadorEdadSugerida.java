package co.com.pinguinera.servicios.integracion;

import co.com.pinguinera.datos.DAO.EdadSugeridaDAO;
import co.com.pinguinera.datos.crud_local.CRUDEdadesSugeridasLocales;
import co.com.pinguinera.datos.model.EdadSugerida;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SincronizadorEdadSugerida {

    private final EdadSugeridaDAO edadSugeridaDAO;
    private final CRUDEdadesSugeridasLocales crudEdadesSugeridasLocales;

    public SincronizadorEdadSugerida(EdadSugeridaDAO edadSugeridaDAO, CRUDEdadesSugeridasLocales crudEdadesSugeridasLocales) {
        this.edadSugeridaDAO = edadSugeridaDAO;
        this.crudEdadesSugeridasLocales = crudEdadesSugeridasLocales;
    }

    public void sincronizarEdadSugerida() throws SQLException {
        // Obtén la lista de datos de EdadSugerida de la base de datos
        List<EdadSugerida> edadesBD = edadSugeridaDAO.obtenerTodos();

        // Obtén la lista de datos de EdadSugerida locales
        List<EdadSugerida> edadesLocales = crudEdadesSugeridasLocales.obtenerTodos();

        // Crea un mapa de edades locales para búsquedas rápidas
        Map<Integer, EdadSugerida> mapaEdadesLocales = new HashMap<>();
        for (EdadSugerida edadLocal : edadesLocales) {
            mapaEdadesLocales.put(edadLocal.getIdPublicacion(), edadLocal);
        }

        // Actualiza datos en la base de datos basados en la lista local
        for (EdadSugerida edadBD : edadesBD) {
            EdadSugerida edadLocal = mapaEdadesLocales.get(edadBD.getIdPublicacion());
            if (edadLocal != null) {
                // Si el dato local está en la base de datos, actualízalo si hay diferencias
                if (!edadLocal.equals(edadBD)) {
                    edadSugeridaDAO.actualizar(edadLocal);
                }
                // Elimina el dato del mapa para que al final sepamos cuáles no estaban en la base de datos
                mapaEdadesLocales.remove(edadLocal.getIdPublicacion());
            }
        }

        // Inserta datos locales que no están en la base de datos
        for (EdadSugerida edadLocal : mapaEdadesLocales.values()) {
            edadSugeridaDAO.insertar(edadLocal);
        }
    }
}

