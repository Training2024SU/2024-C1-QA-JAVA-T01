package co.com.pinguinera.vistas;

import co.com.pinguinera.controladores.autenticacion.EmpleadoSesionControlador;
import co.com.pinguinera.controladores.crud.ControladorCRUDUsuario;
import co.com.pinguinera.controladores.autenticacion.UsuarioSesionControlador;

import java.util.Scanner;

public class MenuPrincipal {
    private final EmpleadoSesionControlador controladorEmpleadoSesion;
    private final UsuarioSesionControlador controladorUsuarioSesion;
    private final ControladorCRUDUsuario controladorRegistroUsuario;
    private final Scanner scanner;

    public MenuPrincipal(EmpleadoSesionControlador controladorEmpleadoSesion,
                         UsuarioSesionControlador controladorUsuarioSesion,
                         ControladorCRUDUsuario controladorRegistroUsuario) {
        this.controladorEmpleadoSesion = controladorEmpleadoSesion;
        this.controladorUsuarioSesion = controladorUsuarioSesion;
        this.controladorRegistroUsuario = controladorRegistroUsuario;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Muestra el menú principal de la aplicación y maneja las opciones seleccionadas por el usuario.
     */
    public void mostrarMenu() {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nBienvenido a la librería Pinguinera");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Iniciar sesión como empleado");
            System.out.println("2. Iniciar sesión como usuario");
            System.out.println("3. Registrarse como usuario");
            System.out.println("4. Salir");

            int eleccion = obtenerOpcion();
            continuar = manejarEleccion(eleccion);
        }
    }

    /**
     * Obtiene una opción numérica válida ingresada por el usuario.
     * @return La opción seleccionada por el usuario.
     */
    private int obtenerOpcion() {
        while (true) {
            System.out.print("Opción: ");
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }

    /**
     * Maneja la elección del usuario según la opción seleccionada.
     * @param eleccion La opción seleccionada por el usuario.
     * @return `true` si el menú debe continuar mostrándose, `false` si el programa debe terminar.
     */
    private boolean manejarEleccion(int eleccion) {
        switch (eleccion) {
            case 1:
                // Iniciar sesión como empleado
                controladorEmpleadoSesion.iniciarSesion();
                break;
            case 2:
                // Iniciar sesión como usuario
                controladorUsuarioSesion.iniciarSesion();
                break;
            case 3:
                // Registrarse como usuario
                controladorRegistroUsuario.registrarUsuario();
                break;
            case 4:
                // Salir
                System.out.println("Gracias por usar la librería Pinguinera. ¡Hasta luego!");
                return false;
            default:
                System.out.println("Opción no válida. Inténtelo de nuevo.");
                break;
        }
        return true;
    }
}
