package co.com.pinguinera.datos.model.publicaciones;

import co.com.pinguinera.datos.model.enums.TipoPublicacion;
import co.com.pinguinera.datos.model.AreaGenero;
import co.com.pinguinera.datos.model.EdadSugerida;
import co.com.pinguinera.datos.model.Publicacion;

import java.util.List;

public class Novela extends Publicacion {

    public Novela() {
        super();
        this.setTipoPublicacion(TipoPublicacion.NOVELA);
    }

    public Novela(int idPublicacion, String titulo, String autor, int numPaginas, int cantEjemplares, int cantPrestados, int cantDisponible) {
        super(idPublicacion, titulo, TipoPublicacion.NOVELA, autor, numPaginas, cantEjemplares, cantPrestados, cantDisponible);
    }

}
