package co.com.pinguinera.modelos.tipoPublicaciones;

import co.com.pinguinera.enums.TipoPublicacion;
import co.com.pinguinera.modelos.AreaGenero;
import co.com.pinguinera.modelos.EdadSugerida;
import co.com.pinguinera.modelos.Publicacion;

import java.util.List;

public class Novela extends Publicacion {

    public Novela() {
        super();
        this.setTipoPublicacion(TipoPublicacion.NOVELA);
    }

    public Novela(String titulo, String autor, int numPaginas, int cantEjemplares, int cantPrestados, int cantDisponible, List<AreaGenero> areas, List<EdadSugerida> edades) {
        super(titulo, TipoPublicacion.NOVELA, autor, numPaginas, cantEjemplares, cantPrestados, cantDisponible, areas, edades);
    }

}
