package org.moreno.cristian.ui;

import org.moreno.cristian.modelos.Usuario;

import java.util.Scanner;

public class MenuAsistente {

    private static Scanner scan = new Scanner(System.in);

    public static void home(Usuario asistente) {
        while (true) {
            System.out.println("\nQué desea hacer?\n" +
                    "   1. Iniciar sesión \n" +
                    "   2. Registrarme \n");

            String respuestaUsuario = scan.nextLine();

            switch(respuestaUsuario) {
                case "1":
                    inicioSesion();
                    break;
                case "2":
                    // code block
                    break;
                default:
                    // code block
            }
        }
    }

    public static void inicioSesion () {

        while (true) {

            System.out.print("Ingresa tu correo: ");
            String correoUsuario = scan.nextLine();

            System.out.print("Ingresa tu contraseña: ");
            String contraseniaUsuario = scan.nextLine();


        }
    }

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
