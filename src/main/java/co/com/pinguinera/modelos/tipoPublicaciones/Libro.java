package co.com.pinguinera.modelos.tipoPublicaciones;

import co.com.pinguinera.modelos.AreaGenero;
import co.com.pinguinera.modelos.EdadSugerida;
import co.com.pinguinera.modelos.Publicacion;
import co.com.pinguinera.enums.TipoPublicacion;

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
