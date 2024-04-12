package co.com.pinguinera.controladores;

import java.util.Scanner;
import co.com.pinguinera.interfaces.RolesRepositorio;
import co.com.pinguinera.interfaces.UsuarioRepositorio;
import co.com.pinguinera.modelos.TipoRol;
import co.com.pinguinera.modelos.Usuario;
import co.com.pinguinera.vistas.Notificaciones;

public class GestionarUsuariosAdministradorControlador {

    private final UsuarioRepositorio usuarioRepositorio;
    private final RolesRepositorio rolesRepositorio;
    private final Scanner scanner;

    public GestionarUsuariosAdministradorControlador(UsuarioRepositorio usuarioRepositorio, RolesRepositorio rolesRepositorio, Scanner scanner) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.rolesRepositorio = rolesRepositorio;
        this.scanner = scanner;
    }

    public void registrarUsuarioConRol() {
        // Recolectar datos del nuevo usuario
        Usuario nuevoUsuario = recolectarDatosUsuario();

        // Registrar el nuevo usuario con los datos proporcionados
        registrarNuevoUsuario(nuevoUsuario);

        // Solicitar al usuario que elija un rol para el nuevo usuario
        TipoRol rolSeleccionado = elegirRolParaUsuario();

        // Verificar si el rol seleccionado es ADMINISTRADOR y si ya existe un administrador
        if (rolSeleccionado == TipoRol.ADMINISTRADOR) {
            boolean existeAdministrador = rolesRepositorio.existeUsuarioConRolAdministrador();
            if (existeAdministrador) {
                System.out.println("Ya existe un usuario con el rol de ADMINISTRADOR. No se puede agregar otro.");
                return; // Salir de la función sin asignar el rol
            }
        }

        // Asignar el rol seleccionado al nuevo usuario
        asignarRolAUsuario(nuevoUsuario, rolSeleccionado);
    }

    public Usuario recolectarDatosUsuario() {
        // Solicitar al usuario los datos para crear un nuevo usuario
        System.out.println("Ingrese el nombre del nuevo usuario:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el correo electrónico del nuevo usuario:");
        String correo = scanner.nextLine();

        System.out.println("Ingrese la contraseña del nuevo usuario:");
        String contraseña = scanner.nextLine();

        // Crear un nuevo usuario con los datos proporcionados
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setCorreo(correo);
        nuevoUsuario.setContraseña(contraseña);

        return nuevoUsuario;
    }

    private void registrarNuevoUsuario(Usuario nuevoUsuario) {
        // Agregar el nuevo usuario a la base de datos
        usuarioRepositorio.agregarUsuario(nuevoUsuario);
        Notificaciones.mostrarMensajeUsuarioAgregado();
    }

    private TipoRol elegirRolParaUsuario() {
        // Preguntar al usuario cuál rol desea asignar al nuevo usuario
        System.out.println("Elija un rol para el nuevo usuario:");
        System.out.println("1. Administrador");
        System.out.println("2. Asistente");

        int opcion = Integer.parseInt(scanner.nextLine());

        if (opcion == 1) {
            return TipoRol.ADMINISTRADOR;
        } else if (opcion == 2) {
            return TipoRol.ASISTENTE;
        } else {
            System.out.println("Opción no válida. Intente de nuevo.");
            return elegirRolParaUsuario();
        }
    }

    private void asignarRolAUsuario(Usuario nuevoUsuario, TipoRol rol) {
        // Asignar el rol seleccionado al usuario
        int usuarioID = nuevoUsuario.getUsuarioID();
        rolesRepositorio.asignarRolAUsuario(usuarioID, rol.getRolID());
        Notificaciones.mostrarRolAsignado(rol);
    }
}
