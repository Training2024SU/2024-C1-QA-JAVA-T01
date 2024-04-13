package co.com.pinguinera.vistas;

import co.com.pinguinera.controladores.GestionarUsuariosAdministradorControlador;
import co.com.pinguinera.controladores.MenuAdministradorControlador;
import co.com.pinguinera.interfaces.LibroRepositorio;
import co.com.pinguinera.interfaces.NovelaRepositorio;
import co.com.pinguinera.interfaces.RolesRepositorio;
import co.com.pinguinera.interfaces.UsuarioRepositorio;

import java.util.Scanner;

public class MenuAdministrador {

    private final Scanner scanner;
    private final MenuAdministradorControlador menuAdministradorControlador;
    private final GestionarUsuariosAdministradorControlador gestionarUsuariosAdministradorControlador; // Declaración
    private final LibroRepositorio libroRepositorio;
    private final NovelaRepositorio novelaRepositorio;

    // Constructor
    public MenuAdministrador(Scanner scanner, UsuarioRepositorio usuarioRepositorio, RolesRepositorio rolesRepositorio,
                             LibroRepositorio libroRepositorio, NovelaRepositorio novelaRepositorio) {
        this.scanner = scanner;
        this.menuAdministradorControlador = new MenuAdministradorControlador(usuarioRepositorio, rolesRepositorio);
        this.gestionarUsuariosAdministradorControlador = new GestionarUsuariosAdministradorControlador(usuarioRepositorio, rolesRepositorio, scanner);
        this.libroRepositorio = libroRepositorio;
        this.novelaRepositorio = novelaRepositorio;
    }

    // Método para mostrar el menú del administrador
    public void mostrarMenuAdministrador() {
        while (true) {
            System.out.println("\nMenú Administrador:");
            System.out.println("1. Agregar Usuario");
            System.out.println("2. Actualizar Usuario");
            System.out.println("3. Eliminar Usuario");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    agregarUsuario();
                    break;

                case 2:
                    actualizarUsuario();
                    break;

                case 3:
                    eliminarUsuario();
                    break;

                case 5:
                    System.out.println("Saliendo del menú Administrador...");
                    return;

                default:
                    System.out.println("Opción no válida, por favor intenta de nuevo.");
            }
        }
    }

    // Método para agregar un usuario
    private void agregarUsuario() {
        // Llama al método de registrar usuario con rol del controlador correspondiente
        gestionarUsuariosAdministradorControlador.registrarUsuarioConRol();
    }

    // Método para actualizar un usuario
    private void actualizarUsuario() {
        System.out.print("Ingresa el ID del usuario a actualizar: ");
        int idUsuario = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        // Recolectar datos del usuario a actualizar usando GestionarUsuariosAdministradorControlador
        Usuario usuarioActualizado = gestionarUsuariosAdministradorControlador.recolectarDatosUsuario();

        // Establecer el ID del usuario a actualizar
        usuarioActualizado.setUsuarioID(idUsuario);

        // Llamar al controlador para actualizar el usuario
        menuAdministradorControlador.actualizarUsuario(usuarioActualizado);
        System.out.println("Usuario actualizado exitosamente.");
    }

    // Método para eliminar un usuario
    private void eliminarUsuario() {
        System.out.print("Ingresa el ID del usuario a eliminar: ");
        int idUsuario = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        menuAdministradorControlador.eliminarUsuario(idUsuario);
        System.out.println("Usuario eliminado exitosamente.");
    }
}
