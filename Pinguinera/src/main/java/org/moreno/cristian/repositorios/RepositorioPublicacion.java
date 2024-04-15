package org.moreno.cristian.repositorios;

import org.moreno.cristian.modelos.Libro;
import org.moreno.cristian.modelos.Publicacion;

import java.util.Optional;

public interface RepositorioPublicacion {
    boolean guardarPublicacion(Publicacion publicacion);
    boolean eliminarPublicacion(String id);
    boolean actualizarPublicacion(Publicacion publicacion);
}
