package co.com.biblioteca.ui;

import co.com.biblioteca.DAO.EmpleadoDAO;
import co.com.biblioteca.modelo.Empleado;
import java.util.Scanner;

import static co.com.biblioteca.ui.MenuUsuario.iniciarSesion;


public class MenuAdministrador {

    private static final Scanner scanner = new Scanner(System.in);
    private static final EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            mostrarMenuAdministrador();
            int opcion = obtenerOpcion();

            switch (opcion) {
                case 1:
                    agregarAsistente();
                    break;
                case 2:
                    MenuLibro.main(new String[0]);
                    break;
                case 3:
                    MenuGrabacion.main(new String[0]);
                    break;
                case 4:
                    MenuPrestamo.main(new String[0]);
                    break;
                case 0:
                    salir = true;
                    iniciarSesion();
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }
        }
    }

    public static void mostrarMenuAdministrador() {
        System.out.println("\nMenú de Administrador");
        System.out.println("1. Agregar nuevo asistente");
        System.out.println("2. Acceder al menú de libros");
        System.out.println("3. Acceder al menú de grabacion");
        System.out.println("4. Acceder al menú de préstamos");
        System.out.println("0. Salir");
        System.out.print("Ingrese su opción: ");
    }

    private static int obtenerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void agregarAsistente() {
        System.out.println("\nAgregar nuevo asistente");
        System.out.print("Ingrese el nombre del asistente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el correo del asistente: ");
        String correo = scanner.nextLine();
        System.out.print("Ingrese la contraseña del asistente: ");
        String contrasena = scanner.nextLine();

        Empleado nuevoEmpleado = new Empleado(nombre, correo, contrasena,"ASISTENTE");
        empleadoDAO.crearEmpleado(nuevoEmpleado);
        System.out.println("¡Asistente creado con éxito!.");
    }
}

