package co.com.pinguinera.vistas;

import co.com.pinguinera.controladores.IniciarSesionControlador;
import co.com.pinguinera.controladores.RegistrarLectorControlador;
import co.com.pinguinera.DAO.AutenticacionDAO;
import co.com.pinguinera.interfaces.LibroRepositorio;
import co.com.pinguinera.interfaces.NovelaRepositorio;
import co.com.pinguinera.interfaces.PrestamoRepositorio;
import co.com.pinguinera.interfaces.RolesRepositorio;
import co.com.pinguinera.interfaces.UsuarioRepositorio;

import java.util.Scanner;

public class MenuPrincipal {
    private final Scanner scanner;
    private final RegistrarLectorControlador registrarLectorControlador;
    private final AutenticacionDAO autenticacionDAO;

    // Declarar dependencias
    private final UsuarioRepositorio usuarioRepositorio;
    private final RolesRepositorio rolesRepositorio;
    private final LibroRepositorio libroRepositorio;
    private final NovelaRepositorio novelaRepositorio;
    private final PrestamoRepositorio prestamoRepositorio;

    // Constructor
    public MenuPrincipal(
            RegistrarLectorControlador registrarLectorControlador,
            AutenticacionDAO autenticacionDAO,
            Scanner scanner,
            UsuarioRepositorio usuarioRepositorio,
            RolesRepositorio rolesRepositorio,
            LibroRepositorio libroRepositorio,
            NovelaRepositorio novelaRepositorio,
            PrestamoRepositorio prestamoRepositorio
    ) {
        this.scanner = scanner;
        this.registrarLectorControlador = registrarLectorControlador;
        this.autenticacionDAO = autenticacionDAO;
        // Inicializar las dependencias
        this.usuarioRepositorio = usuarioRepositorio;
        this.rolesRepositorio = rolesRepositorio;
        this.libroRepositorio = libroRepositorio;
        this.novelaRepositorio = novelaRepositorio;
        this.prestamoRepositorio = prestamoRepositorio;
    }

    public void mostrarMenuPrincipal() {
        // Crear una instancia de `IniciarSesionControlador` con todas las dependencias necesarias
        IniciarSesionControlador iniciarSesionControlador = new IniciarSesionControlador(
                scanner,
                autenticacionDAO,
                usuarioRepositorio,
                rolesRepositorio,
                libroRepositorio,
                novelaRepositorio,
                prestamoRepositorio
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
                        iniciarSesionControlador.iniciarSesion();
                        break;
                    case 2:
                        registrarLectorControlador.registrarLector();
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
