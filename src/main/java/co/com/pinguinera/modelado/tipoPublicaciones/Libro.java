package co.com.pinguinera.modelado.tipoPublicaciones;

import co.com.pinguinera.modelado.AreaGenero;
import co.com.pinguinera.modelado.EdadSugerida;
import co.com.pinguinera.modelado.Publicacion;
import co.com.pinguinera.modelado.enums.TipoPublicacion;

import java.util.List;

public class Libro extends Publicacion {

    public Libro() {
        // Establecer tipo de publicación a LIBRO de forma predeterminada
        super();
        this.setTipoPublicacion(TipoPublicacion.LIBRO);
    }

    public Libro(String titulo, String autor, int numPaginas, int cantEjemplares, int cantPrestados, int cantDisponible, List<AreaGenero> areas, List<EdadSugerida> edades) {
        // Establecer tipo de publicación a LIBRO de forma predeterminada
        super(titulo, TipoPublicacion.LIBRO, autor, numPaginas, cantEjemplares, cantPrestados, cantDisponible, areas, edades);
    }

}
