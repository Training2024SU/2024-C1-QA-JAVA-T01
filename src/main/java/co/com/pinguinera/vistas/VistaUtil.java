package co.com.pinguinera.vistas;

import co.com.pinguinera.LoggerUtil;
import java.util.Scanner;
import java.util.logging.Logger;

public class VistaUtil {

    // Usar el logger global desde LoggerUtil
    private static final Logger LOGGER = LoggerUtil.getLogger();
    private static final Scanner scanner = new Scanner(System.in);

    // Constructor privado para ocultar el constructor público implícito
    private VistaUtil() {
        // Este constructor se deja vacío para impedir instanciaciones de la clase
    }

    public static String pedirCorreoElectronico() {
        LOGGER.info("Por favor, introduzca su correo electrónico");
        return scanner.nextLine();
    }

    public static String pedirContrasena() {
        LOGGER.info("Por favor, introduzca su contraseña");
        return scanner.nextLine();
    }

    public static void mostrarMensajeExito() {
        LOGGER.info("Proceso Exitoso");
    }

    public static void mostrarMensajeError() {
        LOGGER.severe("Error. Por favor, inténtelo nuevamente.");
    }

    public static String pedirNombre() {
        LOGGER.info("Por favor, introduzca su nombre");
        return scanner.nextLine();
    }

    public static String pedirTitulo() {
        LOGGER.info("Por favor, introduzca el título");
        return scanner.nextLine();
    }

    public static String pedirAutor() {
        LOGGER.info("Por favor, introduzca el autor");
        return scanner.nextLine();
    }

    public static int pedirNumPaginas() {
        LOGGER.info("Por favor, introduzca el número de páginas");
        return Integer.parseInt(scanner.nextLine());
    }

    public static int pedirCantEjemplares() {
        LOGGER.info("Por favor, introduzca la cantidad de ejemplares");
        return Integer.parseInt(scanner.nextLine());
    }

    public static int pedirCantPrestados() {
        LOGGER.info("Por favor, introduzca la cantidad de ejemplares prestados");
        return Integer.parseInt(scanner.nextLine());
    }

    public static int obtenerOpcion() {
        while (true) {
            LOGGER.info("Por favor, introduzca la opción");
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                LOGGER.warning("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }
}
