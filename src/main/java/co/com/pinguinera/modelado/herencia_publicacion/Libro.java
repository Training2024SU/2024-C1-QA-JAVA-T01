package co.com.pinguinera.modelado.herencia_publicacion;

import co.com.pinguinera.modelado.AreaGenero;
import co.com.pinguinera.modelado.EdadSugerida;
import co.com.pinguinera.modelado.Publicacion;
import co.com.pinguinera.modelado.enums.TipoPublicacion;

import java.util.List;

public class Libro extends Publicacion {

    // Campo de identificación
    private int id;

    public Libro() {
        // Establecer tipo de publicación a LIBRO de forma predeterminada
        super();
        this.setTipoPublicacion(TipoPublicacion.LIBRO);
    }

    public Libro(String titulo, String autor, int numPaginas, int cantEjemplares, int cantPrestados, int cantDisponible, List<AreaGenero> areas, List<EdadSugerida> edades) {
        // Establecer tipo de publicación a LIBRO de forma predeterminada
        super(titulo, TipoPublicacion.LIBRO, autor, numPaginas, cantEjemplares, cantPrestados, cantDisponible, areas, edades);
    }

    // Agregar método getter para ID
    public int getId() {
        return id;
    }

    // Agregar método setter para ID
    public void setId(int id) {
        this.id = id;
    }
}
