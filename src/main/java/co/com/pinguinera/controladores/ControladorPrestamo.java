package co.com.pinguinera.controladores;

import co.com.pinguinera.datos.DAO.PrestamoDAO;
import co.com.pinguinera.datos.model.Prestamo;
import co.com.pinguinera.datos.model.enums.EstadoPrestamo;
import co.com.pinguinera.vistas.VistaUtil;

import java.sql.SQLException;
import java.util.List;

public class ControladorPrestamo {

    private final PrestamoDAO prestamoDAO;

    public ControladorPrestamo(PrestamoDAO prestamoDAO) {
        this.prestamoDAO = prestamoDAO;
    }

    public void consultarPrestamosPorCorreo() {
        String correo = VistaUtil.pedirCorreoElectronico();
        try {
            List<Prestamo> prestamos = prestamoDAO.consultarPrestamosPorCorreo(correo);
            if (prestamos.isEmpty()) {
                VistaUtil.mostrarMensajeError();
            } else {
                for (Prestamo prestamo : prestamos) {
                    System.out.println(prestamo);
                }
            }
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
        }
    }

    public void cambiarEstadoPrestamo() {
        int idPrestamo = VistaUtil.pedirIdPrestamo();
        EstadoPrestamo nuevoEstado = EstadoPrestamo.valueOf(VistaUtil.pedirNuevoEstado());
        try {
            Prestamo prestamo = prestamoDAO.obtenerPorId(idPrestamo);
            if (prestamo == null) {
                VistaUtil.mostrarMensajeError();
                return;
            }

            EstadoPrestamo estadoActual = prestamo.getEstado();
            prestamoDAO.cambiarEstadoPrestamo(idPrestamo, nuevoEstado);

            if (estadoActual == EstadoPrestamo.SOLICITADO && nuevoEstado == EstadoPrestamo.REALIZADO) {
                prestamoDAO.actualizarCantidadLibrosDisponibles(idPrestamo, EstadoPrestamo.REALIZADO);
            } else if (estadoActual == EstadoPrestamo.REALIZADO && nuevoEstado == EstadoPrestamo.FINALIZADO) {
                prestamoDAO.actualizarCantidadLibrosDisponibles(idPrestamo, EstadoPrestamo.FINALIZADO);
            }

            VistaUtil.mostrarMensajeExito();
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
        }
    }
}
