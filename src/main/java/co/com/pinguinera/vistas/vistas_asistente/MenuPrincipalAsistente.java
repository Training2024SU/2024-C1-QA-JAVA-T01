package co.com.pinguinera.vistas.vistas_asistente;

import co.com.pinguinera.LoggerUtil;
import co.com.pinguinera.controladores.crud.ControladorCRUDLibro;
import co.com.pinguinera.controladores.crud.ControladorCRUDNovela;
import co.com.pinguinera.controladores.crud.ControladorCRUDPrestamo;
import co.com.pinguinera.vistas.MenuConstantes;
import co.com.pinguinera.vistas.VistaUtil;
import co.com.pinguinera.vistas.vistas_asistente.MenuAdministrarPrestamos;

import java.util.logging.Logger;

public class MenuPrincipalAsistente {
    private static final Logger LOGGER = LoggerUtil.getLogger(); // Usar el logger global

    private final ControladorCRUDLibro controladorCRUDLibro;
    private final ControladorCRUDNovela controladorCRUDNovela;
    private final ControladorCRUDPrestamo controladorCRUDPrestamo;
    private final MenuAdministrarPrestamos menuAdministrarPrestamos;

    public MenuPrincipalAsistente(ControladorCRUDLibro controladorCRUDLibro,
                                  ControladorCRUDNovela controladorCRUDNovela,
                                  ControladorCRUDPrestamo controladorCRUDPrestamo,
                                  MenuAdministrarPrestamos menuAdministrarPrestamos) {
        this.controladorCRUDLibro = controladorCRUDLibro;
        this.controladorCRUDNovela = controladorCRUDNovela;
        this.controladorCRUDPrestamo = controladorCRUDPrestamo;
        this.menuAdministrarPrestamos = menuAdministrarPrestamos;
    }

    public void mostrarMenu() {
        boolean continuar = true;

        while (continuar) {
            LOGGER.info("\nMenú principal de asistente");
            LOGGER.info("1. Administrar préstamos");
            LOGGER.info("2. " + MenuConstantes.VER_TODOS_PRESTAMOS);
            LOGGER.info("3. " + MenuConstantes.AGREGAR_LIBRO);
            LOGGER.info("4. " + MenuConstantes.AGREGAR_NOVELA);
            LOGGER.info("5. Salir");

            int opcion = VistaUtil.obtenerOpcion();

            switch (opcion) {
                case 1:
                    menuAdministrarPrestamos.mostrarMenu(); // Llamada al menú administrar préstamos
                    break;
                case 2:
                    controladorCRUDPrestamo.obtenerTodosPrestamos(); // Llamada al método para ver todos los préstamos
                    break;
                case 3:
                    controladorCRUDLibro.registrarLibro();
                    break;
                case 4:
                    controladorCRUDNovela.registrarNovela();
                    break;
                case 5:
                    LOGGER.info("Saliendo del menú...");
                    continuar = false;
                    break;
                default:
                    LOGGER.info(MenuConstantes.OPCION_INVALIDA);
            }
        }
    }
}
