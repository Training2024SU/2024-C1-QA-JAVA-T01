package co.com.pinguinera.vistas.vistas_administrativo;

import co.com.pinguinera.controladores.crud.*;
import co.com.pinguinera.vistas.MenuConstantes;
import co.com.pinguinera.vistas.VistaUtil;
import co.com.pinguinera.LoggerUtil;
import java.util.logging.Logger;

public class MenuPrincipalAdministrativo {
    private static final Logger LOGGER = LoggerUtil.getLogger();

    private final MenuGestionUsuarios menuGestionUsuarios;
    private final MenuGestionPrestamos menuGestionPrestamos;
    private final MenuGestionEmpleados menuGestionEmpleados;
    private final MenuGestionNovelas menuGestionNovelas;
    private final MenuGestionLibros menuGestionLibros;

    public MenuPrincipalAdministrativo(ControladorCRUDUsuario controladorCRUDUsuario,
                                       ControladorCRUDPrestamo controladorCRUDPrestamo,
                                       ControladorCRUDEmpleado controladorCRUDEmpleado,
                                       ControladorCRUDNovela controladorCRUDNovela,
                                       ControladorCRUDLibro controladorCRUDLibro) {
        this.menuGestionUsuarios = new MenuGestionUsuarios(controladorCRUDUsuario);
        this.menuGestionPrestamos = new MenuGestionPrestamos(controladorCRUDPrestamo);
        this.menuGestionEmpleados = new MenuGestionEmpleados(controladorCRUDEmpleado);
        this.menuGestionNovelas = new MenuGestionNovelas(controladorCRUDNovela);
        this.menuGestionLibros = new MenuGestionLibros(controladorCRUDLibro);
    }

    public void mostrarMenu() {
        boolean continuar = true;

        while (continuar) {
            LOGGER.info("\nMenú administrativo");
            LOGGER.info("1. Gestión de usuarios");
            LOGGER.info("2. Gestión de préstamos");
            LOGGER.info("3. Gestión de empleados");
            LOGGER.info("4. Gestión de novelas");
            LOGGER.info("5. Gestión de libros");
            LOGGER.info("6. " + MenuConstantes.SALIR);

            int opcion = VistaUtil.obtenerOpcion();

            switch (opcion) {
                case 1:
                    menuGestionUsuarios.mostrarMenu();
                    break;
                case 2:
                    menuGestionPrestamos.mostrarMenu();
                    break;
                case 3:
                    menuGestionEmpleados.mostrarMenu();
                    break;
                case 4:
                    menuGestionNovelas.mostrarMenu();
                    break;
                case 5:
                    menuGestionLibros.mostrarMenu();
                    break;
                case 6:
                    LOGGER.info(MenuConstantes.OPCION_VOLVER);
                    continuar = false;
                    break;
                default:
                    LOGGER.warning(MenuConstantes.OPCION_INVALIDA);
            }
        }
    }
}
