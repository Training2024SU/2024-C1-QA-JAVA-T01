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
        List<Novela> novelasBD = novelaDAO.obtenerTodos();
        List<Novela> novelasLocales = crudNovelasLocales.obtenerTodos();

        Map<Integer, Novela> mapaNovelasLocales = new HashMap<>();
        for (Novela novelaLocal : novelasLocales) {
            mapaNovelasLocales.put(novelaLocal.getIdPublicacion(), novelaLocal);
        }

        for (Novela novelaBD : novelasBD) {
            Novela novelaLocal = mapaNovelasLocales.get(novelaBD.getIdPublicacion());
            if (novelaLocal != null) {
                if (!novelaLocal.equals(novelaBD)) {
                    novelaDAO.actualizar(novelaLocal);
                }
                mapaNovelasLocales.remove(novelaLocal.getIdPublicacion());
            }
        }

        // Inserta novelas locales que no están en la base de datos
        for (Novela novelaLocal : mapaNovelasLocales.values()) {
            novelaDAO.insertar(novelaLocal);
        }
    }
}
