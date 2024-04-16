package co.com.pinguinera.datos.crud_local;

import co.com.pinguinera.datos.model.EdadSugerida;

import java.util.ArrayList;
import java.util.List;

public class CRUDEdadesSugeridasLocales extends AbstractLocalCRUD<EdadSugerida> {
    // El constructor llama al constructor de la clase base
    public CRUDEdadesSugeridasLocales() {
        super();
    }

    // Método para obtener todas las edades sugeridas de la lista local
    public List<EdadSugerida> obtenerTodos() {
        // Devuelve la lista completa de edades sugeridas locales
        return new ArrayList<>(datosLocales);
    }
}
