package co.com.pinguinera.vistas.vistas_prestamo;

import co.com.pinguinera.datos.model.enums.EstadoPrestamo;

import java.time.LocalDate;
import java.util.Scanner;

public class InformacionPrestamoVista {

    private final Scanner scanner;

    public InformacionPrestamoVista() {
        this.scanner = new Scanner(System.in);
    }

    public LocalDate pedirFechaPrestamo() {
        System.out.print("Ingrese la fecha de préstamo (YYYY-MM-DD): ");
        return LocalDate.parse(scanner.nextLine());
    }

    public LocalDate pedirFechaDevolucion() {
        System.out.print("Ingrese la fecha de devolución (YYYY-MM-DD): ");
        return LocalDate.parse(scanner.nextLine());
    }

    public EstadoPrestamo pedirEstadoPrestamo() {
        System.out.println("Seleccione el estado del préstamo:");
        System.out.println("1. SOLICITADO");
        System.out.println("2. REALIZADO");
        System.out.println("3. FINALIZADO");
        System.out.print("Ingrese el número correspondiente al estado: ");
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
        System.out.print("Ingrese el ID del usuario: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int pedirIdPublicacion() {
        System.out.print("Ingrese el ID de la publicación: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int pedirIdPrestamo() {
        System.out.print("Ingrese el ID del prestamo: ");
        return Integer.parseInt(scanner.nextLine());
    }
}
