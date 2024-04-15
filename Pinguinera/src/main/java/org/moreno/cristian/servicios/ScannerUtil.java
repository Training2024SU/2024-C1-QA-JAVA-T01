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
}
