package co.com.pinguinera.vistas.vista_usuario;

import co.com.pinguinera.LoggerUtil;

import java.util.Scanner;
import java.util.logging.Logger;

public class InformacionUsuarioVista {
    private static final Logger LOGGER = LoggerUtil.getLogger(); // Usar el logger global desde LoggerUtil

    private final Scanner scanner;

    public InformacionUsuarioVista() {
        this.scanner = new Scanner(System.in);
    }

    public String pedirNombreUsuario() {
        LOGGER.info("Ingrese el nombre del usuario:");
        return scanner.nextLine();
    }

    public String pedirCorreoUsuario() {
        LOGGER.info("Ingrese el correo electrónico del usuario:");
        return scanner.nextLine();
    }

    public String pedirContrasenaUsuario() {
        LOGGER.info("Ingrese la contraseña del usuario:");
        return scanner.nextLine();
    }
}
