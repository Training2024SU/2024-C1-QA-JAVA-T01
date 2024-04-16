package co.com.ejercicio.menu.menuPrincipal;

import co.com.ejercicio.util.enums.TipoGestion;

import java.util.Scanner;

import static co.com.ejercicio.menu.gestionMenu.GestionAdministrador.*;
import static co.com.ejercicio.menu.dialogos.MenuPrincipal.mostrarMenu;
import static co.com.ejercicio.menu.constantesMenu.ConstanteParaMenu.ELIGE_OPCION;

public class MenuGestionAdministrador {
    public static void menuAdministrador() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("MENÚ DE GESTIÓN - ADMINISTRADOR");
        System.out.println("¿Qué desea gestionar?");
        System.out.println(TipoGestion.GESTION_UNO.getValue());
        System.out.println(TipoGestion.GESTION_DOS.getValue());
        System.out.println(TipoGestion.GESTION_TRES.getValue());
        System.out.println(TipoGestion.GESTION_CUATRO.getValue());
        System.out.println("0. Cerrar sesion");
        System.out.print(ELIGE_OPCION);
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        switch (opcion) {
            case 1:
                gestionarUsuarios();
                break;
            case 2:
                gestionarPublicacion();
                break;
            case 3:
                gestionarEmpleados();
                break;
            case 4:
                gestionarPrestamos();
                break;
            case 0:
                System.out.println("Cerraste Sesion");
                mostrarMenu();
                break;
            default:
                System.out.println("Opción no válida. Por favor ingrese un número entre 1 y 4.");
                System.out.println(" ");
                mostrarMenuAdministrador();
                System.out.println(" ");
        }
    }
}
