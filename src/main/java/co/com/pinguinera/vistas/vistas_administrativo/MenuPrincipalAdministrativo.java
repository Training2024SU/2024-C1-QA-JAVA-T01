package co.com.pinguinera.vistas.vistas_administrativo;

import co.com.pinguinera.controladores.crud.*;
import co.com.pinguinera.vistas.VistaUtil;

import java.util.Scanner;

public class MenuPrincipalAdministrativo {

    private final ControladorCRUDUsuario controladorCRUDUsuario;
    private final ControladorCRUDPrestamo controladorCRUDPrestamo;
    private final ControladorCRUDEmpleado controladorCRUDEmpleado;
    private final ControladorCRUDNovela controladorCRUDNovela;
    private final ControladorCRUDLibro controladorCRUDLibro;
    private final Scanner scanner;

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
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nMenú administrativo");
            System.out.println("1. Gestión de usuarios");
            System.out.println("2. Gestión de préstamos");
            System.out.println("3. Gestión de empleados");
            System.out.println("4. Gestión de novelas");
            System.out.println("5. Gestión de libros");
            System.out.println("6. Salir");

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
                    System.out.println("Saliendo del menú...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }

    private void mostrarMenuGestionUsuarios() {
        System.out.println("\nGestión de usuarios");
        System.out.println("1. Agregar usuario");
        System.out.println("2. Actualizar usuario");
        System.out.println("3. Eliminar usuario");
        System.out.println("4. Volver");

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
                System.out.println("Opción no válida. Inténtelo de nuevo.");
        }
    }

    private void mostrarMenuGestionPrestamos() {
        System.out.println("\nGestión de préstamos");
        System.out.println("1. Registrar préstamo");
        System.out.println("2. Actualizar Prestamo");
        System.out.println("3. Ver todos los préstamos");
        System.out.println("4. Volver");

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
                System.out.println("Opción no válida. Inténtelo de nuevo.");
        }
    }

    private void mostrarMenuGestionEmpleados() {
        System.out.println("\nGestión de empleados");
        System.out.println("1. Agregar empleado");
        System.out.println("2. Actualizar empleado");
        System.out.println("3. Eliminar empleado");
        System.out.println("4. Volver");

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
                System.out.println("Opción no válida. Inténtelo de nuevo.");
        }
    }

    private void mostrarMenuGestionNovelas() {
        System.out.println("\nGestión de novelas");
        System.out.println("1. Agregar novela");
        System.out.println("2. Actualizar novela");
        System.out.println("3. Eliminar novela");
        System.out.println("4. Ver todas las novelas");
        System.out.println("5. Volver");

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
                System.out.println("Opción no válida. Inténtelo de nuevo.");
        }
    }

    private void mostrarMenuGestionLibros() {
        System.out.println("\nGestión de libros");
        System.out.println("1. Agregar libro");
        System.out.println("2. Actualizar libro");
        System.out.println("3. Eliminar libro");
        System.out.println("4. Ver todos los libros");
        System.out.println("5. Volver");

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
                System.out.println("Opción no válida. Inténtelo de nuevo.");
        }
    }

}
