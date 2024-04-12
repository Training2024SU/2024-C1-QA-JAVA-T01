package org.moreno.cristian.repositorios;

import org.moreno.cristian.modelos.Libro;

import java.util.ArrayList;
import java.util.Optional;

public interface RepositorioLibro {

    Optional<ArrayList<Libro>> todosLibros();
    Optional<ArrayList<Libro>> todosPorAutor(String autor);

    Optional<ArrayList<Libro>> todosDisponibles();
    Optional<Libro> disponiblePorNombre(String nombreLibro);
    Optional<ArrayList<Libro>> disponiblesPorAutor(String nombreAutor);


    Optional<Libro> guardarLibro(Libro nuevoLibro);
    Optional<Libro> eliminarLibro(Libro libro);
    Optional<Libro> actualizarLibro(Libro libro);
}

