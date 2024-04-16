package co.com.ejercicio.menu.dialogos;

import java.sql.SQLException;
import java.util.Scanner;

import static co.com.ejercicio.menu.constantesMenu.ConstanteParaMenu.*;
import static co.com.ejercicio.menu.gestionMenu.GestionPrestamo.*;

public class MenuGestionPrestamo {

    public static void menuPrestamos() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("MENÚ DE GESTIÓN DE PRÉSTAMOS");
        System.out.println("¿Qué desea hacer?");
        System.out.println(INSERTAR);
        System.out.println(OBTENER);
        System.out.println(ACTUALIZAR);
        System.out.println(ELIMINAR);
        System.out.print(ELIGE_OPCION);
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                try {
                    insertarPrestamo();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 2:
                try {
                    obtenerPrestamo();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 3:
                actualizarPrestamo();

                break;
            case 4:
                try {
                    eliminarPrestamo();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                System.out.println("Opción no válida. Por favor ingrese un número entre 1 y 4.");
        }
    }
}
