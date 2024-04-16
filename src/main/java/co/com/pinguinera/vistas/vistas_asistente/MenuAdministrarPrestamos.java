package co.com.pinguinera.vistas.vistas_asistente;

import co.com.pinguinera.LoggerUtil;
import co.com.pinguinera.controladores.ControladorPrestamosCorreo;
import co.com.pinguinera.controladores.crud.ControladorCRUDPrestamo;
import co.com.pinguinera.datos.DAO.UsuarioDAO;
import co.com.pinguinera.datos.DAO.PrestamoDAO;
import co.com.pinguinera.vistas.MenuConstantes;
import co.com.pinguinera.vistas.VistaUtil;

import java.util.logging.Logger;

public class MenuAdministrarPrestamos {

    private static final Logger LOGGER = LoggerUtil.getLogger(); // Usar el logger global

    private final ControladorCRUDPrestamo controladorCRUDPrestamo;
    private final ControladorPrestamosCorreo controladorPrestamosCorreo;

    public MenuAdministrarPrestamos(ControladorCRUDPrestamo controladorCRUDPrestamo, UsuarioDAO usuarioDAO, PrestamoDAO prestamosDAO) {
        this.controladorCRUDPrestamo = controladorCRUDPrestamo;
        this.controladorPrestamosCorreo = new ControladorPrestamosCorreo(usuarioDAO, prestamosDAO);
    }

    public void mostrarMenu() {
        boolean continuar = true;

        while (continuar) {
            LOGGER.info("\nAdministrar Préstamos");
            LOGGER.info("1. Listar todos los préstamos");
            LOGGER.info("2. Registrar nuevo préstamo");
            LOGGER.info("3. Eliminar préstamo");
            LOGGER.info("4. Actualizar préstamo");
            LOGGER.info("5. Listar préstamos por correo");
            LOGGER.info("6. Volver al menú anterior");

            int opcion = VistaUtil.obtenerOpcion();

            switch (opcion) {
                case 1:
                    controladorCRUDPrestamo.obtenerTodosPrestamos();
                    break;
                case 2:
                    controladorCRUDPrestamo.registrarPrestamo();
                    break;
                case 3:
                    controladorCRUDPrestamo.eliminarPrestamo();
                    break;
                case 4:
                    controladorCRUDPrestamo.actualizarPrestamo();
                    break;
                case 5:
                    // Invoca el método `consultarPrestamosPorCorreo` del `ControladorPrestamosCorreo`
                    controladorPrestamosCorreo.consultarPrestamosPorCorreo();
                    break;
                case 6:
                    LOGGER.info("Volviendo al menú anterior...");
                    continuar = false;
                    break;
                default:
                    LOGGER.info(MenuConstantes.OPCION_INVALIDA);
            }
        }
    }
}
