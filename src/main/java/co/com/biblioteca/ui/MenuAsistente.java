package co.com.biblioteca.ui;

import co.com.biblioteca.DAO.*;
import co.com.biblioteca.modelo.Grabacion;
import co.com.biblioteca.modelo.Libro;
import co.com.biblioteca.modelo.Prestamo;
import java.util.List;
import java.util.Scanner;
import static co.com.biblioteca.ui.MenuPrestamo.mostrarMenuPrestamo;
import static co.com.biblioteca.ui.MenuPrestamog.mostrarMenuPrestamog;
import static co.com.biblioteca.ui.MenuUsuario.iniciarSesion;


public class MenuAsistente {

    private static final GrabacionDAO grabacionDAO = new GrabacionDAO();
    private static final LibroDAO libroDAO = new LibroDAO();
    private static final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private static final PrestamoDAO prestamoDAO = new PrestamoDAO(usuarioDAO);
    private static final PrestamogDAO prestamogDAO = new PrestamogDAO(usuarioDAO);
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {

            mostrarMenuAsistente();
            int opcion = obtenerOpcion();

            switch (opcion) {
                case 1:
                    listarRecursosDisponibles();
                    break;
                case 2:
                    buscarRecurso();
                    break;
                case 3:
                   consultarPrestamosUsuario();
                    break;
                case 4:
                    MenuPrestamog.main(new String[0]);
                    break;
                case 5:
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

    public static void mostrarMenuAsistente() {
        System.out.println("\nMenú de Asistente");
        System.out.println("1. Listar Recursos disponibles");
        System.out.println("2. Buscar Recurso por título o autor");
        System.out.println("3. Consultar préstamos de un usuario");
        System.out.println("4. Acceder al menú de préstamos de grabacion");
        System.out.println("5. Acceder al menú de préstamos de libros");
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

    private static void listarRecursosDisponibles() {
        System.out.println("\nListado de recursos disponibles:");
        grabacionDAO.obtenerTodasLasGrabaciones().forEach(System.out::println);
        libroDAO.obtenerTodosLosLibros().forEach(System.out::println);
    }

    private static void buscarRecurso() {
        System.out.println("\nBuscar recurso por título o autor");

        System.out.print("Ingrese el título o autor a buscar: ");
        String criterioBusqueda = scanner.nextLine();

        List<Grabacion> grabacionesEncontradas = grabacionDAO.buscarGrabacion(criterioBusqueda);
        if (grabacionesEncontradas.isEmpty()) {
            System.out.println("No se encontraron grabaciones que coincidan con el criterio de búsqueda.");
        } else {
            System.out.println("Grabaciones encontradas:");
            for (Grabacion grabacion : grabacionesEncontradas) {
                System.out.println(grabacion);
            }
        }

        List<Libro> librosEncontrados = libroDAO.buscarLibros(criterioBusqueda);
        if (librosEncontrados.isEmpty()) {
            System.out.println("No se encontraron libros que coincidan con el criterio de búsqueda.");
        } else {
            System.out.println("Libros encontrados:");
            for (Libro libro : librosEncontrados) {
                System.out.println(libro);
            }
        }
    }


    private static void consultarPrestamosUsuario() {
        System.out.println("\nConsultar préstamos de un usuario");

        System.out.print("Ingrese el correo del usuario: ");
        String correoUsuario = scanner.nextLine();

        List<Prestamo> prestamosUsuario = prestamoDAO.obtenerPrestamosPorUsuario(correoUsuario);
        if (prestamosUsuario.isEmpty()) {
            System.out.println("No se encontraron préstamos para el usuario con correo: " + correoUsuario);
        } else {
            System.out.println("Préstamos del usuario con correo " + correoUsuario + ":");
            System.out.println("Libros encontrados:");
            for (Prestamo prestamo : prestamosUsuario) {
                System.out.println(prestamo);
            }
        }
        List<Prestamo> grabacionesEncontradas = prestamogDAO.obtenerPrestamosPorUsuario(correoUsuario);
        if (grabacionesEncontradas.isEmpty()) {
            System.out.println("No se encontraron grabaciones que coincidan con el criterio de búsqueda.");
        } else {
            System.out.println("Grabaciones encontradas:");
            for (Prestamo prestamo : grabacionesEncontradas) {
                System.out.println(prestamo);
            }
        }
    }
}
