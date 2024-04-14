package com.sofkau.logica.control;

import com.sofkau.dialogo.Menu;

import java.util.Scanner;

import static com.sofkau.dialogo.Menu.menuPrincipal;
import static com.sofkau.dialogo.Menu.nombreUsuario;


public class ControlIngreso {

    protected static boolean bandera = true;

    private static Scanner scannerGlobal = new Scanner(System.in);

    // Se hace publico para ser el unico metodo que se pueda llamar desde el main
    public static void implementarLogica() {
        int option;
        while (bandera) {
            Menu.menuPrincipal();
            option = pedirOpcion();
            inicioSesion(option);
        }
    }

    private static void inicioSesion(int option) {
        switch (option) {
            case 1:
                break;
            case 2:
                Menu.nombreUsuario();
                String nombre = scannerGlobal.nextLine();
                Menu.correoUsuario();
                String correo = scannerGlobal.nextLine();
                Menu.nombreUsuario();
                String contrasena = scannerGlobal.nextLine();



                break;
            case 3:
                bandera = false;
                break;
            default:
                System.out.println("ingrese una opcion válida");
        }
    }

    private static int pedirOpcion() {
        Scanner scanner = new Scanner(System.in);
        int option;
        try {
            option = scanner.nextInt();
        } catch (Exception e) {
            option = 0;
            System.out.println("Error por la razon " + e.getMessage());
        }
        return option;
    }
}
