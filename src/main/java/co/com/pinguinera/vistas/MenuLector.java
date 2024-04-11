package co.com.pinguinera.vistas;

import co.com.pinguinera.controladores.MenuLectorControlador;
import co.com.pinguinera.modelos.interfaces.LibroRepositorio;
import co.com.pinguinera.modelos.interfaces.NovelaRepositorio;
import co.com.pinguinera.modelos.interfaces.PrestamoRepositorio;

import java.util.Scanner;

public class MenuLector {

    private final Scanner scanner;
    private final MenuLectorControlador menuLectorControlador;

    // Constructor
    public MenuLector(Scanner scanner, LibroRepositorio libroRepositorio, NovelaRepositorio novelaRepositorio,
                      PrestamoRepositorio prestamoRepositorio, int usuarioId) {
        this.scanner = scanner;
        this.menuLectorControlador = new MenuLectorControlador(libroRepositorio, novelaRepositorio,
                prestamoRepositorio, usuarioId);
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
                    menuLectorControlador.verTodosLosLibros();
                    break;
                case 2:
                    menuLectorControlador.verTodasLasNovelas();
                    break;
                case 3:
                    System.out.println("Ingrese el ID del libro o novela que desea prestar:");
                    int prestamoId = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer de entrada
                    menuLectorControlador.realizarPrestamo(prestamoId);
                    break;
                case 4:
                    System.out.println("Ingrese el ID del préstamo que desea devolver:");
                    prestamoId = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer de entrada
                    menuLectorControlador.devolverPrestamo(prestamoId);
                    break;
                case 5:
                    menuLectorControlador.verMisPrestamos();
                    break;
                case 6:
                    System.out.println("Saliendo del menú Lector...");
                    return;
                default:
                    System.out.println("Opción no válida, por favor intenta de nuevo.");
            }
        }
    }
}
