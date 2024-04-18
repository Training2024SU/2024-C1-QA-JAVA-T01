package org.moreno.cristian.repositorios;

import org.moreno.cristian.modelos.Autor;

import java.util.Optional;

public interface RepositorioAutor {
    Optional<Autor> autorPorNombre(String nombreAutor);
    boolean guardarAutor(Autor autor);
}
