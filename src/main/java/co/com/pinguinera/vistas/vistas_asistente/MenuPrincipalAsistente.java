package co.com.pinguinera.vistas.vistas_asistente;

import co.com.pinguinera.LoggerUtil;
import co.com.pinguinera.controladores.crud.ControladorCRUDLibro;
import co.com.pinguinera.controladores.crud.ControladorCRUDNovela;
import co.com.pinguinera.controladores.crud.ControladorCRUDPrestamo;
import co.com.pinguinera.vistas.VistaUtil;

import java.util.logging.Logger;

public class MenuPrincipalAsistente {
    private static final Logger LOGGER = LoggerUtil.getLogger(); // Usar el logger global

    private final ControladorCRUDLibro controladorCRUDLibro;
    private final ControladorCRUDNovela controladorCRUDNovela;
    private final ControladorCRUDPrestamo controladorCRUDPrestamo;

    public MenuPrincipalAsistente(ControladorCRUDLibro controladorCRUDLibro,
                                  ControladorCRUDNovela controladorCRUDNovela,
                                  ControladorCRUDPrestamo controladorCRUDPrestamo) {
        this.controladorCRUDLibro = controladorCRUDLibro;
        this.controladorCRUDNovela = controladorCRUDNovela;
        this.controladorCRUDPrestamo = controladorCRUDPrestamo;
    }

    public void mostrarMenu() {
        boolean continuar = true;

        while (continuar) {
            LOGGER.info("\nMenú principal de asistente");
            LOGGER.info("1. Ver todos los libros");
            LOGGER.info("2. Ver todas las novelas");
            LOGGER.info("3. Ver todos los préstamos");
            LOGGER.info("4. Agregar libro");
            LOGGER.info("5. Agregar novela");
            LOGGER.info("6. Eliminar libro");
            LOGGER.info("7. Eliminar novela");
            LOGGER.info("8. Actualizar libro");
            LOGGER.info("9. Actualizar novela");
            LOGGER.info("10. Salir");

            int opcion = VistaUtil.obtenerOpcion();

            switch (opcion) {
                case 1:
                    controladorCRUDLibro.obtenerTodosLibros();
                    break;
                case 2:
                    controladorCRUDNovela.obtenerTodasNovelas();
                    break;
                case 3:
                    controladorCRUDPrestamo.obtenerTodosPrestamos();
                    break;
                case 4:
                    controladorCRUDLibro.registrarLibro();
                    break;
                case 5:
                    controladorCRUDNovela.registrarNovela();
                    break;
                case 6:
                    controladorCRUDLibro.eliminarLibro();
                    break;
                case 7:
                    controladorCRUDNovela.eliminarNovela();
                    break;
                case 8:
                    controladorCRUDLibro.actualizarLibro();
                    break;
                case 9:
                    controladorCRUDNovela.actualizarNovela();
                    break;
                case 10:
                    LOGGER.info("Saliendo del menú...");
                    continuar = false;
                    break;
                default:
                    LOGGER.warning("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }
}
