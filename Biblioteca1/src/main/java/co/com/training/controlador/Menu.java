package co.com.training.controlador;

import co.com.training.integration.database.mysql.MySqlOperation;


import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    private static final MySqlOperation mySqlOperation = new MySqlOperation();

    public static void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        while (continuar) {
            System.out.println("¿Qué quieres hacer hoy?");
            System.out.println("1. Agregar material");
            System.out.println("2. Actualizar material");
            System.out.println("3. Eliminar material");
            System.out.println("4. Obtener material");
            System.out.println("5. Agregar usuario");
            System.out.println("6. Actualizar usuario");
            System.out.println("7. Eliminar usuario");
            System.out.println("8. Consultar usuario");
            System.out.println("9. Salir");
            System.out.print("Escribe el número de la opción deseada: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.println("Opción para agregar material seleccionada.");
                    System.out.println("¿Qué tipo de material desea agregar?");
                    System.out.println("1. Libro");
                    System.out.println("2. Novela");
                    System.out.print("Escribe el número de la opción deseada: ");

                    int tipoMaterial = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer

                    switch (tipoMaterial) {
                        case 1:
                            AgregarLibro agregarLibro = new AgregarLibro(mySqlOperation);
                            agregarLibro.agregarLibro(); // Corrección aquí
                            break;
                        case 2:
                            //agregarNovela();
                            break;
                        default:
                            System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                            break;
                    }
                    break;

                case 2:
                    // Lógica para actualizar material
                    System.out.println("Opción para actualizar material seleccionada.");
                    break;
                case 3:
                    // Lógica para eliminar material
                    System.out.println("Opción para eliminar material seleccionada.");
                    break;
                case 4:
                    // Lógica para obtener material
                    System.out.println("Opción para obtener material seleccionada.");
                    break;
                case 5:
                    // Lógica para agregar usuario
                    //agregarUsuario();
                    break;
                case 6:
                    // Lógica para actualizar usuario
                    //actualizarUsuario();
                    break;
                case 7:
                    // Lógica para eliminar usuario
                    //eliminarUsuario();
                    break;
                case 8:
                    // Lógica para consultar usuario
                    //consultarUsuario();
                    break;
                case 9:
                    // Salir del menú
                    System.out.println("Saliendo del programa...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }
        }
    }

    //private static void agregarUsuario() {
        // Implementación para agregar un usuario
}

