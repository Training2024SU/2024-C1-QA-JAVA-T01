package org.example.logica.control;

import org.example.integration.database.persistencia.CrudUsuario;
import org.example.model.Publicacion;
import org.example.model.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import static org.example.dialog.Menu.menuLector;
import static org.example.logica.control.ConstantesLogica.*;
import static org.example.logica.control.MetodosMain.pedirOpcion;
import static org.example.logica.control.PrestamoControl.confirmarOperacion;
import static org.example.logica.control.PublicacionControl.*;
import static org.example.model.Usuario.usuarios;

public class UsuarioControl {
    private static Usuario usuario;
    static CrudUsuario crudUsuario;
    private static final Logger logger=Logger.getLogger(UsuarioControl.class.getName());
    public static final List<Publicacion> librosSeleccionados = new ArrayList<>();
    public static final List<Publicacion> novelasSeleccionados = new ArrayList<>();

    public UsuarioControl() {
        crudUsuario = new CrudUsuario();
    }

    public void crearUsuario() throws SQLException {
        if (usuarios.isEmpty()) {
            crudUsuario.seleccionarDatos();
        }
        Usuario usuario = solicitudDatosUsuario();
        crudUsuario.crearEntidad(usuario);
        usuarios.add(usuario);
        logger.info(USUARIO_CREADO);
    }

    public Usuario iniciarSesionUsuario() throws SQLException {
        if (usuarios.isEmpty()) {
            crudUsuario.seleccionarDatos();
        }
        List<Usuario> usuarios = Usuario.usuarios;
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        System.out.println(String.format(MSN_CORREO, TIPO_USUARIO));
        String correo = scanner.nextLine();
        System.out.println(String.format(MSN_CONTRASENA, TIPO_USUARIO));
        String contrasena = scanner.nextLine();

       return validarCredencialesUsuario(usuarios, correo, contrasena);
    }

    private static Usuario solicitudDatosUsuario() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println(String.format(MSN_NOMBRE, TIPO_USUARIO));
        String nombre = scanner.nextLine();
        System.out.println(String.format(MSN_CORREO, TIPO_USUARIO));
        String correo = scanner.nextLine();
        System.out.println(String.format(MSN_CONTRASENA, TIPO_USUARIO));
        String contrasena = scanner.nextLine();

        return new Usuario(nombre, correo, contrasena);
    }

    private static Usuario validarCredencialesUsuario(List<Usuario> usuarios, String correo, String contrasena) throws SQLException {
        if (usuarios.isEmpty()) {
            crudUsuario.seleccionarDatos();
        }
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equalsIgnoreCase(correo) && usuario.getContrasenna().equals(contrasena)) {
                System.out.println(INICIO_EXITOSO);
                return usuario;
            }
        }
        logger.warning(INICIO_FALLIDO);
        return null;
    }

    public static void mostrarMenuLector(Usuario usuarioActivo) throws SQLException {
        usuario = usuarioActivo;
        int option;
        boolean bandera = true;
        while (bandera) {
            menuLector();
            option = pedirOpcion();
            bandera = seleccionarOpciones(option, bandera);
        }
    }

    public static boolean seleccionarOpciones(int option, boolean bandera) throws SQLException {
        switch (option) {
            case 1:
                seleccionarLibros();
                break;
            case 2:
                seleccionarNovelas();
                break;
            case 3:
                seleccionarLibroPorAutor();
                break;
            case 4:
                seleccionarNovelaPorAutor();
                break;
            case 5:
                confirmarOperacion(usuario);
                break;
            case 0:
                bandera = false;
                break;
            default:
                System.out.println("ingrese una opcion válida");
        }
        return bandera;
    }

}
