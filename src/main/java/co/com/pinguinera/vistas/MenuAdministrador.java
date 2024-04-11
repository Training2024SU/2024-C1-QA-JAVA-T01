package co.com.pinguinera.vistas;

import co.com.pinguinera.modelos.interfaces.LibroRepositorio;
import co.com.pinguinera.modelos.interfaces.NovelaRepositorio;
import co.com.pinguinera.modelos.interfaces.UsuarioRepositorio;
import co.com.pinguinera.modelos.interfaces.UsuarioRolesRepositorio;

import java.util.Scanner;
import java.util.List;

public class MenuAdministrador {

    private Scanner scanner;
    private UsuarioRepositorio usuarioRepositorio;
    private UsuarioRolesRepositorio usuarioRolesRepositorio;
    private LibroRepositorio libroRepositorio;
    private NovelaRepositorio novelaRepositorio;

    // Constructor
    public MenuAdministrador(Scanner scanner, UsuarioRepositorio usuarioRepositorio, UsuarioRolesRepositorio usuarioRolesRepositorio,
                             LibroRepositorio libroRepositorio, NovelaRepositorio novelaRepositorio) {
        this.scanner = scanner;
        this.usuarioRepositorio = usuarioRepositorio;
        this.usuarioRolesRepositorio = usuarioRolesRepositorio;
        this.libroRepositorio = libroRepositorio;
        this.novelaRepositorio = novelaRepositorio;
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
                    gestionarAsistentes();
                    break;
                case 2:
                    gestionarLibros();
                    break;
                case 3:
                    gestionarNovelas();
                    break;
                case 4:
                    listarAutores();
                    break;
                case 5:
                    System.out.println("Saliendo del menú Administrador...");
                    return;
                default:
                    System.out.println("Opción no válida, por favor intenta de nuevo.");
            }
        }
    }

    // Métodos para gestionar asistentes, libros, novelas y listar autores
    private void gestionarAsistentes() {
        // Aquí debes usar los métodos de UsuarioRepositorio y UsuarioRolesRepositorio
        // para agregar, actualizar o eliminar asistentes.
        // Ejemplo de uso:
        // usuarioRepositorio.agregarUsuario(nuevoAsistente);
        System.out.println("Gestión de asistentes...");
    }

    private void gestionarLibros() {
        // Aquí debes usar los métodos de LibroRepositorio para agregar,
        // actualizar o eliminar libros.
        // Ejemplo de uso:
        // libroRepositorio.agregarLibro(nuevoLibro);
        System.out.println("Gestión de libros...");
    }

    private void gestionarNovelas() {
        // Aquí debes usar los métodos de NovelaRepositorio para agregar,
        // actualizar o eliminar novelas.
        // Ejemplo de uso:
        // novelaRepositorio.agregarNovela(nuevaNovela);
        System.out.println("Gestión de novelas...");
    }

    private void listarAutores() {
        // Aquí debes usar los métodos de LibroRepositorio y NovelaRepositorio
        // para obtener la lista de autores de libros y novelas.
        // Ejemplo de uso:
        // List<String> autoresLibros = libroRepositorio.listarAutoresDeLibros();
        // List<String> autoresNovelas = novelaRepositorio.listarAutoresDeNovelas();
        System.out.println("Listando autores...");
    }
}
