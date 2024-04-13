package co.com.pinguinera.interfaces;

import java.util.List;
import java.util.Date;

public interface PrestamoRepositorio {
    void realizarPrestamo(Prestamo prestamo);
    void confirmarPrestamo(int prestamoId);
    void devolverPrestamo(int prestamoId);
    List<Prestamo> listarPrestamosPorUsuario(int usuarioId);
    List<Prestamo> listarPrestamosPorEstado(String estado); // Cambiado a String
    List<Prestamo> listarPrestamosPorFecha(Date fechaInicio, Date fechaFin);
}
