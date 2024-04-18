package org.moreno.cristian.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.moreno.cristian.modelos.Autor;
import org.moreno.cristian.modelos.Libro;
import org.moreno.cristian.modelos.Publicacion;
import org.moreno.cristian.modelos.Usuario;
import org.moreno.cristian.modelos.enums.AreaConocimiento;
import org.moreno.cristian.modelos.enums.Rol;
import org.moreno.cristian.repositorios.RepositorioLibro;
import org.moreno.cristian.servicios.*;
import org.moreno.cristian.ui.crudNovelas.CrudNovelas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static org.moreno.cristian.ui.crudLibros.CrudLibros.crudLibros;
import static org.moreno.cristian.ui.crudNovelas.CrudNovelas.crudNovelas;

public class MenuAdmin {

    private static Scanner scan = ScannerUtil.obtenerScanner();
    private static final ServicioUsuario servicioUsuario = new ServicioUsuario();
    static Logger log = LogManager.getLogger(String.valueOf(MenuAdmin.class));

    public static void home(Usuario admin) {

        log.info("Bienvenido de nuevo " + admin.getNombre());

        while (true) {
            log.info("\nQué desea hacer?\n" +
                    "   1. Crear asistente \n" +
                    "   2. Ver usuarios \n" +
                    "   3. CRUD libros \n" +
                    "   4. CRUD novelas " +
                    "   5. Ver préstamos \n");

            String respuestaAdmin = scan.nextLine();

            switch(respuestaAdmin) {
                case "1":
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
//                    listarPrestamos(scan);
                    break;
                default:
                    log.error("Inténtalo de nuevo");
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
                log.info("\nUsuario guardado\n");
                break; // Salir del bucle
            } else {
                log.warn("Por favor, ingresa todos los datos correctamente.");
            }
        }
    }
    public static void verUsuarios() {

        Optional<List<Usuario>> usuarios = servicioUsuario.listarUsuarios();

        if (usuarios.isPresent()) {
            for (Usuario usuario : usuarios.get()) {
                log.info(usuario.toString());
            }
        } else {
            log.warn("No se encontró ningún usuario");
        }
    }



}
