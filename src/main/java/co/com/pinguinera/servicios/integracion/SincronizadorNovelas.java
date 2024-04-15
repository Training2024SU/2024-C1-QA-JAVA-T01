package co.com.pinguinera.servicios.integracion;

import co.com.pinguinera.datos.crud_base_datos.NovelaPersistencia;
import co.com.pinguinera.datos.crud_local.CRUDNovelasLocales;
import co.com.pinguinera.datos.model.publicaciones.Novela;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SincronizadorNovelas {

    private final NovelaPersistencia novelaPersistencia;
    private final CRUDNovelasLocales CRUDNovelasLocales;

    public SincronizadorNovelas(NovelaPersistencia novelaPersistencia, CRUDNovelasLocales CRUDNovelasLocales) {
        this.novelaPersistencia = novelaPersistencia;
        this.CRUDNovelasLocales = CRUDNovelasLocales;
    }

    public void sincronizarNovelas() throws SQLException {
        // Obtén la lista de novelas de la base de datos
        List<Novela> novelasBD = novelaPersistencia.obtenerTodos();

        // Obtén la lista de novelas local
        List<Novela> novelasLocales = CRUDNovelasLocales.obtenerTodos();

        // Crea un mapa de novelas locales para búsquedas rápidas
        Map<Integer, Novela> mapaNovelasLocales = new HashMap<>();
        for (Novela novelaLocal : novelasLocales) {
            mapaNovelasLocales.put(novelaLocal.getIdPublicacion(), novelaLocal);
        }

        // Actualiza novelas en la base de datos basados en la lista local
        for (Novela novelaBD : novelasBD) {
            Novela novelaLocal = mapaNovelasLocales.get(novelaBD.getIdPublicacion());
            if (novelaLocal != null) {
                // Si la novela local está en la base de datos, actualízala si hay diferencias
                if (!novelaLocal.equals(novelaBD)) {
                    novelaPersistencia.actualizar(novelaLocal);
                }
                // Elimina la novela del mapa para que al final sepamos cuáles no estaban en la base de datos
                mapaNovelasLocales.remove(novelaLocal.getIdPublicacion());
            }
        }

        // Inserta novelas locales que no están en la base de datos
        for (Novela novelaLocal : mapaNovelasLocales.values()) {
            novelaPersistencia.insertar(novelaLocal);
        }
    }
}
