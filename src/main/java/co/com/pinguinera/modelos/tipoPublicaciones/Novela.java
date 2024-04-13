package co.com.pinguinera.modelos.tipoPublicaciones;
import co.com.pinguinera.modelos.AreaGenero;
import co.com.pinguinera.modelos.EdadSugerida;

import co.com.pinguinera.modelos.Publicacion;

import java.util.List;

public class Novela extends Publicacion {

    public Novela() {
        // Constructor vacío
    }

    public Novela(String titulo, String tipoPublicacion, String autor, int numPaginas, int cantEjemplares, int cantPrestados, int cantDisponible, List<AreaGenero> areas, List<EdadSugerida> edades) {
        super(titulo, tipoPublicacion, autor, numPaginas, cantEjemplares, cantPrestados, cantDisponible, areas, edades);
    }

}
