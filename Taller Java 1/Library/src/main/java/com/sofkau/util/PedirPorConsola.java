package com.sofkau.util;

import java.util.Scanner;

public class PedirPorConsola {

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
    public static String pedirString() {
        Scanner scanner = new Scanner(System.in);
        String option;
        try {
            option = scanner.nextLine();
        } catch (Exception e) {
            option = "";
            System.out.println("Error por la razon " + e.getMessage());
        }

        return option;
    }
}
