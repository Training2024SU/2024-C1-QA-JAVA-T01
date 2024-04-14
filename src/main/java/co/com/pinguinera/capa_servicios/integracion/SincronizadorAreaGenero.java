package co.com.pinguinera.capa_servicios.integracion;

import co.com.pinguinera.capa_servicios.crud_base_datos.AreaGeneroPersistencia;
import co.com.pinguinera.capa_servicios.crud_local.CRUDAreaGeneroLocales;
import co.com.pinguinera.modelado.AreaGenero;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SincronizadorAreaGenero {

    private final AreaGeneroPersistencia areaGeneroPersistencia;
    private final CRUDAreaGeneroLocales crudAreaGeneroLocales;

    public SincronizadorAreaGenero(AreaGeneroPersistencia areaGeneroPersistencia, CRUDAreaGeneroLocales crudAreaGeneroLocales) {
        this.areaGeneroPersistencia = areaGeneroPersistencia;
        this.crudAreaGeneroLocales = crudAreaGeneroLocales;
    }

    public void sincronizarAreaGenero() throws SQLException {
        // Obtén la lista de AreaGeneros de la base de datos
        List<AreaGenero> areaGenerosBD = areaGeneroPersistencia.obtenerTodos();

        // Obtén la lista de AreaGeneros locales
        List<AreaGenero> areaGenerosLocales = crudAreaGeneroLocales.obtenerTodos();

        // Crea un mapa de AreaGeneros locales para búsquedas rápidas
        Map<Integer, AreaGenero> mapaAreaGenerosLocales = new HashMap<>();
        for (AreaGenero areaGeneroLocal : areaGenerosLocales) {
            mapaAreaGenerosLocales.put(areaGeneroLocal.getIdPublicacion(), areaGeneroLocal);
        }

        // Actualiza AreaGeneros en la base de datos basados en la lista local
        for (AreaGenero areaGeneroBD : areaGenerosBD) {
            AreaGenero areaGeneroLocal = mapaAreaGenerosLocales.get(areaGeneroBD.getIdPublicacion());
            if (areaGeneroLocal != null) {
                // Si el AreaGenero local está en la base de datos, actualízalo si hay diferencias
                if (!areaGeneroLocal.equals(areaGeneroBD)) {
                    areaGeneroPersistencia.actualizar(areaGeneroLocal);
                }
                // Elimina el AreaGenero del mapa para que al final sepamos cuáles no estaban en la base de datos
                mapaAreaGenerosLocales.remove(areaGeneroLocal.getIdPublicacion());
            }
        }

        // Inserta AreaGeneros locales que no están en la base de datos
        for (AreaGenero areaGeneroLocal : mapaAreaGenerosLocales.values()) {
            areaGeneroPersistencia.insertar(areaGeneroLocal);
        }
    }
}
