package co.com.pinguinera.vistas;

import co.com.pinguinera.controladores.MenuLectorControlador;
import co.com.pinguinera.interfaces.LibroRepositorio;
import co.com.pinguinera.interfaces.NovelaRepositorio;
import co.com.pinguinera.interfaces.PrestamoRepositorio;
import co.com.pinguinera.modelos.Prestamo;

import java.sql.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuLector {

    private static final int VER_LIBROS = 1;
    private static final int VER_NOVELAS = 2;
    private static final int REALIZAR_PRESTAMO = 3;
    private static final int DEVOLVER_PRESTAMO = 4;
    private static final int VER_PRESTAMOS = 5;
    private static final int SALIR = 6;

    private final Scanner scanner;
    private final MenuLectorControlador menuLectorControlador;

    // Constructor
    public MenuLector(Scanner scanner, LibroRepositorio libroRepositorio, NovelaRepositorio novelaRepositorio,
                      PrestamoRepositorio prestamoRepositorio, int usuarioId) {
        this.scanner = scanner;

        // Verifica que los repositorios no sean null
        if (prestamoRepositorio == null) {
            throw new IllegalArgumentException("PrestamoRepositorio no puede ser null");
        }

        this.menuLectorControlador = new MenuLectorControlador(libroRepositorio, novelaRepositorio, prestamoRepositorio, usuarioId);
    }

    // Método para mostrar el menú del lector
    public void mostrarMenuLector() {
        while (true) {
            System.out.println("\nMenú Lector:");
            System.out.println("1. Ver todos los libros");
            System.out.println("2. Ver todas las novelas");
            System.out.println("3. Realizar un préstamo");
            System.out.println("4. Devolver un préstamo");
            System.out.println("5. Ver mis préstamos");
            System.out.println("6. Salir");
            System.out.println("Elige una opción:");

            int opcion;

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer de entrada

                switch (opcion) {
                    case VER_LIBROS:
                        menuLectorControlador.verTodosLosLibros();
                        break;
                    case VER_NOVELAS:
                        menuLectorControlador.verTodasLasNovelas();
                        break;
                    case REALIZAR_PRESTAMO:
                        System.out.println("Ingrese el ID del libro o novela que desea prestar:");
                        int itemId = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer de entrada

                        System.out.println("Ingrese el tipo (LIBRO o NOVELA):");
                        String tipo = scanner.nextLine();

                        System.out.println("Ingrese la fecha de préstamo (formato yyyy-MM-dd):");
                        String fechaPrestamoStr = scanner.nextLine();
                        Date fechaPrestamo = Date.valueOf(fechaPrestamoStr);

                        System.out.println("Ingrese la fecha de devolución (formato yyyy-MM-dd) o déjela en blanco si no se conoce:");
                        String fechaDevolucionStr = scanner.nextLine();
                        Date fechaDevolucion = fechaDevolucionStr.isEmpty() ? null : Date.valueOf(fechaDevolucionStr);

                        // Crear un objeto Prestamo con los datos recopilados
                        Prestamo prestamo = new Prestamo();
                        prestamo.setUsuarioID(menuLectorControlador.getUsuarioId());
                        prestamo.setItemID(itemId);
                        prestamo.setTipo(tipo);
                        prestamo.setFechaPrestamo(fechaPrestamo);
                        prestamo.setFechaDevolucion(fechaDevolucion);
                        prestamo.setEstado("SOLICITADO");

                        // Llamar a realizarPrestamo
                        menuLectorControlador.realizarPrestamo(prestamo);
                        break;
                    case DEVOLVER_PRESTAMO:
                        System.out.println("Ingrese el ID del préstamo que desea devolver:");
                        int prestamoId = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer de entrada
                        menuLectorControlador.devolverPrestamo(prestamoId);
                        break;
                    case VER_PRESTAMOS:
                        menuLectorControlador.verMisPrestamos();
                        break;
                    case SALIR:
                        System.out.println("Saliendo del menú Lector...");
                        return;
                    default:
                        System.out.println("Opción no válida. Por favor, inténtalo de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingresa un número.");
                scanner.nextLine(); // Limpiar el buffer
            }
        }
    }
}
