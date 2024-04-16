package co.com.pinguinera.datos.crud_local;

import co.com.pinguinera.datos.model.publicaciones.Libro;

import java.util.ArrayList;
import java.util.List;

public class CRUDLibrosLocales extends AbstractLocalCRUD<Libro> {
    // El constructor llama al constructor de la clase base
    public CRUDLibrosLocales() {
        super(); // Llama al constructor de la clase base `AbstractLocalCRUD`
    }

    // Método para obtener todos los libros de la lista local
    public List<Libro> obtenerTodos() {
        // Devuelve la lista completa de libros locales
        return new ArrayList<>(datosLocales);
    }
}
