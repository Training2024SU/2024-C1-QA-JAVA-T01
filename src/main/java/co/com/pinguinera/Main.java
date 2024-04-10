package co.com.pinguinera;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import co.com.pinguinera.modelos.DAO.RolDAO;
import co.com.pinguinera.modelos.DAO.UsuarioDAO;
import co.com.pinguinera.modelos.DAO.UsuarioRolesDAO;
import co.com.pinguinera.modelos.TipoRol;
import co.com.pinguinera.modelos.Usuario;

public class Main {
    public static void main(String[] args) {
        try {
            // Intenta establecer la conexión a la base de datos
            Connection conexion = DataBase.conectar();

            // Si la conexión se establece con éxito, muestra un mensaje
            System.out.println("Conexión establecida correctamente.");

            UsuarioDAO usuarioDAO = new UsuarioDAO(conexion); // Crear un objeto UsuarioDAO para interactuar con la base de datos
            RolDAO rolDAO = new RolDAO(conexion); // Crear un objeto RolDAO para interactuar con la base de datos
            UsuarioRolesDAO usuarioRolesDAO = new UsuarioRolesDAO(conexion); // Crear un objeto UsuarioRolesDAO para interactuar con la base de datos

            // Agregar un nuevo rol (ADMINISTRADOR) si no existe
            TipoRol rol = TipoRol.ADMINISTRADOR;
            if (rolDAO.buscarRolPorNombre(rol.name()) == null) {
                rolDAO.agregarRol(rol);
                System.out.println("Rol ADMINISTRADOR agregado correctamente.");
            }

            // Agregar un nuevo usuario con el rol ADMINISTRADOR
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre("Juan Pérez");
            nuevoUsuario.setCorreo("juan.perez@example.com");
            nuevoUsuario.setContraseña("admin123");
            nuevoUsuario.setRol(rol);
            usuarioDAO.agregarUsuario(nuevoUsuario);
            System.out.println("Usuario Juan Pérez agregado correctamente.");

            // Obtener el ID del usuario recién creado
            int usuarioID = nuevoUsuario.getUsuarioID();

            // Asignar el rol al usuario en la tabla UsuarioRoles
            usuarioRolesDAO.asignarRolAUsuario(usuarioID, rolDAO.buscarRolPorNombre(rol.name()).getRolID());
            System.out.println("Rol ADMINISTRADOR asignado a Juan Pérez.");

            // Mostrar mensaje de éxito
            System.out.println("Proceso completado exitosamente.");

            // Cerrar la conexión a la base de datos
            conexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
