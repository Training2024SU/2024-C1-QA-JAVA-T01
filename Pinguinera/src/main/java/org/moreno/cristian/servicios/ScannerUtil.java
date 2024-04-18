package org.moreno.cristian.servicios;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class ScannerUtil {
    private static Scanner scanner = new Scanner(System.in);
    static Logger log = LogManager.getLogger(String.valueOf(ScannerUtil.class));

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
                break;
            } catch (java.util.InputMismatchException e) {
                log.error("Input no válido. Por favor ingresa un entero");
                // Clear
                scanner.next(); // Read and discard the invalid input
            }

        }
        return entero;
    }
}
