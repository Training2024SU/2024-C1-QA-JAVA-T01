package co.com.pinguinera.vistas;

import co.com.pinguinera.LoggerUtil;
import co.com.pinguinera.controladores.autenticacion.EmpleadoSesionControlador;
import co.com.pinguinera.controladores.crud.ControladorCRUDUsuario;
import co.com.pinguinera.controladores.autenticacion.UsuarioSesionControlador;

import java.util.Scanner;
import java.util.logging.Logger;

public class MenuPrincipal {
    private static final Logger LOGGER = LoggerUtil.getLogger(); // Usar el logger global desde LoggerUtil

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
            LOGGER.info("\nBienvenido a la librería Pinguinera");
            LOGGER.info("Seleccione una opción:");
            LOGGER.info("1. Iniciar sesión como empleado");
            LOGGER.info("2. Iniciar sesión como usuario");
            LOGGER.info("3. Registrarse como usuario");
            LOGGER.info("4. Salir");

            int eleccion = VistaUtil.obtenerOpcion();
            continuar = manejarEleccion(eleccion);
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
                LOGGER.info("Gracias por usar la librería Pinguinera. ¡Hasta luego!");
                return false;
            default:
                LOGGER.warning("Opción no válida. Inténtelo de nuevo.");
                break;
        }
        return true;
    }
}
