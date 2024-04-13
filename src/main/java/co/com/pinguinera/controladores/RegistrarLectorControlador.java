package co.com.pinguinera.controladores;

import java.util.Scanner;

import co.com.pinguinera.DAO.RolesDAO;
import co.com.pinguinera.DAO.UsuarioDAO;
import co.com.pinguinera.vistas.Notificaciones;

public class RegistrarLectorControlador {

    private UsuarioDAO usuarioDAO;
    private RolesDAO rolesDAO; // Cambié UsuarioRolesDAO a RolesDAO
    private Scanner scanner;

    public RegistrarLectorControlador(UsuarioDAO usuarioDAO, RolesDAO rolesDAO, Scanner scanner) {
        this.usuarioDAO = usuarioDAO;
        this.rolesDAO = rolesDAO; // Asigna rolesDAO
        this.scanner = scanner;
    }

    public void registrarLector() {
        // Recolectar datos del nuevo usuario
        Usuario nuevoUsuario = recolectarDatosUsuario();

        // Registrar el nuevo usuario con los datos proporcionados
        registrarNuevoLector(nuevoUsuario);

        // Manejar la interfaz gráfica
        Notificaciones.mostrarMensajeExito();
    }

    private Usuario recolectarDatosUsuario() {
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

    private void registrarNuevoLector(Usuario nuevoUsuario) {
        // Agregar el nuevo usuario a la base de datos
        usuarioDAO.agregarUsuario(nuevoUsuario);
        Notificaciones.mostrarMensajeUsuarioAgregado();

        // Obtener el ID del usuario recién creado
        int usuarioID = nuevoUsuario.getUsuarioID();

        // Asignar el rol por defecto de Lector
        TipoRol rol = TipoRol.LECTOR;

        // Asignar el rol al usuario en la tabla UsuarioRoles
        rolesDAO.asignarRolAUsuario(usuarioID, rol.getRolID());
        Notificaciones.mostrarRolAsignado(rol);
    }
}
