package co.com.biblioteca.ui;

import co.com.biblioteca.DAO.PrestamogDAO;
import co.com.biblioteca.DAO.UsuarioDAO;
import co.com.biblioteca.modelo.Prestamo;
import co.com.biblioteca.modelo.Usuario;
import java.time.LocalDate;
import java.util.List;

import static co.com.biblioteca.dialogo.ControlMenu.pedirOpcion;



public class MenuPrestamog {
    private static PrestamogDAO prestamogDAO = null;
    private static UsuarioDAO usuarioDAO = null;
    

    public MenuPrestamog(PrestamogDAO prestamogDAO, UsuarioDAO usuarioDAO) {
        this.prestamogDAO = prestamogDAO;
        this.usuarioDAO = usuarioDAO;
        
    }

    public static void main(String[] args) {
        PrestamogDAO prestamogDAO = new PrestamogDAO(new UsuarioDAO());
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        MenuPrestamog menuPrestamog = new MenuPrestamog(prestamogDAO, usuarioDAO);
        menuPrestamog.mostrarMenuPrestamog();
    }

    public static void mostrarMenuPrestamog() {
        int opcion;
        do {
            System.out.println("Menú de Préstamos:");
            System.out.println("1. Realizar préstamo");
            System.out.println("2. Devolver préstamo");
            System.out.println("3. Mostrar todos los préstamos");
            System.out.println("4. Mostrar préstamos activos");
            System.out.println("5. Entregar Grabacion");
            System.out.println("0. Salir");
            System.out.print("Ingrese la opción deseada: ");
            opcion = pedirOpcion();

            switch (opcion) {
                case 1:
                    realizarPrestamog();
                    break;
                case 2:
                    devolverGrabacion();
                    break;
                case 3:
                    mostrarTodosLosPrestamogs();
                    break;
                case 4:
                    verPrestamogsActivos();
                    break;
                case 5:
                    entregarGrabacion();
                    break;
                case 0:
                    System.out.println("Saliendo del menú de préstamos...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        } while (opcion != 0);
    }

    public static void realizarPrestamog() {
        System.out.println("==== Realizar Préstamo ====");
        System.out.print("Ingrese el ID del usuario: ");
        int idUsuario = pedirOpcion();
        System.out.print("Ingrese el ID de la grabacion: ");
        int idGrabacion = pedirOpcion();

        Usuario usuario = usuarioDAO.obtenerUsuario(idUsuario);
        if (usuario == null) {
            System.out.println("No se encontró ningún usuario con ese ID.");
        }else {
            String fechaPrestamog = LocalDate.now().toString();
            String fechaDevolucion = LocalDate.now().plusDays(15).toString();
            Prestamo prestamog;
            prestamog = new Prestamo(idUsuario, idGrabacion, fechaPrestamog, fechaDevolucion, "SOLICITADO");
            prestamogDAO.crearPrestamog(prestamog);
            System.out.println("Préstamo realizado con éxito.");
        }
    }

    private static void devolverGrabacion() {
        System.out.println("==== Devolver Grabacion ====");
        System.out.print("Ingrese el ID del préstamo: ");
        int idPrestamog = pedirOpcion();
        Prestamo prestamog = prestamogDAO.obtenerPrestamo(idPrestamog);
        if (prestamog == null) {
            System.out.println("No se encontró ningún préstamo con ese ID.");
            return;
        }
        prestamog.setFechaDevolucion(LocalDate.now().toString());
        prestamog.setEstado("FINALIZADO");
        prestamogDAO.actualizarPrestamo(prestamog);
        System.out.println("Grabacion devuelta con éxito.");
    }
    private static void entregarGrabacion() {
        System.out.println("==== Devolver Libro ====");
        System.out.print("Ingrese el ID del préstamo: ");
        int idPrestamo = pedirOpcion();
        Prestamo prestamo = prestamogDAO.obtenerPrestamo(idPrestamo);
        if (prestamo == null) {
            System.out.println("No se encontró ningún préstamo con ese ID.");
            return;
        }
        prestamo.setFechaDevolucion(LocalDate.now().toString());
        prestamo.setEstado("FINALIZADO");
        prestamogDAO.actualizarPrestamo(prestamo);
        System.out.println("Libro devuelto con éxito.");
    }

    private static void mostrarTodosLosPrestamogs() {
        System.out.println("==== Todos los Préstamos ====");
        List<Prestamo> prestamogs = prestamogDAO.obtenerTodosLosPrestamos();
        if (prestamogs.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
        } else {
            for (Prestamo prestamog : prestamogs) {
                System.out.println(prestamog);
            }
        }
    }

    private static void verPrestamogsActivos() {
        System.out.println("==== Préstamos Activos ====");
        List<Prestamo> prestamogs = prestamogDAO.obtenerPrestamosActivos();
        if (prestamogs.isEmpty()) {
            System.out.println("No hay préstamos activos.");
        } else {
            for (Prestamo prestamog : prestamogs) {
                System.out.println(prestamog);
            }
        }
    }
}


