package co.com.pinguinera.capa_servicios.CRUD;

import co.com.pinguinera.capa_servicios.CRUD.AbstractLocalCRUD;
import co.com.pinguinera.modelado.publicaciones.Libro;

import java.util.ArrayList;
import java.util.List;

public class ServicioLocalCRUDLibros extends AbstractLocalCRUD<Libro> {
    // El constructor llama al constructor de la clase base
    public ServicioLocalCRUDLibros() {
        super(); // Llama al constructor de la clase base `AbstractLocalCRUD`
    }

    // Método para obtener todos los libros de la lista local
    public List<Libro> obtenerTodos() {
        // Devuelve la lista completa de libros locales
        return new ArrayList<>(datosLocales);
    }
}
