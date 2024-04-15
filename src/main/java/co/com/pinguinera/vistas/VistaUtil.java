package co.com.pinguinera.vistas;

import java.util.Scanner;

public class VistaUtil {

    private static final Scanner scanner = new Scanner(System.in);

    public static String pedirCorreoElectronico() {
        System.out.print("Por favor, introduzca su correo electrónico: ");
        return scanner.nextLine();
    }

    public static String pedirContrasena() {
        System.out.print("Por favor, introduzca su contraseña: ");
        return scanner.nextLine();
    }

    public static void mostrarMensajeExito() {
        System.out.println("Inicio de sesión exitoso. ¡Bienvenido!");
    }

    public static void mostrarMensajeError() {
        System.out.println("Error. Por favor, inténtelo nuevamente.");
    }

    // Método para pedir el nombre del usuario
    public static String pedirNombre() {
        System.out.print("Por favor, introduzca su nombre: ");
        return scanner.nextLine();
    }


    // Método para cerrar el Scanner al finalizar la aplicación
    public static void cerrarScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
