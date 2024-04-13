package co.com.pinguinera.capa_servicios;

import co.com.pinguinera.capa_datos.PrestamoDAO;
import co.com.pinguinera.modelado.Prestamo;

import java.sql.SQLException;
import java.util.List;

public class PrestamoServicio {
    private PrestamoDAO prestamoDAO;

    public PrestamoServicio() {
        this.prestamoDAO = new PrestamoDAO();
    }

    // Obtener todos los préstamos
    public List<Prestamo> obtenerTodosLosPrestamos() throws SQLException {
        return prestamoDAO.obtenerTodosLosPrestamos();
    }

    // Filtrar préstamos por correo de usuario
    /*
    public List<Prestamo> filtrarPrestamosPorUsuario(String correoUsuario) throws SQLException {
        // Implementar este método
        return null;
    }
    */

    // Filtrar préstamos por estado
    /*
    public List<Prestamo> filtrarPrestamosPorEstado(String estado) throws SQLException {
        // Implementar este método
        return null;
    }
    */

    // Otros métodos de lógica de negocio pueden ir aquí
    // Por ejemplo, podrías agregar métodos como eliminarPrestamo, actualizarPrestamo, etc.
}
