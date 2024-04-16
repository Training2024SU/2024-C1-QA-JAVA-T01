package org.moreno.cristian.servicios;

import java.util.Scanner;

public class ScannerUtil {
    private static Scanner scanner = new Scanner(System.in);

    public static Scanner obtenerScanner() {
        return scanner;
    }

    public static void cerrarScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }

    public static int pedirEntero() {

        int entero =0;

        while (true) {
            try {
                entero = scanner.nextInt();
                scanner.nextLine();
                System.out.print("You entered: " + entero + "\n");
                break;
            } catch (java.util.InputMismatchException e) {
                System.out.print("Invalid input. Please enter an integer.");
                // Clear
                scanner.next(); // Read and discard the invalid input
            }

        }
        return entero;
    }
}
