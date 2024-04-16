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
        System.out.println("Proceso Exitoso");
    }

    public static void mostrarMensajeError() {
        System.out.println("Error. Por favor, inténtelo nuevamente.");
    }

    // Método para pedir el nombre del usuario
    public static String pedirNombre() {
        System.out.print("Por favor, introduzca su nombre: ");
        return scanner.nextLine();
    }

    // Métodos adicionales para pedir información relacionada con libros y novelas
    public static String pedirTitulo() {
        System.out.print("Por favor, introduzca el título: ");
        return scanner.nextLine();
    }

    public static String pedirAutor() {
        System.out.print("Por favor, introduzca el autor: ");
        return scanner.nextLine();
    }

    public static int pedirNumPaginas() {
        System.out.print("Por favor, introduzca el número de páginas: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public static int pedirCantEjemplares() {
        System.out.print("Por favor, introduzca la cantidad de ejemplares: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public static int pedirCantPrestados() {
        System.out.print("Por favor, introduzca la cantidad de ejemplares prestados: ");
        return Integer.parseInt(scanner.nextLine());
    }


}
