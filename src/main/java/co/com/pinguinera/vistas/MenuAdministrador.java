package co.com.pinguinera.vistas;

import co.com.pinguinera.controladores.MenuAdministradorControlador;
import co.com.pinguinera.interfaces.LibroRepositorio;
import co.com.pinguinera.interfaces.NovelaRepositorio;
import co.com.pinguinera.interfaces.UsuarioRepositorio;
import co.com.pinguinera.interfaces.UsuarioRolesRepositorio;

import java.util.Scanner;

public class MenuAdministrador {

    private Scanner scanner;
    private MenuAdministradorControlador menuAdministradorControlador;

    // Constructor
    public MenuAdministrador(Scanner scanner, UsuarioRepositorio usuarioRepositorio, UsuarioRolesRepositorio usuarioRolesRepositorio,
                             LibroRepositorio libroRepositorio, NovelaRepositorio novelaRepositorio) {
        this.scanner = scanner;
        this.menuAdministradorControlador = new MenuAdministradorControlador(usuarioRepositorio, usuarioRolesRepositorio,
                libroRepositorio, novelaRepositorio);
    }

    // Método para mostrar el menú del administrador
    public void mostrarMenuAdministrador() {
        while (true) {
            System.out.println("\nMenú Administrador:");
            System.out.println("1. Gestionar Asistentes");
            System.out.println("2. Gestionar Libros");
            System.out.println("3. Gestionar Novelas");
            System.out.println("4. Listar Autores");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    menuAdministradorControlador.gestionarAsistentes();
                    break;
                case 2:
                    menuAdministradorControlador.gestionarLibros();
                    break;
                case 3:
                    menuAdministradorControlador.gestionarNovelas();
                    break;
                case 4:
                    menuAdministradorControlador.listarAutores();
                    break;
                case 5:
                    System.out.println("Saliendo del menú Administrador...");
                    return;
                default:
                    System.out.println("Opción no válida, por favor intenta de nuevo.");
            }
        }
    }
}
