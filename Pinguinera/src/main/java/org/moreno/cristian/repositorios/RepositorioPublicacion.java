package org.moreno.cristian.repositorios;

import org.moreno.cristian.modelos.Libro;

import java.util.Optional;

public interface RepositorioPublicacion {
    boolean guardarPublicacion(Libro libro);
    boolean eliminarPublicacion(Libro libro);
    boolean actualizarPublicacion(Libro libro);
}
