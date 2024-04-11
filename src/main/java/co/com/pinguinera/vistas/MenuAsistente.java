package co.com.pinguinera.vistas;

import co.com.pinguinera.controladores.MenuAsistenteControlador;
import co.com.pinguinera.interfaces.LibroRepositorio;
import co.com.pinguinera.interfaces.NovelaRepositorio;
import co.com.pinguinera.interfaces.PrestamoRepositorio;

import java.util.Scanner;

public class MenuAsistente {

    private final Scanner scanner;
    private final MenuAsistenteControlador menuAsistenteControlador;

    // Constructor del menú asistente
    public MenuAsistente(Scanner scanner, LibroRepositorio libroRepositorio, NovelaRepositorio novelaRepositorio,
                         PrestamoRepositorio prestamoRepositorio) {
        this.scanner = scanner;
        this.menuAsistenteControlador = new MenuAsistenteControlador(libroRepositorio, novelaRepositorio, prestamoRepositorio);
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
                    menuAsistenteControlador.gestionarLibros();
                    break;
                case 2:
                    menuAsistenteControlador.gestionarNovelas();
                    break;
                case 3:
                    menuAsistenteControlador.gestionarPrestamos();
                    break;
                case 4:
                    System.out.println("Saliendo del menú asistente...");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intenta de nuevo.");
            }
        }
    }
}
