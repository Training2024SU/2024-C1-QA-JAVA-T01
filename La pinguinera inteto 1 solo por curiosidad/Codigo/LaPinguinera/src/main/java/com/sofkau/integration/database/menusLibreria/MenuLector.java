package com.sofkau.integration.database.menusLibreria;

import java.util.Scanner;

public class MenuLector {
    private Scanner scanner;

    private void menuUsuario() {
        scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("Menú de usuario:");
            System.out.println("1. Solicitar préstamo");
            System.out.println("2. Ver publicaciones (libros, novelas)");
            System.out.println("3. Volver al menú principal");

            System.out.print("Ingrese su selección: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    //solicitarPrestamo();
                    break;
                case 2:
                    //verPublicaciones();
                    break;
                case 3:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                    break;
            }
        } while (opcion != 3);
    }
}
