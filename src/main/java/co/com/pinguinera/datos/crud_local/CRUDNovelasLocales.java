package co.com.pinguinera.datos.crud_local;

import co.com.pinguinera.datos.model.publicaciones.Novela; // Asegúrate de importar la clase Novela
import java.util.ArrayList;
import java.util.List;

public class CRUDNovelasLocales extends AbstractLocalCRUD<Novela> {
    // Constructor de la clase
    public CRUDNovelasLocales() {
        super(); // Llama al constructor de la clase base `AbstractLocalCRUD`
    }

    // Método para obtener todas las novelas de la lista local
    public List<Novela> obtenerTodos() {
        // Devuelve la lista completa de novelas locales
        return new ArrayList<>(datosLocales);
    }
}
