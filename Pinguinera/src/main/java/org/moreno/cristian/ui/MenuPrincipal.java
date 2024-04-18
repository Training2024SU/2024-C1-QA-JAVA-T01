package org.moreno.cristian.ui;

import org.moreno.cristian.modelos.Usuario;
import org.moreno.cristian.servicios.ScannerUtil;
import org.moreno.cristian.servicios.ServicioUsuario;

import java.util.Optional;
import java.util.Scanner;

public class MenuPrincipal {

    private static Scanner scan = ScannerUtil.obtenerScanner();
    private static final ServicioUsuario servicioUsuario = new ServicioUsuario();

    public static void menuInicial() {
        while (true) {
            System.out.println("\nQué desea hacer?\n" +
                    "   1. Iniciar sesión \n" +
                    "   2. Registrarme \n" +
                    "   3. Salir \n");

            String respuestaUsuario = scan.nextLine();

            switch(respuestaUsuario) {
                case "1":
                    inicioSesion();
                    break;
                case "2":
                    registroUsuario();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Respuesta no válida");
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

                System.out.println("Bienvenido " + usuarioDb.get().getNombre() + "\n");
                switch(usuarioDb.get().getRol()) {
                    case LECTOR:
                        MenuLector.home(usuarioDb.get());
                        break;
                    case ADMIN:
                        MenuAdmin.home(usuarioDb.get());
                        break;
                    case ASISTENTE:
                        MenuAsistente.home(usuarioDb.get());
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

}
