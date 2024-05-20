package co.com.biblioteca.ui;
import co.com.biblioteca.DAO.LibroDAO;
import co.com.biblioteca.DAO.PrestamoDAO;
import co.com.biblioteca.DAO.PrestamogDAO;
import co.com.biblioteca.DAO.UsuarioDAO;
import co.com.biblioteca.modelo.Libro;
import co.com.biblioteca.modelo.Usuario;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import static co.com.biblioteca.dialogo.ControlMenu.pedirOpcion;
import static co.com.biblioteca.dialogo.ControlMenu.pedirString;
import static co.com.biblioteca.ui.MenuGrabacion.buscarGrabacion;
import static co.com.biblioteca.ui.MenuPrestamo.realizarPrestamo;
import static co.com.biblioteca.ui.MenuPrestamog.realizarPrestamog;
import static co.com.biblioteca.ui.MenuSuper.iniciarSesionEmpleado;


public class MenuUsuario {
    private static final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private static final Scanner scanner = new Scanner(System.in);

    public static Usuario iniciarSesion() {
        int opcion;
        do {
            System.out.println("\n-----Bienvenido a la Biblioteca-----");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrarse");
            System.out.println("3. Iniciar sesion como Empleado");
            System.out.println("0. Salir");
            System.out.print("Ingrese su opción: ");
            opcion = pedirOpcion();
            switch (opcion) {
                case 1:
                    return iniciarSesionUsuario();
                case 2:
                    registrarUsuario();
                    return null;
                case 3:
                    iniciarSesionEmpleado();
                    return null;
                case 0:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    return null;
            }
        } while (opcion != 0);
        return null;
    }

    private static Usuario iniciarSesionUsuario() {
        System.out.println("\nInicio de sesión");
        System.out.print("Ingrese su correo electrónico: ");
        String correo = scanner.next();
        System.out.print("Ingrese su contraseña: ");
        String contrasena = scanner.next();

        try {
            Usuario usuario = usuarioDAO.iniciarSesion(correo, contrasena);
            if (usuario != null) {
                System.out.println("\n¡Inicio de sesión exitoso!");
                System.out.println("Bienvenido, " + usuario.getNombre());
                menuUsuarioLogueado(usuario);
                return usuario;
            } else {
                System.out.println("\nUsuario con correo o contraseña incorrecta");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("\nError al intentar iniciar sesión: " + e.getMessage());
            return null;
        }
    }

    private static void menuUsuarioLogueado(Usuario usuario) {
        int opcion;
        do {
            System.out.println("\nMenú de usuario");
            System.out.println("1. Ver información personal");
            System.out.println("2. Modificar contraseña");
            System.out.println("3. Buscar libros");
            System.out.println("4. Buscar grabacion");
            System.out.println("5. Solicitar préstamos libro");
            System.out.println("6. Solicitar préstamos grabacion");
            System.out.println("0. Cerrar sesión");
            System.out.print("Ingrese su opción: ");
            opcion = pedirOpcion();
            switch (opcion) {
                case 1:
                    mostrarInformacionPersonal(usuario);
                    break;
                case 2:
                    modificarContrasena(usuario);
                    break;
                case 3:
                    buscarLibros();
                    break;
                case 4:
                    buscarGrabacion();
                    break;
                case 5:
                    PrestamoDAO prestamoDAO = new PrestamoDAO(new UsuarioDAO());
                    UsuarioDAO usuarioDAO = new UsuarioDAO();
                    Scanner scanner = new Scanner(System.in);
                    MenuPrestamo menuPrestamo = new MenuPrestamo(prestamoDAO, usuarioDAO, scanner);
                    realizarPrestamo();
                    break;
                 case 6:
                     PrestamogDAO prestamogDAO = new PrestamogDAO(new UsuarioDAO());
                     usuarioDAO = new UsuarioDAO();
                     MenuPrestamog menuPrestamog = new MenuPrestamog(prestamogDAO, usuarioDAO);
                     realizarPrestamog();

                    break;
                case 0:
                    System.out.println("¡Hasta luego!");
                    iniciarSesion();
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }
        } while (opcion != 0);
    }

    private static void buscarLibros() {
        System.out.println("\nBúsqueda de libros");
        System.out.print("Ingrese el título o autor del libro: ");
        String busqueda = pedirString();

        LibroDAO libroDAO = new LibroDAO();
        List<Libro> librosEncontrados = libroDAO.buscarLibros(busqueda);
        if (!librosEncontrados.isEmpty()) {
            System.out.println("Libros encontrados:");
            for (Libro libro : librosEncontrados) {
                System.out.println(libro.toString());
            }
        } else {
            System.out.println("No se encontraron libros con el criterio de búsqueda proporcionado.");
        }
    }


    private static void modificarContrasena(Usuario usuario) {
        System.out.println("\nModificación de contraseña");
        System.out.print("Ingrese su nueva contraseña: ");
        String nuevaContrasena = scanner.next();
        usuario.setContrasena(nuevaContrasena);
        usuarioDAO.actualizarUsuario(usuario);
        System.out.println("¡Contraseña modificada exitosamente!");
    }

    public static void registrarUsuario() {
        System.out.println("\nRegistro de usuario");
        System.out.print("Ingrese su nombre: ");
        String nombre = pedirString();
        System.out.print("Ingrese su correo electrónico: ");
        String correo = pedirString();
        System.out.print("Ingrese su contraseña: ");
        String contrasena = pedirString();

        Usuario nuevoUsuario = new Usuario(nombre, correo, contrasena);
        usuarioDAO.crearUsuario(nuevoUsuario);
        System.out.println("\n¡Usuario registrado exitosamente!");
    }

    private static void mostrarInformacionPersonal(Usuario usuario) {
        System.out.println("\nInformación personal:");
        System.out.println("Nombre: " + usuario.getNombre());
        System.out.println("Correo electrónico: " + usuario.getCorreo());

    }
}
