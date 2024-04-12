package co.com.training.controlador;

import co.com.training.modelo.Rol;

import java.util.Scanner;

public class Autenticador {

    public static boolean solicitarInicioSesion() {
        Scanner scanner = new Scanner(System.in);
        boolean inicioSesionValido = false;
        while (!inicioSesionValido) {
            System.out.println("Ingrese su correo electrónico:");
            String correo = scanner.nextLine();
            System.out.println("Ingrese su contraseña:");
            String contrasena = scanner.nextLine();
            System.out.println("Ingrese su rol (ADMINISTRADOR o ASISTENTE):");
            String rolStr = scanner.nextLine().toUpperCase();


            // solo estamos comparando con el usuario administrador
            if (correo.equals("administrador@pingu.com.co") &&
                    contrasena.equals("contraseñasegura123456") &&
                    Rol.valueOf(rolStr) == Rol.ADMINISTRADOR) {
                System.out.println("Inicio de sesión exitoso.");
                inicioSesionValido = true;
            } else {
                System.out.println("Credenciales incorrectas. Inténtelo de nuevo.");
            }
        }
        return inicioSesionValido;


    }
}
