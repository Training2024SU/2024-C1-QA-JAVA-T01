package org.moreno.cristian.ui;

import org.moreno.cristian.modelos.Usuario;

import java.util.Scanner;

public class MenuLector {

    private static Scanner scan = new Scanner(System.in);


    public static void home (Usuario lector) {

        System.out.println("Bienvenido de nuevo " + lector.getNombre() + "\n");


        while (true) {

            System.out.println("Qué desea hacer\n" +
                    "   1. Ver libros" +
                    "   2. Prestar libros" +
                    "   3. Devolver libros");

            String respuestaLector = scan.nextLine();
        }
    }

    public static void listarLibros () {


        while (true) {

            System.out.println(
                    "   1. Ver todos\n" +
                    "   2. Filtrar por autor\n" );

            String respuestaLector = scan.nextLine();
        }
    }

    public static void prestarLibros () {


        while (true) {

            System.out.print("Escribe el nombre del libro que deseas prestar: ");

            String respuestaLector = scan.nextLine();
        }
    }

    public static void devolverLibros () {


        while (true) {

            System.out.print("Escribe el nombre del libro que deseas devolver: ");

            String respuestaLector = scan.nextLine();
        }
    }


}
