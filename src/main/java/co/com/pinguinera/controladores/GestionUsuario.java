package co.com.pinguinera.controladores;

import java.util.Scanner;
import co.com.pinguinera.modelos.DAO.RolDAO;
import co.com.pinguinera.modelos.DAO.UsuarioDAO;
import co.com.pinguinera.modelos.DAO.UsuarioRolesDAO;
import co.com.pinguinera.modelos.TipoRol;
import co.com.pinguinera.modelos.Usuario;

public class GestionUsuario {

    private UsuarioDAO usuarioDAO;
    private RolDAO rolDAO;
    private UsuarioRolesDAO usuarioRolesDAO;

    public GestionUsuario(UsuarioDAO usuarioDAO, RolDAO rolDAO, UsuarioRolesDAO usuarioRolesDAO) {
        this.usuarioDAO = usuarioDAO;
        this.rolDAO = rolDAO;
        this.usuarioRolesDAO = usuarioRolesDAO;
    }

    public void registrarLector() {
        Scanner scanner = new Scanner(System.in);

        // Asignar el rol por defecto de Lector
        TipoRol rol = TipoRol.LECTOR;
        rolDAO.agregarRol(rol);

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
        nuevoUsuario.setRol(rol);

        // Agregar el nuevo usuario a la base de datos
        usuarioDAO.agregarUsuario(nuevoUsuario);
        System.out.println("Usuario agregado correctamente.");

        // Obtener el ID del usuario recién creado
        int usuarioID = nuevoUsuario.getUsuarioID(); // Suponiendo que el método getUsuarioID() devuelve el ID del usuario

        // Asignar el rol al usuario en la tabla UsuarioRoles
        usuarioRolesDAO.asignarRolAUsuario(usuarioID, rol.getRolID());
        System.out.println("Rol \"" + rol.name() + "\" asignado al nuevo usuario.");

        // Mostrar mensaje de éxito
        System.out.println("Proceso completado exitosamente.");

        // Cerrar el scanner
        scanner.close();
    }
}
