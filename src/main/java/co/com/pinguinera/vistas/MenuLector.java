package co.com.pinguinera.vistas;

import co.com.pinguinera.modelos.EstadoPrestamo;
import co.com.pinguinera.modelos.Libro;
import co.com.pinguinera.modelos.Novela;
import co.com.pinguinera.modelos.Prestamo;
import co.com.pinguinera.modelos.interfaces.LibroRepositorio;
import co.com.pinguinera.modelos.interfaces.NovelaRepositorio;
import co.com.pinguinera.modelos.interfaces.PrestamoRepositorio;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MenuLector {

    private Scanner scanner;
    private LibroRepositorio libroRepositorio;
    private NovelaRepositorio novelaRepositorio;
    private PrestamoRepositorio prestamoRepositorio;
    private int usuarioId;

    // Constructor
    public MenuLector(Scanner scanner, LibroRepositorio libroRepositorio, NovelaRepositorio novelaRepositorio,
                      PrestamoRepositorio prestamoRepositorio, int usuarioId) {
        this.scanner = scanner;
        this.libroRepositorio = libroRepositorio;
        this.novelaRepositorio = novelaRepositorio;
        this.prestamoRepositorio = prestamoRepositorio;
        this.usuarioId = usuarioId;
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
            System.out.print("Elige una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

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
                case 6:
                    System.out.println("Saliendo del menú Lector...");
                    return;
                default:
                    System.out.println("Opción no válida, por favor intenta de nuevo.");
            }
        }
    }

    // Métodos para realizar operaciones del lector
    private void verTodosLosLibros() {
        List<Libro> libros = libroRepositorio.obtenerTodosLosLibros();
        if (libros.isEmpty()) {
            System.out.println("No hay libros disponibles.");
        } else {
            System.out.println("Libros disponibles:");
            for (Libro libro : libros) {
                System.out.println(libro);
            }
        }
    }

    private void verTodasLasNovelas() {
        List<Novela> novelas = novelaRepositorio.obtenerTodasLasNovelas();
        if (novelas.isEmpty()) {
            System.out.println("No hay novelas disponibles.");
        } else {
            System.out.println("Novelas disponibles:");
            for (Novela novela : novelas) {
                System.out.println(novela);
            }
        }
    }

    private void realizarPrestamo() {
        System.out.println("Ingrese el ID del libro o novela que desea prestar:");
        int prestamoId = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer de entrada

        Prestamo prestamo = new Prestamo();
        prestamoRepositorio.realizarPrestamo(prestamo);
        System.out.println("Préstamo realizado con éxito.");
    }

    private void devolverPrestamo() {
        System.out.println("Ingrese el ID del préstamo que desea devolver:");
        int prestamoId = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer de entrada

        prestamoRepositorio.devolverPrestamo(prestamoId);
        System.out.println("Préstamo devuelto con éxito.");
    }

    private void verMisPrestamos() {
        List<Prestamo> prestamos = prestamoRepositorio.listarPrestamosPorUsuario(usuarioId);
        if (prestamos.isEmpty()) {
            System.out.println("No tienes préstamos actualmente.");
        } else {
            System.out.println("Tus préstamos:");
            for (Prestamo prestamo : prestamos) {
                System.out.println(prestamo);
            }
        }
    }
}
