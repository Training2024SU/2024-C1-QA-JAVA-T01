package co.com.pinguinera.vistas.vistas_administrativo;

import co.com.pinguinera.controladores.crud.ControladorCRUDPrestamo;
import co.com.pinguinera.vistas.MenuConstantes;
import co.com.pinguinera.vistas.VistaUtil;
import java.util.logging.Logger;
import co.com.pinguinera.LoggerUtil;

public class MenuGestionPrestamos {
    private static final Logger LOGGER = LoggerUtil.getLogger();
    private final ControladorCRUDPrestamo controladorCRUDPrestamo;

    public MenuGestionPrestamos(ControladorCRUDPrestamo controladorCRUDPrestamo) {
        this.controladorCRUDPrestamo = controladorCRUDPrestamo;
    }

    public void mostrarMenu() {
        LOGGER.info("\nGestión de préstamos");
        LOGGER.info("1. Registrar préstamo");
        LOGGER.info("2. Actualizar préstamo");
        LOGGER.info("3. Ver todos los préstamos");
        LOGGER.info("4. " + MenuConstantes.OPCION_VOLVER);

        int opcion = VistaUtil.obtenerOpcion();
        switch (opcion) {
            case 1:
                controladorCRUDPrestamo.registrarPrestamo();
                break;
            case 2:
                controladorCRUDPrestamo.actualizarPrestamo();
                break;
            case 3:
                controladorCRUDPrestamo.obtenerTodosPrestamos();
                break;
            case 4:
                return;
            default:
                LOGGER.warning(MenuConstantes.OPCION_INVALIDA);
        }
    }
}
