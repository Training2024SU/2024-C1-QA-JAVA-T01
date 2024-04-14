package co.com.pinguinera.capa_servicios.CRUD;

import co.com.pinguinera.capa_servicios.interfaces.CRUD;
import co.com.pinguinera.modelado.publicaciones.Libro;

import java.util.ArrayList;
import java.util.List;

public class ServicioCRUDLibros implements CRUD<Libro> {
    private final List<Libro> libros;

    // Constructor
    public ServicioCRUDLibros() {
        // Inicializa la lista local como una lista vacía
        this.libros = new ArrayList<>();
    }

    @Override
    public void agregar(Libro libro) {
        // Agrega el libro a la lista local si no existe ya
        if (!libros.contains(libro)) {
            libros.add(libro);
        }
    }

    @Override
    public void actualizar(Libro libro) {
        // Actualiza el libro en la lista local si existe
        int index = libros.indexOf(libro);
        if (index != -1) {
            libros.set(index, libro);
        }
    }

    @Override
    public void eliminar(Libro libro) {
        // Elimina el libro de la lista local si existe
        libros.remove(libro);
    }
}
