package com.sofkau.integration.database.menusLibreria;

import java.util.Scanner;

public class MenuAdministrador {
    private Scanner scanner;

    private void menuAdministrador() {
        scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("Menú de administrador:");
            System.out.println("1. Interacción con usuarios");
            System.out.println("2. Interacción con asistentes");
            System.out.println("3. Interacción con publicaciones");
            System.out.println("4. Volver al menú principal");

            System.out.print("Ingrese su selección: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    menuInteraccionUsuarios();
                    break;
                case 2:
                    menuInteraccionAsistentes();
                    break;
                case 3:
                    menuInteraccionPublicaciones();
                    break;
                case 4:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                    break;
            }
        } while (opcion != 4);
    }

    private void menuInteraccionUsuarios() {
        int opcion;
        do {
            System.out.println("Menú de interacción con usuarios:");
            System.out.println("1. Consultar usuarios");
            System.out.println("2. Agregar usuario");
            System.out.println("3. Actualizar usuario");
            System.out.println("4. Eliminar usuario");
            System.out.println("5. Volver al menú anterior");

            System.out.print("Ingrese su selección: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    //consultarUsuarios();
                    break;
                case 2:
                   // agregarUsuario();
                    break;
                case 3:
                   // actualizarUsuario();
                    break;
                case 4:
                   // eliminarUsuario();
                    break;
                case 5:
                    System.out.println("Volviendo al menú anterior...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                    break;
            }
        } while (opcion != 5);
    }

    private void menuInteraccionAsistentes() {
        int opcion;
        do {
            System.out.println("Menú de interacción con asistentes:");
            System.out.println("1. Consultar asistentes");
            System.out.println("2. Agregar asistente");
            System.out.println("3. Actualizar asistente");
            System.out.println("4. Eliminar asistente");
            System.out.println("5. Volver al menú anterior");

            System.out.print("Ingrese su selección: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    //consultarAsistentes();
                    break;
                case 2:
                   // agregarAsistente();
                    break;
                case 3:
                    //actualizarAsistente();
                    break;
                case 4:
                    //eliminarAsistente();
                    break;
                case 5:
                    System.out.println("Volviendo al menú anterior...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                    break;
            }
        } while (opcion != 5);
    }

    private void menuInteraccionPublicaciones() {
        int opcion;
        do {
            System.out.println("Menú de interacción con publicaciones:");
            System.out.println("1. Consultar publicaciones");
            System.out.println("2. Agregar publicación");
            System.out.println("3. Actualizar publicación");
            System.out.println("4. Eliminar publicación");
            System.out.println("5. Volver al menú anterior");

            System.out.print("Ingrese su selección: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                   // consultarPublicaciones();
                    break;
                case 2:
                    //agregarPublicacion();
                    break;
                case 3:
                   // actualizarPublicacion();
                    break;
                case 4:
                   // eliminarPublicacion();
                    break;
                case 5:
                    System.out.println("Volviendo al menú anterior...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                    break;
            }
        } while (opcion != 5);
    }
}
