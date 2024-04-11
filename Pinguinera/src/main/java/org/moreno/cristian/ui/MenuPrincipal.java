package org.moreno.cristian.ui;

import org.moreno.cristian.modelos.Usuario;
import org.moreno.cristian.modelos.enums.Rol;
import org.moreno.cristian.servicios.ServicioUsuario;

import java.util.Optional;
import java.util.Scanner;

public class MenuPrincipal {

    private static Scanner scan = new Scanner(System.in);
    private static final ServicioUsuario servicioUsuario = new ServicioUsuario();

    public static void menuInicial() {
        while (true) {
            System.out.println("\nQué desea hacer?\n" +
                    "   1. Iniciar sesión \n" +
                    "   2. Registrarme \n");

            String respuestaUsuario = scan.nextLine();

            switch(respuestaUsuario) {
                case "1":
                    inicioSesion();
                    break;
                case "2":
                    registroUsuario();
                    break;
                default:
                    // code block
            }
        }
    }

    public static void inicioSesion () {

        while (true) {

            System.out.print("Ingresa tu correo: ");
            String correoUsuario = scan.nextLine();

            System.out.print("Ingresa tu contraseña: ");
            String contraseniaUsuario = scan.nextLine();

            Optional<Usuario> usuarioDb = servicioUsuario.validarCredenciales(correoUsuario, contraseniaUsuario);
            if (usuarioDb.isPresent()) {
                System.out.println(usuarioDb.get().toString());
                System.out.println("Bienvenido ");
                switch(usuarioDb.get().getRol()) {
                    case LECTOR:
                        menuLector();
                        break;
                    case ADMIN:
                        menuAdmin();
                        break;
                    case ASISTENTE:
                        menuAsistente();
                        break;
                }
            } else {
                System.out.println("Correo o contraseña erróneos, inténtalo de nuevo");
            }
        }
    }

    public static void registroUsuario() {

        while (true) {

            System.out.print("Ingresa tu nombre: ");
            String nombreUsuario = scan.nextLine();
            System.out.print("Ingresa tu correo: ");
            String correoUsuario = scan.nextLine();
            System.out.print("Ingresa tu contraseña: ");
            String contraseniaUsuario = scan.nextLine();

            if (!nombreUsuario.isEmpty() && !correoUsuario.isEmpty() && !contraseniaUsuario.isEmpty()) {
                // Si ninguno de los campos está vacío
                Usuario newUser = new Usuario(nombreUsuario, correoUsuario, contraseniaUsuario);
                servicioUsuario.guardarUsuario(newUser);
                System.out.println("\nUsuario guardado\n");
                break; // Salir del bucle
            } else {
                System.out.println("Por favor, ingresa todos los datos correctamente.");
            }

        }
    }

    public static void menuAdmin() {

        while (true) {
            System.out.println("\nQué desea hacer?\n" +
                    "1. Crear asistente \n" +
                    "2. Ver usuarios \n" +
                    "3. CRUD libros \n" +
                    "4. Ver préstamos");

            String respuestaAdmin = scan.nextLine();

            switch(respuestaAdmin) {
                case "1":
                    // code block
                    break;
                case "2":
                    // code block
                    break;
                case "3":
                    // code block
                    break;
                case "4":
                    // code block
                    break;
                default:
                    // code block
            }
        }

    }
    public static void menuAsistente() {

    }
    public static void menuLector() {

    }
}
