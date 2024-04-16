package co.com.pinguinera.vistas.vista_empleado;

import co.com.pinguinera.LoggerUtil;
import co.com.pinguinera.vistas.VistaUtil;

import java.util.Scanner;
import java.util.logging.Logger;

public class InformacionEmpleadoVista {
    private static final Logger LOGGER = LoggerUtil.getLogger(); // Usar el logger global desde LoggerUtil

    private Scanner scanner;

    public InformacionEmpleadoVista() {
        this.scanner = new Scanner(System.in);
    }

    public String pedirNombre() {
        return VistaUtil.pedirNombre();
    }

    public String pedirContrasena() {
        return VistaUtil.pedirContrasena();
    }

    public String pedirCorreo() {
        return VistaUtil.pedirCorreoElectronico();
    }

    public String pedirRol() {
        LOGGER.info("Ingrese el rol del empleado (Administrativo/Asistente):");
        return scanner.nextLine();
    }

    public boolean pedirEsAdministrativo() {
        LOGGER.info("¿El empleado es administrativo? (0 para no, 1 para sí):");
        int opcion = Integer.parseInt(scanner.nextLine());
        return opcion == 1;
    }

    public int pedirIdEmpleado() {
        LOGGER.info("Ingrese el ID del empleado:");
        return Integer.parseInt(scanner.nextLine());
    }
}
