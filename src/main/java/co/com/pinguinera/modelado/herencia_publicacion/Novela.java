package co.com.pinguinera.modelado.herencia_publicacion;

import co.com.pinguinera.modelado.enums.TipoPublicacion;
import co.com.pinguinera.modelado.AreaGenero;
import co.com.pinguinera.modelado.EdadSugerida;
import co.com.pinguinera.modelado.Publicacion;

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
