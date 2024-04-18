package org.moreno.cristian.repositorios;

import org.moreno.cristian.modelos.Libro;

import java.util.List;
import java.util.Optional;

public interface RepositorioLibro {

    Optional<List<Libro>> todosLibros();
    Optional<List<Libro>> todosPorAutor(String autor);

    Optional<Libro> libroPorNombre(String nombre);
    Optional<List<Libro>> todosDisponibles();
    Optional<Libro> disponiblePorNombreLibro(String nombreLibro);
    Optional<List<Libro>> disponiblesPorAutor(String nombreAutor);


    boolean guardarLibro(Libro nuevoLibro);
    boolean eliminarLibro(String libroId);
    boolean actualizarLibro(Libro libro);
}

