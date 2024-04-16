package com.sofkau.integration.database.menusLibreria;

import java.util.Scanner;

public class MenuAsistente {
    private Scanner scanner;
    private void menuAsistente() {
        scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("Menú de asistente:");
            System.out.println("1. Consultar préstamo de usuario");
            System.out.println("2. Recibir préstamo de usuario");
            System.out.println("3. Agregar libro");
            System.out.println("4. Actualizar libro");
            System.out.println("5. Consultar libro");
            System.out.println("6. Eliminar libro");
            System.out.println("7. Agregar novela");
            System.out.println("8. Actualizar novela");
            System.out.println("9. Consultar novela");
            System.out.println("10. Eliminar novela");
            System.out.println("11. Volver al menú principal");

            System.out.print("Ingrese su selección: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    //consultarPrestamoUsuario();
                    break;
                case 2:
                    //recibirPrestamoUsuario();
                    break;
                case 3:
                    //agregarLibro();
                    break;
                case 4:
                    //actualizarLibro();
                    break;
                case 5:
                    //consultarLibro();
                    break;
                case 6:
                    //eliminarLibro();
                    break;
                case 7:
                    //agregarNovela();
                    break;
                case 8:
                    //actualizarNovela();
                    break;
                case 9:
                    //consultarNovela();
                    break;
                case 10:
                    //eliminarNovela();
                    break;
                case 11:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                    break;
            }
        } while (opcion != 11);
    }
}
