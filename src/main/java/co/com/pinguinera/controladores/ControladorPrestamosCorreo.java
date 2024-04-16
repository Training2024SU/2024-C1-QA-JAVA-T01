package co.com.pinguinera.controladores;

import co.com.pinguinera.datos.DAO.PrestamoDAO;
import co.com.pinguinera.datos.DAO.UsuarioDAO;
import co.com.pinguinera.datos.model.Prestamo;
import co.com.pinguinera.servicios.GestorPrestamos;
import co.com.pinguinera.vistas.VistaUtil;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ControladorPrestamosCorreo {
    private final GestorPrestamos gestorPrestamos;
    private static final Logger LOGGER = Logger.getLogger(ControladorPrestamosCorreo.class.getName());

    public ControladorPrestamosCorreo(UsuarioDAO usuarioDAO, PrestamoDAO prestamoDAO) {
        this.gestorPrestamos = new GestorPrestamos(usuarioDAO, prestamoDAO);
    }

    /**
     * Consulta y muestra los préstamos relacionados con un usuario dado su correo electrónico.
     */
    public void consultarPrestamosPorCorreo() {
        String correo = VistaUtil.pedirCorreoElectronico();
        List<Prestamo> prestamos = gestorPrestamos.listarPrestamosPorCorreo(correo);

        if (prestamos.isEmpty()) {
            LOGGER.info("No se encontraron préstamos para el usuario con correo: " + correo);
        } else {
            // Mostrar los préstamos encontrados
            LOGGER.info("Préstamos encontrados para el usuario con correo: " + correo);
            for (Prestamo prestamo : prestamos) {
                LOGGER.info(prestamo.toString());
            }
        }
    }

    /**
     * Relaciona usuarios con sus préstamos y los almacena en un mapa.
     * @return Un mapa donde la clave es el correo de un usuario y el valor es una lista de préstamos de ese usuario.
     */
    public Map<String, List<Prestamo>> relacionarUsuariosYPrestamos() {
        gestorPrestamos.cargarDatos();

        return gestorPrestamos.relacionarUsuariosYPrestamos();
    }
}
