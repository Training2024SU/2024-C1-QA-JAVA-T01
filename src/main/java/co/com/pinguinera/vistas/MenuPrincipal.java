package co.com.pinguinera.vistas;

import co.com.pinguinera.controladores.IniciarSesionControlador;
import co.com.pinguinera.controladores.RegistrarUsuarioControlador;
import co.com.pinguinera.modelos.DAO.AutenticacionDAO;

import java.util.Scanner;

public class MenuPrincipal {
    private Scanner scanner;
    private RegistrarUsuarioControlador registrarUsuarioControlador;
    private AutenticacionDAO autenticacionDAO;

    public MenuPrincipal(RegistrarUsuarioControlador registrarUsuarioControlador, AutenticacionDAO autenticacionDAO, Scanner scanner) {
        this.scanner = scanner; // Usar la instancia de `Scanner` pasada como argumento
        this.registrarUsuarioControlador = registrarUsuarioControlador;
        this.autenticacionDAO = autenticacionDAO;
    }
    public void mostrarMenuPrincipal() {
        // Initialize IniciarSesionControlador object here
        IniciarSesionControlador iniciarSesionControlador = new IniciarSesionControlador(scanner, autenticacionDAO);

        while (true) {
            System.out.println("Bienvenido a La Pingüinera");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrarse como Lector");
            System.out.println("0. Salir");
            System.out.print("Opción: ");

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
