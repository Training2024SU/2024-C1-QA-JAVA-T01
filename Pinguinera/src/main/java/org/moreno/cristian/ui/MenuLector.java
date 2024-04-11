package org.moreno.cristian.ui;

import org.moreno.cristian.modelos.Usuario;

import java.util.Scanner;

public class MenuLector {

    private static Scanner scan = new Scanner(System.in);


    public static void home (Usuario lector) {

        while (true) {

            System.out.println("Bienvenido de nuevo " + lector.getNombre());

        }
    }


}
