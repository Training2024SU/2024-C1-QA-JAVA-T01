package org.moreno.cristian.ui;

import java.util.Scanner;

public class Menu {

    private static Scanner scan = new Scanner(System.in);

    public static void menuAdmin() {

        while (true) {
            System.out.println("\nQué desea hacer?\n" +
                    "1. Crear asistente \n" +
                    "2. Ver usuarios \n" +
                    "3. CRUD libros \n" +
                    "4. Ver préstamos");

            String respuestaAdmin = scan.nextLine();

            switch(respuestaAdmin) {
                case "1":
                    // code block
                    break;
                case "2":
                    // code block
                    break;
                case "3":
                    // code block
                    break;
                case "4":
                    // code block
                    break;
                default:
                    // code block
            }
        }

    }
    public static void menuAsistente() {

    }
    public static void menuLector() {

    }
}
