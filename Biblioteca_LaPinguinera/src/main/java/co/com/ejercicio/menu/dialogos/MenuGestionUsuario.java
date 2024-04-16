package co.com.ejercicio.menu.dialogos;

import java.sql.SQLException;
import java.util.Scanner;

import static co.com.ejercicio.menu.dialogos.MenuPrincipal.mostrarMenu;
import static co.com.ejercicio.menu.gestionMenu.GestionPublicacion.gestionObtenerPublicacion;
import static co.com.ejercicio.menu.gestionMenu.GestionUsuario.*;
import static co.com.ejercicio.menu.constantesMenu.ConstanteParaMenu.*;


public class MenuGestionUsuario {

    public static void menuAccionesUsuario(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿ Que desea realizar ? ");
        System.out.println("1. Realizar Prestamo");
        System.out.println("2. Ver publicaciones Disponibles");
        System.out.println("0. Cerrar sesion");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                try {
                    gestionInsertarUsuario();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 2:
                try {
                    gestionObtenerPublicacion();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 0:
                System.out.println("Cerraste Sesion");
                mostrarMenu();

                break;
        }
    }


    public static void interactuarConUsuario(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("MENÚ DE GESTIÓN DE USUARIOS");
        System.out.println("¿Qué desea hacer con los usuarios?");
        System.out.println(INSERTAR);
        System.out.println(OBTENER);
        System.out.println(ACTUALIZAR);
        System.out.println(ELIMINAR);
        System.out.print(ELIGE_OPCION);
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                try {
                    gestionInsertarUsuario();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 2:
                try {
                    gestionObtenerUsuario();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 3:
                gestionActualizarUsuario();

                break;
            case 4:
                try {
                    gestionEliminarUsuario();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                System.out.println("Opción no válida. Por favor ingrese un número entre 1 y 4.");
                mostrarMenuGestionUsuarios();
        }

        scanner.close();
    }
}
