package co.com.pinguinera.vistas;

import co.com.pinguinera.modelos.interfaces.LibroRepositorio;
import co.com.pinguinera.modelos.interfaces.NovelaRepositorio;
import co.com.pinguinera.modelos.interfaces.PrestamoRepositorio;

import java.util.Scanner;

public class MenuAsistente {

    private final Scanner scanner;
    private final LibroRepositorio libroRepositorio;
    private final NovelaRepositorio novelaRepositorio;
    private final PrestamoRepositorio prestamoRepositorio;

    // Constructor del menú asistente
    public MenuAsistente(Scanner scanner, LibroRepositorio libroRepositorio, NovelaRepositorio novelaRepositorio, PrestamoRepositorio prestamoRepositorio) {
        this.scanner = scanner;
        this.libroRepositorio = libroRepositorio;
        this.novelaRepositorio = novelaRepositorio;
        this.prestamoRepositorio = prestamoRepositorio;
    }

    // Método para mostrar el menú asistente
    public void mostrarMenuAsistente() {
        while (true) {
            System.out.println("\nMenú Asistente:");
            System.out.println("1. Gestionar Libros");
            System.out.println("2. Gestionar Novelas");
            System.out.println("3. Gestionar Préstamos");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    gestionarLibros();
                    break;
                case 2:
                    gestionarNovelas();
                    break;
                case 3:
                    gestionarPrestamos();
                    break;
                case 4:
                    System.out.println("Saliendo del menú asistente...");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intenta de nuevo.");
            }
        }
    }

    // Métodos para gestionar libros, novelas y préstamos
    private void gestionarLibros() {
        // Aquí puedes llamar a métodos de LibroRepositorio para gestionar libros
        System.out.println("Gestión de libros...");
        // Ejemplo: libroRepositorio.actualizarLibro(libro);
    }

    private void gestionarNovelas() {
        // Aquí puedes llamar a métodos de NovelaRepositorio para gestionar novelas
        System.out.println("Gestión de novelas...");
        // Ejemplo: novelaRepositorio.actualizarNovela(novela);
    }

    private void gestionarPrestamos() {
        // Aquí puedes llamar a métodos de PrestamoRepositorio para gestionar préstamos
        System.out.println("Gestión de préstamos...");
        // Ejemplo: prestamoRepositorio.devolverPrestamo(prestamoId);
    }
}
