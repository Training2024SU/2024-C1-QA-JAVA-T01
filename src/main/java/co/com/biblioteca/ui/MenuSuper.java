package co.com.biblioteca.ui;

import co.com.biblioteca.DAO.EmpleadoDAO;
import co.com.biblioteca.modelo.Empleado;
import java.sql.SQLException;
import java.util.Scanner;

import static co.com.biblioteca.dialogo.ControlMenu.pedirString;
import static co.com.biblioteca.ui.MenuUsuario.iniciarSesion;

public class MenuSuper {
    private static EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    private static Scanner scanner = new Scanner(System.in);


    public MenuSuper(EmpleadoDAO empleadoDAO) {
        MenuSuper.empleadoDAO = empleadoDAO;

    }

    public static void main(String[] args) {
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        Scanner scanner = new Scanner(System.in);
        MenuSuper menuEmpleado = new MenuSuper(empleadoDAO);
        menuEmpleado.mostrarMenu();
    }

    public static void mostrarMenu() {
        int opcion;
        do {
            System.out.println("Menú de Super:");
            System.out.println("1. Crear nuevo empleado");
            System.out.println("2. Ver información de empleado");
            System.out.println("3. Actualizar información de empleado");
            System.out.println("4. Eliminar empleado");
            System.out.println("5. Ver todos los empleados");
            System.out.println("0. Salir");
            System.out.print("Ingrese la opción deseada: ");
            opcion = obtenerEntero();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    crearEmpleado();
                    break;
                case 2:
                    verInformacionEmpleado();
                    break;
                case 3:
                    actualizarInformacionEmpleado();
                    break;
                case 4:
                    eliminarEmpleado();
                    break;
                case 5:
                    verTodosLosEmpleados();
                    break;
                case 0:
                    System.out.println("Saliendo del menú de empleados...");
                    iniciarSesion();
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        } while (opcion != 0);

    }

    private static void crearEmpleado() {
        System.out.println("==== Crear Nuevo Empleado ====");
        System.out.print("Ingrese el nombre del empleado: ");
        String nombre = pedirString();
        System.out.print("Ingrese el correo del empleado: ");
        String correo = pedirString();
        System.out.print("Ingrese la contraseña del empleado: ");
        String contrasena = pedirString();
        System.out.print("Ingrese el rol del empleado: ");
        String rol = pedirString();

        Empleado nuevoEmpleado = new Empleado(nombre, correo, contrasena, rol);
        empleadoDAO.crearEmpleado(nuevoEmpleado);
        System.out.println("¡Empleado creado con éxito!.");
    }

    public static void iniciarSesionEmpleado() {
        System.out.println("==== Iniciar Sesión ====");
        System.out.print("Ingrese el correo del empleado: ");
        String correo = pedirString();
        System.out.print("Ingrese la contraseña del empleado: ");
        String contrasena = pedirString();

        try {
            Empleado empleado = empleadoDAO.iniciarSesion(correo, contrasena);
            if (empleado != null) {
                System.out.println("Inicio de sesión exitoso. Bienvenido, " + empleado.getNombre() + ".");
            } else {
                System.out.println("Correo o contraseña incorrectos. Por favor, intente de nuevo.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void verInformacionEmpleado() {
        System.out.println("==== Ver Información de Empleado ====");
        System.out.print("Ingrese el ID del empleado: ");
        int idEmpleado = obtenerEntero();

        Empleado empleado = empleadoDAO.obtenerEmpleado(idEmpleado);
        if (empleado != null) {
            System.out.println("Información del empleado:");
            System.out.println(empleado);
        } else {
            System.out.println("No se encontró ningún empleado con ese ID.");
        }
    }

    private static void actualizarInformacionEmpleado() {
        System.out.println("\nActualizar Información de Empleado");

        System.out.print("Ingrese el ID del empleado a actualizar: ");
        int idEmpleado = obtenerEntero();

        Empleado empleado = empleadoDAO.obtenerEmpleado(idEmpleado);
        if (empleado != null) {
            System.out.println("Ingrese los nuevos datos del empleado:");
            System.out.print("Nuevo nombre: ");
            String nuevoNombre = pedirString();
            System.out.print("Nuevo correo: ");
            String nuevoCorreo = pedirString();
            empleado.setCorreo(nuevoCorreo);
            System.out.print("Nueva contraseña: ");
            String nuevaContrasena = pedirString();;
            System.out.print("Nuevo rol: ");
            String nuevoRol = pedirString();

            empleado.setNombre(nuevoNombre);
            empleado.setCorreo(nuevoCorreo);
            empleado.setContrasena(nuevaContrasena);
            empleado.setRol(nuevoRol);

            empleadoDAO.actualizarEmpleado(empleado);
            System.out.println("Información del empleado actualizada con éxito.");
        } else {
            System.out.println("No se encontró ningún empleado con ese ID.");
        }
    }

    private static void eliminarEmpleado() {
        System.out.println("==== Eliminar Empleado ====");
        System.out.print("Ingrese el ID del empleado a eliminar: ");
        int idEmpleado = obtenerEntero();

        Empleado empleado = empleadoDAO.obtenerEmpleado(idEmpleado);
        if (empleado != null) {
            empleadoDAO.eliminarEmpleado(idEmpleado);
            System.out.println("Empleado eliminado correctamente.");
        } else {
            System.out.println("No se encontró ningún empleado con ese ID.");
        }
    }

    private static void verTodosLosEmpleados() {
        System.out.println("==== Todos los Empleados ====");
        empleadoDAO.obtenerTodosLosEmpleados().forEach(System.out::println);
    }

    private static int obtenerEntero() {
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, ingrese un número válido.");
            scanner.next(); // Limpiar el buffer del escáner
        }
        return scanner.nextInt();
    }

}
