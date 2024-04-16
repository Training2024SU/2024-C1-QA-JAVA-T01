package co.com.pinguinera.vistas.vistas_prestamo;

import co.com.pinguinera.datos.model.enums.EstadoPrestamo;
import co.com.pinguinera.LoggerUtil;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Logger;

public class InformacionPrestamoVista {

    private final Scanner scanner;
    private final Logger logger;

    public InformacionPrestamoVista() {
        this.scanner = new Scanner(System.in);
        this.logger = LoggerUtil.getLogger(); // Obtiene el logger global
    }

    public LocalDate pedirFechaPrestamo() {
        logger.info("Ingrese la fecha de préstamo (YYYY-MM-DD): ");
        return LocalDate.parse(scanner.nextLine());
    }

    public LocalDate pedirFechaDevolucion() {
        logger.info("Ingrese la fecha de devolución (YYYY-MM-DD): ");
        return LocalDate.parse(scanner.nextLine());
    }

    public EstadoPrestamo pedirEstadoPrestamo() {
        logger.info("Seleccione el estado del préstamo:");
        logger.info("1. SOLICITADO");
        logger.info("2. REALIZADO");
        logger.info("3. FINALIZADO");
        logger.info("Ingrese el número correspondiente al estado: ");
        int opcion = Integer.parseInt(scanner.nextLine());

        switch (opcion) {
            case 1:
                return EstadoPrestamo.SOLICITADO;
            case 2:
                return EstadoPrestamo.REALIZADO;
            case 3:
                return EstadoPrestamo.FINALIZADO;
            default:
                throw new IllegalArgumentException("Opción inválida.");
        }
    }
    public int pedirIdUsuario() {
        logger.info("Ingrese el ID del usuario: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int pedirIdPublicacion() {
        logger.info("Ingrese el ID de la publicación: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int pedirIdPrestamo() {
        logger.info("Ingrese el ID del préstamo: ");
        return Integer.parseInt(scanner.nextLine());
    }
}
