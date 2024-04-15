package org.moreno.cristian.repositorios;

import org.moreno.cristian.modelos.Libro;
import org.moreno.cristian.modelos.Novela;

import java.util.List;
import java.util.Optional;

public interface RepositorioNovela {
    Optional<List<Novela>> todasNovelas();
    Optional<List<Novela>> todasPorAutor(String autor);

    Optional<List<Novela>> todasDisponibles();
    Optional<List<Novela>> disponiblePorNombreNovela(String nombreNovela);
    Optional<List<Novela>> disponiblesPorAutor(String nombreAutor);


    boolean guardarNovela(Novela nuevaNovela);
    boolean eliminarNovela(Novela novela);
    boolean actualizarNovela(Novela novela);
}
