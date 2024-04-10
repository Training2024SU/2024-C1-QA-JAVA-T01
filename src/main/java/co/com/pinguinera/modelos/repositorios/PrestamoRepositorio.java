package co.com.pinguinera.modelos.repositorios;

import co.com.pinguinera.modelos.EstadoPrestamo;
import co.com.pinguinera.modelos.Prestamo;

import java.util.List;
import java.util.Date;

public interface PrestamoRepositorio {
    void realizarPrestamo(Prestamo prestamo);
    void confirmarPrestamo(int prestamoId);
    void devolverPrestamo(int prestamoId);
    List<Prestamo> listarPrestamosPorUsuario(int usuarioId);
    List<Prestamo> listarPrestamosPorEstado(EstadoPrestamo estado);
    List<Prestamo> listarPrestamosPorFecha(Date fechaInicio, Date fechaFin);
}
