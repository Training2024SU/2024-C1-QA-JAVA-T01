package com.sofkau.logica.control;

import java.util.Scanner;

import static com.sofkau.dialogo.Menu.menuPrincipal;



public class InicioSesion {

    private boolean bandera = true;
    public static void implementarLogica() {
        int option;
        boolean bandera = true;
        while (bandera) {
            menuPrincipal();
            option = pedirOpcion();
            bandera = seleccionarOpciones(option,bandera);

        }
    }

    public static boolean seleccionarOpciones(int option, boolean bandera) {
        switch (option) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                bandera = false;
                break;
            default:
                System.out.println("ingrese una opcion válida");
        }
        return bandera;
    }

    public static int pedirOpcion() {
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
