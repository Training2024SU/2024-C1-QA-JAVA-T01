package co.com.pinguinera.vistas.vistas_administrativo;

import co.com.pinguinera.controladores.crud.ControladorCRUDUsuario;
import co.com.pinguinera.vistas.MenuConstantes;
import co.com.pinguinera.vistas.VistaUtil;
import java.util.logging.Logger;
import co.com.pinguinera.LoggerUtil;

public class MenuGestionUsuarios {
    private static final Logger LOGGER = LoggerUtil.getLogger();
    private final ControladorCRUDUsuario controladorCRUDUsuario;

    public MenuGestionUsuarios(ControladorCRUDUsuario controladorCRUDUsuario) {
        this.controladorCRUDUsuario = controladorCRUDUsuario;
    }

    public void mostrarMenu() {
        LOGGER.info("\nGestión de usuarios");
        LOGGER.info("1. Agregar usuario");
        LOGGER.info("2. Actualizar usuario");
        LOGGER.info("3. Eliminar usuario");
        LOGGER.info("4. Listar todos los usuarios");
        LOGGER.info("5. " + MenuConstantes.OPCION_VOLVER);

        int opcion = VistaUtil.obtenerOpcion();
        switch (opcion) {
            case 1:
                controladorCRUDUsuario.registrarUsuario();
                break;
            case 2:
                controladorCRUDUsuario.actualizarUsuario();
                break;
            case 3:
                controladorCRUDUsuario.eliminarUsuario();
                break;
            case 4:
                controladorCRUDUsuario.obtenerTodosUsuarios();
                break;
            case 5:
                return;
            default:
                LOGGER.warning(MenuConstantes.OPCION_INVALIDA);
        }
    }
}
