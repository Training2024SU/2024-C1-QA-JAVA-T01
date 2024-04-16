package co.com.pinguinera.vistas.vistas_administrativo;

import co.com.pinguinera.controladores.crud.ControladorCRUDLibro;
import co.com.pinguinera.vistas.MenuConstantes;
import co.com.pinguinera.vistas.VistaUtil;
import java.util.logging.Logger;
import co.com.pinguinera.LoggerUtil;

public class MenuGestionLibros {
    private static final Logger LOGGER = LoggerUtil.getLogger();
    private final ControladorCRUDLibro controladorCRUDLibro;

    public MenuGestionLibros(ControladorCRUDLibro controladorCRUDLibro) {
        this.controladorCRUDLibro = controladorCRUDLibro;
    }

    public void mostrarMenu() {
        LOGGER.info("\nGestión de libros");
        LOGGER.info("1. " + MenuConstantes.AGREGAR_LIBRO);
        LOGGER.info("2. " + MenuConstantes.ACTUALIZAR_LIBRO);
        LOGGER.info("3. " + MenuConstantes.ELIMINAR_LIBRO);
        LOGGER.info("4. Ver todos los libros");
        LOGGER.info("5. " + MenuConstantes.OPCION_VOLVER);

        int opcion = VistaUtil.obtenerOpcion();
        switch (opcion) {
            case 1:
                controladorCRUDLibro.registrarLibro();
                break;
            case 2:
                controladorCRUDLibro.actualizarLibro();
                break;
            case 3:
                controladorCRUDLibro.eliminarLibro();
                break;
            case 4:
                controladorCRUDLibro.obtenerTodosLibros();
                break;
            case 5:
                return;
            default:
                LOGGER.warning(MenuConstantes.OPCION_INVALIDA);
        }
    }
}
