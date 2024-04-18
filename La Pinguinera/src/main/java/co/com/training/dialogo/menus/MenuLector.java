package co.com.training.dialogo.menus;

import co.com.training.modelo.Usuario;
import java.util.Scanner;

public class MenuLector {

    private Usuario usuario;
    private Scanner scanner;

    public MenuLector(Usuario usuario) {
        this.usuario = usuario;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("Bienvenido, " + usuario.getNombreUsuario());
            System.out.println("1. Ver catálogo de libros");
            System.out.println("2. Ver catálogo de novelas");
            System.out.println("3. Consultar préstamos");
            System.out.println("4. Realizar préstamo");
            System.out.println("5. Salir");
            System.out.print("Ingrese su opción: ");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    // Lógica para mostrar el catálogo de libros
                    break;
                case 2:
                    // Lógica para mostrar el catálogo de novelas
                    break;
                case 3:
                    // Lógica para consultar préstamos del usuario
                    break;
                case 4:
                    // Lógica para realizar un préstamo
                    break;
                case 5:
                    System.out.println("Gracias por utilizar el sistema.");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
            }
        } while (opcion != 5);
    }
}
