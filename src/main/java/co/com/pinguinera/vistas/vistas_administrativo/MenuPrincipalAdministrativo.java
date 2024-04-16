package co.com.pinguinera.vistas.vistas_administrativo;

import co.com.pinguinera.LoggerUtil;
import co.com.pinguinera.controladores.crud.*;
import co.com.pinguinera.vistas.MenuConstantes;
import co.com.pinguinera.vistas.VistaUtil;

import java.util.Scanner;
import java.util.logging.Logger;

public class MenuPrincipalAdministrativo {

    private static final Logger LOGGER = LoggerUtil.getLogger(); // Usar el logger global desde LoggerUtil

    private final ControladorCRUDUsuario controladorCRUDUsuario;
    private final ControladorCRUDPrestamo controladorCRUDPrestamo;
    private final ControladorCRUDEmpleado controladorCRUDEmpleado;
    private final ControladorCRUDNovela controladorCRUDNovela;
    private final ControladorCRUDLibro controladorCRUDLibro;

    public MenuPrincipalAdministrativo(ControladorCRUDUsuario controladorCRUDUsuario,
                                       ControladorCRUDPrestamo controladorCRUDPrestamo,
                                       ControladorCRUDEmpleado controladorCRUDEmpleado,
                                       ControladorCRUDNovela controladorCRUDNovela,
                                       ControladorCRUDLibro controladorCRUDLibro) {
        this.controladorCRUDUsuario = controladorCRUDUsuario;
        this.controladorCRUDPrestamo = controladorCRUDPrestamo;
        this.controladorCRUDEmpleado = controladorCRUDEmpleado;
        this.controladorCRUDNovela = controladorCRUDNovela;
        this.controladorCRUDLibro = controladorCRUDLibro;
        Scanner scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        boolean continuar = true;

        while (continuar) {
            LOGGER.info("\nMenú administrativo");
            LOGGER.info("1. Gestión de usuarios");
            LOGGER.info("2. Gestión de préstamos");
            LOGGER.info("3. Gestión de empleados");
            LOGGER.info("4. Gestión de novelas");
            LOGGER.info("5. Gestión de libros");
            LOGGER.info("6. Salir");

            int opcion = VistaUtil.obtenerOpcion();

            switch (opcion) {
                case 1:
                    mostrarMenuGestionUsuarios();
                    break;
                case 2:
                    mostrarMenuGestionPrestamos();
                    break;
                case 3:
                    mostrarMenuGestionEmpleados();
                    break;
                case 4:
                    mostrarMenuGestionNovelas();
                    break;
                case 5:
                    mostrarMenuGestionLibros();
                    break;
                case 6:
                    LOGGER.info(MenuConstantes.OPCION_VOLVER);
                    continuar = false;
                    break;
                default:
                    LOGGER.warning(MenuConstantes.OPCION_INVALIDA);
            }
        }
    }

    private void mostrarMenuGestionUsuarios() {
        LOGGER.info("\nGestión de usuarios");
        LOGGER.info("1. Agregar usuario");
        LOGGER.info("2. Actualizar usuario");
        LOGGER.info("3. Eliminar usuario");
        LOGGER.info("4. " + MenuConstantes.OPCION_VOLVER);

        int opcion = VistaUtil.obtenerOpcion();
        switch (opcion) {
            case 1:
                controladorCRUDUsuario.registrarUsuario();
                break;
            case 2:
                controladorCRUDUsuario.actualizarUsuario();
                break;
            case 3:
                controladorCRUDUsuario.eliminarUsuario();
                break;
            case 4:
                return;
            default:
                LOGGER.warning(MenuConstantes.OPCION_INVALIDA);
        }
    }

    private void mostrarMenuGestionPrestamos() {
        LOGGER.info("\nGestión de préstamos");
        LOGGER.info("1. Registrar préstamo");
        LOGGER.info("2. Actualizar préstamo");
        LOGGER.info("3. Ver todos los préstamos");
        LOGGER.info("4. " + MenuConstantes.OPCION_VOLVER);

        int opcion = VistaUtil.obtenerOpcion();
        switch (opcion) {
            case 1:
                controladorCRUDPrestamo.registrarPrestamo();
                break;
            case 2:
                controladorCRUDPrestamo.actualizarPrestamo();
                break;
            case 3:
                controladorCRUDPrestamo.obtenerTodosPrestamos();
                break;
            case 4:
                return;
            default:
                LOGGER.warning(MenuConstantes.OPCION_INVALIDA);
        }
    }

    private void mostrarMenuGestionEmpleados() {
        LOGGER.info("\nGestión de empleados");
        LOGGER.info("1. Agregar empleado");
        LOGGER.info("2. Actualizar empleado");
        LOGGER.info("3. Eliminar empleado");
        LOGGER.info("4. " + MenuConstantes.OPCION_VOLVER);

        int opcion = VistaUtil.obtenerOpcion();
        switch (opcion) {
            case 1:
                controladorCRUDEmpleado.registrarEmpleado();
                break;
            case 2:
                controladorCRUDEmpleado.actualizarEmpleado();
                break;
            case 3:
                controladorCRUDEmpleado.eliminarEmpleado();
                break;
            case 4:
                return;
            default:
                LOGGER.warning(MenuConstantes.OPCION_INVALIDA);
        }
    }

    private void mostrarMenuGestionNovelas() {
        LOGGER.info("\nGestión de novelas");
        LOGGER.info("1. Agregar novela");
        LOGGER.info("2. Actualizar novela");
        LOGGER.info("3. Eliminar novela");
        LOGGER.info("4. Ver todas las novelas");
        LOGGER.info("5. " + MenuConstantes.OPCION_VOLVER);

        int opcion = VistaUtil.obtenerOpcion();
        switch (opcion) {
            case 1:
                controladorCRUDNovela.registrarNovela();
                break;
            case 2:
                controladorCRUDNovela.actualizarNovela();
                break;
            case 3:
                controladorCRUDNovela.eliminarNovela();
                break;
            case 4:
                controladorCRUDNovela.obtenerTodasNovelas();
                break;
            case 5:
                return;
            default:
                LOGGER.warning(MenuConstantes.OPCION_INVALIDA);
        }
    }

    private void mostrarMenuGestionLibros() {
        LOGGER.info("\nGestión de libros");
        LOGGER.info("1. Agregar libro");
        LOGGER.info("2. Actualizar libro");
        LOGGER.info("3. Eliminar libro");
        LOGGER.info("4. Ver todos los libros");
        LOGGER.info("5. " + MenuConstantes.OPCION_VOLVER);

        int opcion = VistaUtil.obtenerOpcion();
        switch (opcion) {
            case 1:
                controladorCRUDLibro.registrarLibro();
                break;
            case 2:
                controladorCRUDLibro.actualizarLibro();
                break;
            case 3:
                controladorCRUDLibro.eliminarLibro();
                break;
            case 4:
                controladorCRUDLibro.obtenerTodosLibros();
                break;
            case 5:
                return;
            default:
                LOGGER.warning(MenuConstantes.OPCION_INVALIDA);
        }
    }
}
