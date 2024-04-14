package co.com.pinguinera.capa_servicios.crud_local;

import co.com.pinguinera.modelado.AreaGenero;

import java.util.ArrayList;
import java.util.List;

public class CRUDAreaGeneroLocales extends AbstractLocalCRUD<AreaGenero> {
    // El constructor llama al constructor de la clase base
    public CRUDAreaGeneroLocales() {
        super();
    }

    // Método para obtener todas las áreas de género de la lista local
    public List<AreaGenero> obtenerTodos() {
        // Devuelve la lista completa de áreas de género locales
        return new ArrayList<>(datosLocales);
    }
}
