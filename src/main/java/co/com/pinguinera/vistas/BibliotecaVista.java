package co.com.pinguinera.vistas;

import co.com.pinguinera.controladores.GestionUsuario;

import java.util.Scanner;

public class BibliotecaVista {
    private Scanner scanner;
    private GestionUsuario gestionUsuario; // Agregar instancia de GestionUsuario

    public BibliotecaVista(GestionUsuario gestionUsuario) { // Pasar GestionUsuario al constructor
        this.scanner = new Scanner(System.in);
        this.gestionUsuario = gestionUsuario; // Inicializar GestionUsuario
    }

    public void mostrarMenuPrincipal() {
        boolean continuar = true;
        while (continuar) {
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
                        // Lógica para iniciar sesión
                        break;
                    case 2:
                        gestionUsuario.registrarLector();
                        break;
                    case 0:
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                }
            } else {
                System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                scanner.nextLine(); // Consumir la entrada inválida
            }
        }
    }



    // Otros métodos de la clase
}
