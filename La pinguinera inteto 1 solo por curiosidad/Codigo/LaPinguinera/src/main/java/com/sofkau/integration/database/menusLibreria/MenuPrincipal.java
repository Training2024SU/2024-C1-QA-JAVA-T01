package com.sofkau.integration.database.menusLibreria;

import java.util.Scanner;

public class MenuPrincipal {
    private Scanner scanner;

    public void mostrarMenu() {
        scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("Bienvenido al menú!");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar sesión como usuario");
            System.out.println("3. Iniciar sesión como administrador");
            System.out.println("4. Iniciar sesión como asistente");
            System.out.println("5. Salir");

            System.out.print("Ingrese su selección: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    //registrar();
                    break;
                case 2:
                    //iniciarSesionUsuario();
                    break;
                case 3:
                    //iniciarSesionAdministrador();
                    break;
                case 4:
                    //iniciarSesionAsistente();
                    break;
                case 5:
                    System.out.println("Saliendo del menú...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                    break;
            }
        } while (opcion != 5);
    }

}
