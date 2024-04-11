package co.com.pinguinera.vistas;

import co.com.pinguinera.controladores.IniciarSesionControlador;
import co.com.pinguinera.controladores.RegistrarUsuarioControlador;
import co.com.pinguinera.DAO.AutenticacionDAO;
import co.com.pinguinera.interfaces.LibroRepositorio;
import co.com.pinguinera.interfaces.NovelaRepositorio;
import co.com.pinguinera.interfaces.PrestamoRepositorio;
import co.com.pinguinera.interfaces.UsuarioRepositorio;
import co.com.pinguinera.interfaces.UsuarioRolesRepositorio;

import java.util.Scanner;

public class MenuPrincipal {
    private final Scanner scanner;
    private final RegistrarUsuarioControlador registrarUsuarioControlador;
    private final AutenticacionDAO autenticacionDAO;

    // Declarar dependencias
    private final UsuarioRepositorio usuarioRepositorio;
    private final UsuarioRolesRepositorio usuarioRolesRepositorio;
    private final LibroRepositorio libroRepositorio;
    private final NovelaRepositorio novelaRepositorio;
    private final PrestamoRepositorio prestamoRepositorio; // Agregar PrestamoRepositorio

    // Constructor
    public MenuPrincipal(
            RegistrarUsuarioControlador registrarUsuarioControlador,
            AutenticacionDAO autenticacionDAO,
            Scanner scanner,
            UsuarioRepositorio usuarioRepositorio,
            UsuarioRolesRepositorio usuarioRolesRepositorio,
            LibroRepositorio libroRepositorio,
            NovelaRepositorio novelaRepositorio,
            PrestamoRepositorio prestamoRepositorio // Recibir PrestamoRepositorio
    ) {
        this.scanner = scanner; // Usar la instancia de `Scanner` pasada como argumento
        this.registrarUsuarioControlador = registrarUsuarioControlador;
        this.autenticacionDAO = autenticacionDAO;
        // Inicializar las dependencias
        this.usuarioRepositorio = usuarioRepositorio;
        this.usuarioRolesRepositorio = usuarioRolesRepositorio;
        this.libroRepositorio = libroRepositorio;
        this.novelaRepositorio = novelaRepositorio;
        this.prestamoRepositorio = prestamoRepositorio; // Inicializar PrestamoRepositorio
    }

    public void mostrarMenuPrincipal() {
        // Crear una instancia de `IniciarSesionControlador` con todas las dependencias necesarias
        IniciarSesionControlador iniciarSesionControlador = new IniciarSesionControlador(
                scanner,
                autenticacionDAO,
                usuarioRepositorio,
                usuarioRolesRepositorio,
                libroRepositorio,
                novelaRepositorio,
                prestamoRepositorio // Pasar PrestamoRepositorio
        );

        while (true) {
            System.out.println("Bienvenido a La Pingüinera");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrarse como Lector");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");

            if (scanner.hasNextInt()) {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea pendiente

                switch (opcion) {
                    case 1:
                        iniciarSesionControlador.iniciarSesion(); // Llamar al método iniciarSesionControlador
                        break;
                    case 2:
                        registrarUsuarioControlador.registrarLector();
                        break;
                    case 0:
                        System.out.println("Saliendo del programa...");
                        return; // Salir del método para finalizar la aplicación
                    default:
                        System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                }
            } else {
                System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                scanner.nextLine(); // Consumir la entrada inválida
            }
        }
    }
}
