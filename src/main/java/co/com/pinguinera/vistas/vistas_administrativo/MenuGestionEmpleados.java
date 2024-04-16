package co.com.pinguinera.vistas.vistas_administrativo;

import co.com.pinguinera.controladores.crud.ControladorCRUDEmpleado;
import co.com.pinguinera.vistas.MenuConstantes;
import co.com.pinguinera.vistas.VistaUtil;
import java.util.logging.Logger;
import co.com.pinguinera.LoggerUtil;

public class MenuGestionEmpleados {
    private static final Logger LOGGER = LoggerUtil.getLogger();
    private final ControladorCRUDEmpleado controladorCRUDEmpleado;

    public MenuGestionEmpleados(ControladorCRUDEmpleado controladorCRUDEmpleado) {
        this.controladorCRUDEmpleado = controladorCRUDEmpleado;
    }

    public void mostrarMenu() {
        LOGGER.info("\nGestión de empleados");
        LOGGER.info("1. Agregar empleado");
        LOGGER.info("2. Actualizar empleado");
        LOGGER.info("3. Eliminar empleado");
        LOGGER.info("4. Listar todos los empleados");
        LOGGER.info("5. " + MenuConstantes.OPCION_VOLVER);

        int opcion = VistaUtil.obtenerOpcion();
        switch (opcion) {
            case 1:
                controladorCRUDEmpleado.registrarEmpleado();
                break;
            case 2:
                controladorCRUDEmpleado.actualizarEmpleado();
                break;
            case 3:
                controladorCRUDEmpleado.eliminarEmpleado();
                break;
            case 4:
                controladorCRUDEmpleado.obtenerTodosEmpleados();
                break;
            case 5:
                return;
            default:
                LOGGER.warning(MenuConstantes.OPCION_INVALIDA);
        }
    }
}
