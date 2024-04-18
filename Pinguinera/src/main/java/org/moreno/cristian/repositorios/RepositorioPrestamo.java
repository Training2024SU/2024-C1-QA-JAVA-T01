package org.moreno.cristian.repositorios;

import org.moreno.cristian.modelos.Prestamo;

import java.util.List;
import java.util.Optional;

public interface RepositorioPrestamo {

    boolean guardarPrestamo(Prestamo nuevoPrestamo);
    boolean actualizarPrestamo (Prestamo prestamo);
    Optional<List<Prestamo>> listarPrestamo();

}
