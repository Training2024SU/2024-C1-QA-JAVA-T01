package co.com.pinguinera.vistas.vistas_administrativo;

import co.com.pinguinera.controladores.crud.ControladorCRUDNovela;
import co.com.pinguinera.vistas.MenuConstantes;
import co.com.pinguinera.vistas.VistaUtil;
import java.util.logging.Logger;
import co.com.pinguinera.LoggerUtil;

public class MenuGestionNovelas {
    private static final Logger LOGGER = LoggerUtil.getLogger();
    private final ControladorCRUDNovela controladorCRUDNovela;

    public MenuGestionNovelas(ControladorCRUDNovela controladorCRUDNovela) {
        this.controladorCRUDNovela = controladorCRUDNovela;
    }

    public void mostrarMenu() {
        LOGGER.info("\nGestión de novelas");
        LOGGER.info("1. " + MenuConstantes.AGREGAR_NOVELA);
        LOGGER.info("2. Actualizar novela");
        LOGGER.info("3. Eliminar novela");
        LOGGER.info("4. Ver todas las novelas");
        LOGGER.info("5. " + MenuConstantes.OPCION_VOLVER);

        int opcion = VistaUtil.obtenerOpcion();
        switch (opcion) {
            case 1:
                controladorCRUDNovela.registrarNovela();
                break;
            case 2:
                controladorCRUDNovela.actualizarNovela();
                break;
            case 3:
                controladorCRUDNovela.eliminarNovela();
                break;
            case 4:
                controladorCRUDNovela.obtenerTodasNovelas();
                break;
            case 5:
                return;
            default:
                LOGGER.warning(MenuConstantes.OPCION_INVALIDA);
        }
    }
}
