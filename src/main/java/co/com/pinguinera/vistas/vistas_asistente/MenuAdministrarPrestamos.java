package co.com.pinguinera.vistas.vistas_asistente;

import co.com.pinguinera.LoggerUtil;
import co.com.pinguinera.controladores.ControladorPrestamo;
import co.com.pinguinera.controladores.crud.ControladorCRUDPrestamo;
import co.com.pinguinera.vistas.MenuConstantes;
import co.com.pinguinera.vistas.VistaUtil;

import java.util.logging.Logger;

public class MenuAdministrarPrestamos {

    private static final Logger LOGGER = LoggerUtil.getLogger(); // Usar el logger global

    private final ControladorCRUDPrestamo controladorCRUDPrestamo;
    private final ControladorPrestamo controladorPrestamo;

    public MenuAdministrarPrestamos(ControladorCRUDPrestamo controladorCRUDPrestamo,
                                    ControladorPrestamo controladorPrestamo) {
        this.controladorCRUDPrestamo = controladorCRUDPrestamo;
        this.controladorPrestamo = controladorPrestamo;
    }

    public void mostrarMenu() {
        boolean continuar = true;

        while (continuar) {
            LOGGER.info("\nAdministrar Préstamos");
            LOGGER.info("1. Listar todos los préstamos");
            LOGGER.info("2. Registrar nuevo préstamo");
            LOGGER.info("3. Eliminar préstamo");
            LOGGER.info("4. Actualizar préstamo");
            LOGGER.info("5. Consultar préstamos por correo"); // Renombrada opción
            LOGGER.info("6. Cambiar estado de préstamo"); // Opción agregada
            LOGGER.info("7. Volver al menú anterior");

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
                    controladorPrestamo.consultarPrestamosPorCorreo(); // Invoca el método de ControladorPrestamo
                    break;
                case 6:
                    controladorPrestamo.cambiarEstadoPrestamo(); // Invoca el método de ControladorPrestamo
                    break;
                case 7:
                    LOGGER.info("Volviendo al menú anterior...");
                    continuar = false;
                    break;
                default:
                    LOGGER.info(MenuConstantes.OPCION_INVALIDA);
            }
        }
    }
}
