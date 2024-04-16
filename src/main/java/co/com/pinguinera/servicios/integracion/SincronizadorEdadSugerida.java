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
        List<EdadSugerida> edadesBD = edadSugeridaDAO.obtenerTodos();
        List<EdadSugerida> edadesLocales = crudEdadesSugeridasLocales.obtenerTodos();

        Map<Integer, EdadSugerida> mapaLocales = new HashMap<>();
        for (EdadSugerida edadLocal : edadesLocales) {
            mapaLocales.put(edadLocal.getIdPublicacion(), edadLocal);
        }

        for (EdadSugerida edadBD : edadesBD) {
            EdadSugerida edadLocal = mapaLocales.get(edadBD.getIdPublicacion());
            if (edadLocal != null) {
                if (!edadLocal.equals(edadBD)) {
                    edadSugeridaDAO.actualizar(edadLocal);
                }
                mapaLocales.remove(edadLocal.getIdPublicacion());
            }
        }

        // Inserta los datos locales que no están en la base de datos
        for (EdadSugerida edadLocal : mapaLocales.values()) {
            edadSugeridaDAO.insertar(edadLocal);
        }
    }
}
