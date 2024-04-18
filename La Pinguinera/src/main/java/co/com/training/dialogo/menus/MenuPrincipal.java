package co.com.training.dialogo.menus;

import co.com.training.integration.database.mysql.MySqlOperation;
import co.com.training.modelo.Usuario;
import co.com.training.util.enums.RolUsuario;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static co.com.training.operaciones.UsuarioCRUD.INSERT_ADMINISTRADOR;

public class MenuPrincipal {

    private static Map<String, Usuario> usuariosRegistrados = new HashMap<>();

    public static void mostrarMenuPrincipal() {

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("=== Menú Principal ===");
            System.out.println("¡Bienvenido a la Biblioteca La Pinguinera!");
            System.out.println("1. Ingresar");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            System.out.print("Escribe el número de la opción deseada: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    ingresar();
                    break;
                case 2:
                    registrarse();
                    break;
                case 3:
                    System.out.println("Gracias por usar la Biblioteca La Pinguinera. ¡Hasta luego!");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, elige una opción válida.");
            }
        }
        scanner.close();
    }

    private static void ingresar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Ingresar ===");
        System.out.print("Correo electrónico: ");
        String correoUsuario = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasenaUsuario = scanner.nextLine();
        System.out.print("Rol (ADMINISTRADOR, ASISTENTE, LECTOR): ");
        String rolUsuario = scanner.nextLine();

        Usuario usuario = usuariosRegistrados.get(correoUsuario);
        if (usuario != null && usuario.getContrasenaUsuario().equals(contrasenaUsuario)) {
            System.out.println("¡Bienvenido de vuelta, " + usuario.getNombreUsuario() + "!");
            // Aquí puedes agregar la lógica adicional para el usuario ingresado, como redireccionar a otro menú, etc.
        } else {
            System.out.println("Correo electrónico o contraseña incorrectos. Por favor, inténtalo de nuevo.");
        }
    }

    private static void registrarse() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Registrarse ===");
        System.out.print("Correo electrónico: ");
        String correo = scanner.nextLine();
        if (usuariosRegistrados.containsKey(correo)) {
            System.out.println("El correo electrónico ya está registrado. Por favor, ingresa otro.");
            return;
        }
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();
        System.out.print("Rol (ADMINISTRADOR, ASISTENTE, LECTOR): ");
        String rolInput = scanner.nextLine();

        RolUsuario rol = RolUsuario.valueOf(rolInput.toUpperCase());

        Usuario nuevoUsuario = new Usuario(nombre, contraseña, correo, rol);
        usuariosRegistrados.put(correo, nuevoUsuario);
        System.out.println("¡Registro exitoso, " + nombre + "! Ahora puedes iniciar sesión.");
    }

    public static void crearAdministrador(MySqlOperation mySqlOperation) {
        String correoUsuario = "administrador@pingu.com.co";
        String nombreUsuario = "John Doe";
        String contrasenaUsuario = "contrasenasegura123456";
        String rolUsuario = "ADMINISTRADOR";
        String statement = String.format(INSERT_ADMINISTRADOR, correoUsuario, nombreUsuario, contrasenaUsuario, rolUsuario);
        mySqlOperation.setSqlStatement(statement);
        mySqlOperation.executeSqlStatementVoid();
    }


}