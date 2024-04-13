package org.moreno.cristian.repositorios;

import org.moreno.cristian.modelos.Libro;

import java.util.List;
import java.util.Optional;

public interface RepositorioLibro {

    Optional<List<Libro>> todosLibros();
    Optional<List<Libro>> todosPorAutor(String autor);

    Optional<List<Libro>> todosDisponibles();
    Optional<List<Libro>> disponiblePorNombreLibro(String nombreLibro);
    Optional<List<Libro>> disponiblesPorAutor(String nombreAutor);


    boolean guardarLibro(Libro nuevoLibro);
    boolean eliminarLibro(Libro libro);
    boolean actualizarLibro(Libro libro);
}

