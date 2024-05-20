package co.com.biblioteca.dialogo;

import java.util.Scanner;

public class ControlMenu {


    public static String MSJ_SEGURO_SALIR = "¿Seguro que desea salir? (S/N):";

    public static String preguntarSalir() throws NullPointerException {
        System.out.println(MSJ_SEGURO_SALIR);
        return pedirString();
    }

    public static String pedirString() {
        Scanner scanner = new Scanner(System.in);
        String option;
        do {
            try {
                option = scanner.nextLine();
            } catch (Exception e) {
                option = "";
                System.out.println("Error por la razon " + e.getMessage());
            }
        } while (option.isEmpty());
        return option;
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
