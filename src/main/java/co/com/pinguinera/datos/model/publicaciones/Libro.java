package co.com.pinguinera.datos.model.publicaciones;

import co.com.pinguinera.datos.model.AreaGenero;
import co.com.pinguinera.datos.model.EdadSugerida;
import co.com.pinguinera.datos.model.Publicacion;
import co.com.pinguinera.datos.model.enums.TipoPublicacion;

import java.util.List;

public class Libro extends Publicacion {

    public Libro() {
        // Establecer tipo de publicación a LIBRO de forma predeterminada
        super();
        this.setTipoPublicacion(TipoPublicacion.LIBRO);
    }

    public Libro(int idPublicacion, String titulo, String autor, int numPaginas, int cantEjemplares, int cantPrestados, int cantDisponible, List<AreaGenero> areas, List<EdadSugerida> edades) {
        // Llama al constructor de Publicacion con todos los parámetros, incluido `idPublicacion`
        super(idPublicacion, titulo, TipoPublicacion.LIBRO, autor, numPaginas, cantEjemplares, cantPrestados, cantDisponible, areas, edades);
    }
}
