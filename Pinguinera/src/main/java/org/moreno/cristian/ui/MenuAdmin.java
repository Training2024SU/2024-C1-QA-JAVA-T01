package org.moreno.cristian.ui;

import org.moreno.cristian.modelos.Autor;
import org.moreno.cristian.modelos.Libro;
import org.moreno.cristian.modelos.Publicacion;
import org.moreno.cristian.modelos.Usuario;
import org.moreno.cristian.modelos.enums.AreaConocimiento;
import org.moreno.cristian.modelos.enums.Rol;
import org.moreno.cristian.repositorios.RepositorioLibro;
import org.moreno.cristian.servicios.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import static org.moreno.cristian.ui.crudLibros.CrudLibros.crudLibros;
import static org.moreno.cristian.ui.crudNovelas.CrudNovelas.crudNovelas;

public class MenuAdmin {

    private static Scanner scan = new Scanner(System.in);
    private static final ServicioUsuario servicioUsuario = new ServicioUsuario();
    private static final RepositorioLibro servicioLibro;

    static {
        try {
            servicioLibro = new ServicioLibro(new ServicioPublicacion(ConexionBD.obtenerConexion(), new ServicioAutor(ConexionBD.obtenerConexion())), ConexionBD.obtenerConexion());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void home(Usuario admin) {

        System.out.println("Bienvenido de nuevo " + admin.getNombre());

        while (true) {
            System.out.println("\nQué desea hacer?\n" +
                    "   1. Crear asistente \n" +
                    "   2. Ver usuarios \n" +
                    "   3. CRUD libros \n" +
                    "   4. CRUD novelas " +
                    "   5. Ver préstamos \n");

            String respuestaAdmin = scan.nextLine();

            System.out.println("Respuesta admin: " + respuestaAdmin);

            switch(respuestaAdmin) {
                case "1":
                    System.out.println("opcion 1 elegida");
                    crearAsistente();
                    break;
                case "2":
                    verUsuarios();
                    break;
                case "3":
                    crudLibros();
                    break;
                case "4":
                    crudNovelas();
                    break;
                case "5":
//                    listarPrestamos();
                    break;
                default:
                    System.out.println("Intentalo de nuevo");
            }
        }

    }
    public static void crearAsistente() {

        while (true) {
            System.out.print("Ingresa nombre asistente: ");
            String nombreAsistente = scan.nextLine();
            System.out.print("Ingresa correo asistente: ");
            String correoAsistente = scan.nextLine();
            System.out.print("Ingresa contraseña asistente: ");
            String contraseniaAsistente = scan.nextLine();

            if (!nombreAsistente.isEmpty() && !correoAsistente.isEmpty() && !contraseniaAsistente.isEmpty()) {
                // Si ninguno de los campos está vacío
                Usuario newUser = new Usuario(nombreAsistente, correoAsistente, contraseniaAsistente, Rol.ASISTENTE);
                servicioUsuario.guardarUsuario(newUser);
                System.out.println("\nUsuario guardado\n");
                break; // Salir del bucle
            } else {
                System.out.println("Por favor, ingresa todos los datos correctamente.");
            }
        }
    }
    public static void verUsuarios() {

        Optional<ArrayList<Usuario>> usuarios = servicioUsuario.listarUsuarios();

        if (usuarios.isPresent()) {
            for (Usuario usuario : usuarios.get()) {
                System.out.println(usuario.toString());
            }
        } else {
            System.out.println("No se encontró ningún usuario");
        }
    }



}
