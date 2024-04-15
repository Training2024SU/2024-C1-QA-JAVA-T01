package co.com.pinguinera.vistas.vista_usuario;

import java.util.Scanner;

public class InformacionUsuarioVista {

    private final Scanner scanner;

    public InformacionUsuarioVista() {
        this.scanner = new Scanner(System.in);
    }

    public String pedirNombreUsuario() {
        System.out.print("Ingrese el nombre del usuario: ");
        return scanner.nextLine();
    }

    public String pedirCorreoUsuario() {
        System.out.print("Ingrese el correo electrónico del usuario: ");
        return scanner.nextLine();
    }

    public String pedirContrasenaUsuario() {
        System.out.print("Ingrese la contraseña del usuario: ");
        return scanner.nextLine();
    }

}
