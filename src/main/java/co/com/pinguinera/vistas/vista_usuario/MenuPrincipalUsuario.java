package co.com.pinguinera.vistas.vista_usuario;

import co.com.pinguinera.controladores.crud.ControladorCRUDUsuario;
import co.com.pinguinera.controladores.crud.ControladorCRUDPrestamo;

import java.util.Scanner;

public class MenuPrincipalUsuario {

    private final ControladorCRUDUsuario controladorCRUDUsuario;
    private final ControladorCRUDPrestamo controladorPrestamo;
    private final Scanner scanner;

    public MenuPrincipalUsuario(ControladorCRUDUsuario controladorCRUDUsuario,
                                ControladorCRUDPrestamo controladorPrestamo) {
        this.controladorCRUDUsuario = controladorCRUDUsuario;
        this.controladorPrestamo = controladorPrestamo;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nMenú principal de usuario");
            System.out.println("1. Actualizar información de usuario");
            System.out.println("2. Realizar préstamo");
            System.out.println("3. Salir");

            int opcion = obtenerOpcion();

            switch (opcion) {
                case 1:
                    controladorCRUDUsuario.actualizarUsuario();
                    break;
                case 2:
                    controladorPrestamo.registrarPrestamo();
                    break;
                case 3:
                    System.out.println("Saliendo del menú...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }

    private int obtenerOpcion() {
        while (true) {
            System.out.print("Opción: ");
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }
}
