package co.com.pinguinera.capa_servicios.crud_local;

import co.com.pinguinera.modelado.Prestamo;

import java.util.ArrayList;
import java.util.List;

public class CRUDPrestamosLocales extends AbstractLocalCRUD<Prestamo> {
    // El constructor llama al constructor de la clase base
    public CRUDPrestamosLocales() {
        super();
    }

    // Método para obtener todos los préstamos de la lista local
    public List<Prestamo> obtenerTodos() {
        // Devuelve la lista completa de préstamos locales
        return new ArrayList<>(datosLocales);
    }
}
