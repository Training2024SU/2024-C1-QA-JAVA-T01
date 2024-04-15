package co.com.pinguinera.servicios.integracion;

import co.com.pinguinera.datos.DAO.NovelaDAO;
import co.com.pinguinera.datos.crud_local.CRUDNovelasLocales;
import co.com.pinguinera.datos.model.publicaciones.Novela;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SincronizadorNovelas {

    private final NovelaDAO novelaDAO;
    private final CRUDNovelasLocales crudNovelasLocales;

    public SincronizadorNovelas(NovelaDAO novelaDAO, CRUDNovelasLocales crudNovelasLocales) {
        this.novelaDAO = novelaDAO;
        this.crudNovelasLocales = crudNovelasLocales;
    }

    public void sincronizarNovelas() throws SQLException {
        // Obtén la lista de novelas de la base de datos usando NovelaDAO
        List<Novela> novelasBD = novelaDAO.obtenerTodos();

        // Obtén la lista de novelas locales
        List<Novela> novelasLocales = crudNovelasLocales.obtenerTodos();

        // Crea un mapa de novelas locales para búsquedas rápidas
        Map<Integer, Novela> mapaNovelasLocales = new HashMap<>();
        for (Novela novelaLocal : novelasLocales) {
            mapaNovelasLocales.put(novelaLocal.getIdPublicacion(), novelaLocal);
        }

        // Actualiza novelas en la base de datos basadas en la lista local
        for (Novela novelaBD : novelasBD) {
            Novela novelaLocal = mapaNovelasLocales.get(novelaBD.getIdPublicacion());
            if (novelaLocal != null) {
                // Si la novela local está en la base de datos, actualízala si hay diferencias
                if (!novelaLocal.equals(novelaBD)) {
                    novelaDAO.actualizar(novelaLocal);
                }
                // Elimina la novela del mapa para que al final sepamos cuáles no estaban en la base de datos
                mapaNovelasLocales.remove(novelaLocal.getIdPublicacion());
            }
        }

        // Inserta novelas locales que no están en la base de datos
        for (Novela novelaLocal : mapaNovelasLocales.values()) {
            novelaDAO.insertar(novelaLocal);
        }
    }
}
