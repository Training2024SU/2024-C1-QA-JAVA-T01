package co.com.ejercicio.menu.dialogos;

import java.sql.SQLException;
import java.util.Scanner;

import static co.com.ejercicio.menu.gestionMenu.GestionEmpleado.*;
import static co.com.ejercicio.menu.gestionMenu.GestionUsuario.*;
import static co.com.ejercicio.menu.constantesMenu.ConstanteParaMenu.*;

public class MenuGestionEmpleado {
    public static void menuEmpleado(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("MENÚ DE GESTIÓN DE EMPLEADOS");
        System.out.println("¿Qué desea hacer con los Empleados?");
        System.out.println(INSERTAR);
        System.out.println(OBTENER);
        System.out.println(ACTUALIZAR);
        System.out.println(ELIMINAR);
        System.out.print(ELIGE_OPCION);
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch(opcion)

        {
            case 1:
                try {
                    gestionInsertarEmpleado();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 2:
                try {
                    gestionObtenerEmpleado();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 3:
                try {
                    gestionEliminarEmpleado();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 4:
                gestionActualizarEmpleado();
                break;
            default:
                System.out.println("Opción no válida. Por favor ingrese un número entre 1 y 4.");
                mostrarMenuGestionUsuarios();
        }
        scanner.close();
    }


    }




