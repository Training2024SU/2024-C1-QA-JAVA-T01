package co.com.pinguinera.vistas.vista_empleado;

import co.com.pinguinera.datos.model.Empleado;
import java.util.Scanner;

public class RegistroEmpleadoVista {

    private Scanner scanner;

    public RegistroEmpleadoVista() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Solicita los datos de un nuevo empleado desde la consola y devuelve un objeto `Empleado` con los datos ingresados.
     * @return Un objeto `Empleado` con los datos ingresados.
     */
    public Empleado pedirDatosEmpleado() {
        Empleado empleado = new Empleado();

        // Solicitar nombre del empleado
        System.out.print("Ingrese el nombre del empleado: ");
        String nombre = scanner.nextLine();
        empleado.setNombre(nombre);

        // Solicitar contraseña del empleado
        System.out.print("Ingrese la contraseña del empleado: ");
        String contrasena = scanner.nextLine();
        empleado.setContrasena(contrasena);

        // Solicitar correo electrónico del empleado
        System.out.print("Ingrese el correo electrónico del empleado: ");
        String correo = scanner.nextLine();
        empleado.setCorreo(correo);

        // Solicitar rol del empleado (Administrativo/Asistente)
        System.out.print("Ingrese el rol del empleado (Administrativo/Asistente): ");
        String rol = scanner.nextLine();
        empleado.setRol(rol);

        // Solicitar si el empleado es administrativo (0 para no, 1 para sí)
        System.out.print("¿El empleado es administrativo? (0 para no, 1 para sí): ");
        int esAdministrativo = Integer.parseInt(scanner.nextLine());
        empleado.setEsAdministrativo(esAdministrativo == 1);

        // Devolver el objeto Empleado con los datos ingresados
        return empleado;
    }

    /**
     * Muestra un mensaje de éxito cuando el registro es exitoso.
     */
    public void mostrarMensajeExitoRegistro() {
        System.out.println("¡Registro exitoso!");
    }

    /**
     * Muestra un mensaje de error si el registro falla.
     * @param mensaje Mensaje de error a mostrar.
     */
    public void mostrarMensajeErrorRegistro(String mensaje) {
        System.out.println("Ha ocurrido un error: " + mensaje);
    }
}
