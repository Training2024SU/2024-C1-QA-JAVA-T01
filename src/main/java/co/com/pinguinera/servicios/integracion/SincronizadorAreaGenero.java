package co.com.pinguinera.servicios.integracion;

import co.com.pinguinera.datos.DAO.AreaGeneroDAO;
import co.com.pinguinera.datos.crud_local.CRUDAreaGeneroLocales;
import co.com.pinguinera.datos.model.AreaGenero;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SincronizadorAreaGenero {
    private final AreaGeneroDAO areaGeneroDAO;
    private final CRUDAreaGeneroLocales crudAreaGeneroLocales;

    public SincronizadorAreaGenero(AreaGeneroDAO areaGeneroDAO, CRUDAreaGeneroLocales crudAreaGeneroLocales) {
        this.areaGeneroDAO = areaGeneroDAO;
        this.crudAreaGeneroLocales = crudAreaGeneroLocales;
    }

    public void sincronizarAreaGenero() throws SQLException {
        List<AreaGenero> areaGenerosBD = areaGeneroDAO.obtenerTodos();
        List<AreaGenero> areaGenerosLocales = crudAreaGeneroLocales.obtenerTodos();

        Map<Integer, AreaGenero> mapaLocales = new HashMap<>();
        for (AreaGenero agLocal : areaGenerosLocales) {
            mapaLocales.put(agLocal.getIdPublicacion(), agLocal);
        }

        for (AreaGenero agBD : areaGenerosBD) {
            AreaGenero agLocal = mapaLocales.get(agBD.getIdPublicacion());
            if (agLocal != null) {
                if (!agLocal.equals(agBD)) {
                    areaGeneroDAO.actualizar(agLocal);
                }
                mapaLocales.remove(agLocal.getIdPublicacion());
            }
        }

        // Inserta los AreaGeneros locales que no están en la base de datos
        for (AreaGenero agLocal : mapaLocales.values()) {
            areaGeneroDAO.insertar(agLocal);
        }
    }
}
