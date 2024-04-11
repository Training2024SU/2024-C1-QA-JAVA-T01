package co.com.pinguinera.controladores;

import java.util.Scanner;
import co.com.pinguinera.DAO.RolDAO;
import co.com.pinguinera.DAO.UsuarioDAO;
import co.com.pinguinera.DAO.UsuarioRolesDAO;
import co.com.pinguinera.modelos.TipoRol;
import co.com.pinguinera.modelos.Usuario;
import co.com.pinguinera.vistas.Notificaciones;

public class RegistrarUsuarioControlador {
    private UsuarioDAO usuarioDAO;
    private RolDAO rolDAO;
    private UsuarioRolesDAO usuarioRolesDAO;
    private Scanner scanner; // Agregar un atributo para `Scanner`

    public RegistrarUsuarioControlador(UsuarioDAO usuarioDAO, RolDAO rolDAO, UsuarioRolesDAO usuarioRolesDAO, Scanner scanner) {
        this.usuarioDAO = usuarioDAO;
        this.rolDAO = rolDAO;
        this.usuarioRolesDAO = usuarioRolesDAO;
        this.scanner = scanner; // Asignar el objeto `Scanner`
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
        // Asignar el rol por defecto de Lector
        TipoRol rol = TipoRol.LECTOR;
        rolDAO.agregarRol(rol);

        // Asignar el rol al usuario
        nuevoUsuario.setRol(rol);

        // Agregar el nuevo usuario a la base de datos
        usuarioDAO.agregarUsuario(nuevoUsuario);
        Notificaciones.mostrarMensajeUsuarioAgregado();

        // Obtener el ID del usuario recién creado
        int usuarioID = nuevoUsuario.getUsuarioID(); // Suponiendo que el método `getUsuarioID` devuelve el ID del usuario

        // Asignar el rol al usuario en la tabla UsuarioRoles
        usuarioRolesDAO.asignarRolAUsuario(usuarioID, rol.getRolID());
        Notificaciones.mostrarRolAsignado(rol);
    }
}
