package co.com.biblioteca.ui;

import co.com.biblioteca.DAO.PrestamoDAO;
import co.com.biblioteca.DAO.UsuarioDAO;
import co.com.biblioteca.modelo.Prestamo;
import co.com.biblioteca.modelo.Usuario;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static co.com.biblioteca.dialogo.ControlMenu.pedirOpcion;



public class MenuPrestamo {
    private static PrestamoDAO prestamoDAO = null;
    private static UsuarioDAO usuarioDAO = null;

    public MenuPrestamo(PrestamoDAO prestamoDAO, UsuarioDAO usuarioDAO, Scanner scanner) {
        this.prestamoDAO = prestamoDAO;
        this.usuarioDAO = usuarioDAO;
    }

    public static void main(String[] args) {
        PrestamoDAO prestamoDAO = new PrestamoDAO(new UsuarioDAO());
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Scanner scanner = new Scanner(System.in);
        MenuPrestamo menuPrestamo = new MenuPrestamo(prestamoDAO, usuarioDAO, scanner);
        menuPrestamo.mostrarMenuPrestamo();
    }

    public static void mostrarMenuPrestamo() {
        int opcion;
        do {
            System.out.println("Menú de Préstamos:");
            System.out.println("1. Realizar préstamo");
            System.out.println("2. Devolver préstamo");
            System.out.println("3. Entregar prestamo");
            System.out.println("4. Mostrar todos los préstamos");
            System.out.println("5. Mostrar préstamos activos");
            System.out.println("0. Salir");
            System.out.print("Ingrese la opción deseada: ");
            opcion = pedirOpcion();

            switch (opcion) {
                case 1:
                    realizarPrestamo();
                    break;
                case 2:
                    devolverLibro();
                    break;
                case 3:
                    entregarLibro();
                    break;
                case 4:
                    mostrarTodosLosPrestamos();
                    break;
                case 5:
                    verPrestamosActivos();
                    break;
                case 0:
                    System.out.println("Saliendo del menú de préstamos...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        } while (opcion != 0);
    }

    public static void realizarPrestamo() {
        System.out.println("==== Realizar Préstamo ====");
        System.out.print("Ingrese el ID del usuario: ");
        int idUsuario = pedirOpcion();
        System.out.print("Ingrese el ID del libro: ");
        int idLibro = pedirOpcion();

        Usuario usuario = usuarioDAO.obtenerUsuario(idUsuario);
        if (usuario == null) {
            System.out.println("No se encontró ningún usuario con ese ID.");
        }else {
            // Crear un nuevo prestamo utilizando el constructor vacío actualizado
            String fechaPrestamo = LocalDate.now().toString();
            String fechaDevolucion = LocalDate.now().plusDays(15).toString();
            Prestamo prestamo;
            prestamo = new Prestamo(idUsuario, idLibro, fechaPrestamo, fechaDevolucion, "SOLICITADO");
            prestamoDAO.crearPrestamo(prestamo);
            System.out.println("Préstamo realizado con éxito.");
        }
    }

    private static void devolverLibro() {
        System.out.println("==== Devolver Libro ====");
        System.out.print("Ingrese el ID del préstamo: ");
        int idPrestamo = pedirOpcion();
        Prestamo prestamo = prestamoDAO.obtenerPrestamo(idPrestamo);
        if (prestamo == null) {
            System.out.println("No se encontró ningún préstamo con ese ID.");
            return;
        }
        prestamo.setFechaDevolucion(LocalDate.now().toString());
        prestamo.setEstado("FINALIZADO");
        prestamoDAO.actualizarPrestamo(prestamo);
        System.out.println("Libro devuelto con éxito.");
    }

    private static void entregarLibro() {
        System.out.println("==== Entregar Libro ====");
        System.out.print("Ingrese el ID del préstamo: ");
        int idPrestamo = pedirOpcion();
        Prestamo prestamo = prestamoDAO.obtenerPrestamo(idPrestamo);
        if (prestamo == null) {
            System.out.println("No se encontró ningún préstamo con ese ID.");
            return;
        }
        prestamo.setFechaDevolucion(LocalDate.now().toString());
        prestamo.setEstado("REALIZADO");
        prestamoDAO.entregarPrestamo(prestamo);
        System.out.println("Libro devuelto con éxito.");
    }

    private static void mostrarTodosLosPrestamos() {
        System.out.println("==== Todos los Préstamos ====");
        List<Prestamo> prestamos = prestamoDAO.obtenerTodosLosPrestamos();
        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
        } else {
            for (Prestamo prestamo : prestamos) {
                System.out.println(prestamo);
            }
        }
    }

    private static void verPrestamosActivos() {
        System.out.println("==== Préstamos Activos ====");
        List<Prestamo> prestamos = prestamoDAO.obtenerPrestamosActivos();
        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos activos.");
        } else {
            for (Prestamo prestamo : prestamos) {
                System.out.println(prestamo);
            }
        }
    }
}


