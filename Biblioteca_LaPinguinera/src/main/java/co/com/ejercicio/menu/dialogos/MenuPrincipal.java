package co.com.ejercicio.menu.dialogos;

import java.util.Scanner;

import static co.com.ejercicio.menu.loguin.InicioSesion.*;
import static co.com.ejercicio.menu.loguin.IngresoAsistema.inicioSesionAsistente;
import static co.com.ejercicio.menu.loguin.IngresoAsistema.inicioSesionUsuario;
import static co.com.ejercicio.menu.loguin.RegistroUsuario.registrarUsuario;


public class MenuPrincipal {
    public static void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("BIENVENIDO AL SISTEMA");
        System.out.println("¿Cómo desea ingresar al sistema?");
        System.out.println("1. Administrador");
        System.out.println("2. Asistente");
        System.out.println("3. Usuario");
        System.out.println("4. Registrate");
        System.out.print("Ingrese su opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        switch (opcion) {
            case 1:
                ingresarComoAdministrador();
                break;
            case 2:
                inicioSesionAsistente();
                break;
            case 3:
                inicioSesionUsuario();
                break;
            case 4:
                registrarUsuario();
                break;
            default:
                System.out.println("Opción no válida. Por favor ingrese 1 o 2.");
                System.out.println(" ");
                mostrarMenu();
                System.out.println(" ");
        }

        scanner.close();
    }
}
