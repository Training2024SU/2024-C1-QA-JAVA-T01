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

    private final Scanner scanner;
    private final MenuLectorControlador menuLectorControlador;

    public MenuLector(Scanner scanner, LibroRepositorio libroRepositorio, NovelaRepositorio novelaRepositorio, PrestamoRepositorio prestamoRepositorio, int usuarioId) {
        this.scanner = scanner;
        this.menuLectorControlador = new MenuLectorControlador(libroRepositorio, novelaRepositorio, prestamoRepositorio, usuarioId);
    }

    public void mostrarMenuLector() {
        while (true) {
            mostrarOpcionesMenu();
            try {
                int opcion = obtenerOpcionUsuario();
                manejarOpcionSeleccionada(opcion);
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                scanner.nextLine(); // Limpiar el buffer de entrada
            }
        }
    }

    private void mostrarOpcionesMenu() {
        System.out.println("\nMenú Lector:");
        System.out.println("1. Ver todos los libros");
        System.out.println("2. Ver todas las novelas");
        System.out.println("3. Realizar un préstamo");
        System.out.println("4. Devolver un préstamo");
        System.out.println("5. Ver mis préstamos");
        System.out.println("6. Buscar novela por título");
        System.out.println("7. Buscar libro por título");
        System.out.println("8. Listar autores de novelas");
        System.out.println("9. Listar autores de libros");
        System.out.println("10. Salir");
        System.out.print("Elige una opción: ");
    }

    private int obtenerOpcionUsuario() {
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        return opcion;
    }

    private void manejarOpcionSeleccionada(int opcion) {
        switch (opcion) {
            case 1:
                verTodosLosLibros();
                break;
            case 2:
                verTodasLasNovelas();
                break;
            case 3:
                realizarPrestamo();
                break;
            case 4:
                devolverPrestamo();
                break;
            case 5:
                verMisPrestamos();
                break;
            case 7:
                buscarNovelaPorTitulo();
                break;
            case 8:
                buscarLibroPorTitulo();
                break;
            case 9:
                listarAutoresDeNovelas();
                break;
            case 10:
                listarAutoresDeLibros();
                break;
            case 6:
                System.out.println("Saliendo del menú Lector...");
                return;
            default:
                System.out.println("Opción no válida. Por favor, inténtalo de nuevo.");
        }
    }

    private void verTodosLosLibros() {
        menuLectorControlador.verTodosLosLibros();
        System.out.println("Lista de libros mostrada exitosamente.");
    }

    private void verTodasLasNovelas() {
        menuLectorControlador.verTodasLasNovelas();
        System.out.println("Lista de novelas mostrada exitosamente.");
    }

    private void realizarPrestamo() {
        System.out.print("Ingrese el ID del libro o novela que desea prestar: ");
        int itemId = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        System.out.print("Ingrese el tipo (LIBRO o NOVELA): ");
        String tipo = scanner.nextLine();

        System.out.print("Ingrese la fecha de préstamo (formato yyyy-MM-dd): ");
        String fechaPrestamoStr = scanner.nextLine();
        Date fechaPrestamo = Date.valueOf(fechaPrestamoStr);

        System.out.print("Ingrese la fecha de devolución (formato yyyy-MM-dd) o déjela en blanco si no se conoce: ");
        String fechaDevolucionStr = scanner.nextLine();
        Date fechaDevolucion = fechaDevolucionStr.isEmpty() ? null : Date.valueOf(fechaDevolucionStr);

        Prestamo prestamo = new Prestamo();
        prestamo.setUsuarioID(menuLectorControlador.getUsuarioId());
        prestamo.setItemID(itemId);
        prestamo.setTipo(tipo);
        prestamo.setFechaPrestamo(fechaPrestamo);
        prestamo.setFechaDevolucion(fechaDevolucion);
        prestamo.setEstado("SOLICITADO");

        menuLectorControlador.realizarPrestamo(prestamo);
        System.out.println("Préstamo realizado exitosamente.");
    }

    private void devolverPrestamo() {
        System.out.print("Ingrese el ID del préstamo que desea devolver: ");
        int prestamoId = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        menuLectorControlador.devolverPrestamo(prestamoId);
        System.out.println("Préstamo devuelto exitosamente.");
    }

    private void verMisPrestamos() {
        menuLectorControlador.verMisPrestamos();
        System.out.println("Lista de préstamos mostrada exitosamente.");
    }

    private void buscarNovelaPorTitulo() {
        System.out.print("Ingrese el título de la novela: ");
        String tituloNovela = scanner.nextLine();
        menuLectorControlador.buscarNovelaPorTitulo(tituloNovela);
    }

    private void buscarLibroPorTitulo() {
        System.out.print("Ingrese el título del libro: ");
        String tituloLibro = scanner.nextLine();
        menuLectorControlador.buscarLibroPorTitulo(tituloLibro);
    }

    private void listarAutoresDeNovelas() {
        menuLectorControlador.listarAutoresDeNovelas();
    }

    private void listarAutoresDeLibros() {
        menuLectorControlador.listarAutoresDeLibros();
    }
}
