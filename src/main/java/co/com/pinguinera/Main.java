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

            // Verificar si existe algún usuario con el rol de ADMINISTRADOR
            boolean existeAdmin = rolDAO.existeUsuarioConRolAdministrador();
            if (existeAdmin) {
                System.out.println("Ya existe un usuario con el rol de ADMINISTRADOR en la base de datos.");
            } else {
                System.out.println("No existe ningún usuario con el rol de ADMINISTRADOR en la base de datos.");
            }

            // Solo continuar si no existe ningún usuario con el rol de ADMINISTRADOR
            if (!existeAdmin) {
                // Agregar un nuevo rol (ADMINISTRADOR) si no existe
                TipoRol rol = TipoRol.ADMINISTRADOR;
                rolDAO.agregarRol(rol);
                System.out.println("Rol ADMINISTRADOR agregado correctamente.");

                // Agregar un nuevo usuario con el rol ADMINISTRADOR
                Usuario nuevoUsuario = new Usuario();
                nuevoUsuario.setNombre("John Doe");
                nuevoUsuario.setCorreo("administrador@pingu.com.co");
                nuevoUsuario.setContraseña("contraseñasegura123456");
                nuevoUsuario.setRol(rol);
                usuarioDAO.agregarUsuario(nuevoUsuario);
                System.out.println("Usuario agregado correctamente.");

                // Obtener el ID del usuario recién creado
                int usuarioID = nuevoUsuario.getUsuarioID();

                // Asignar el rol al usuario en la tabla UsuarioRoles
                usuarioRolesDAO.asignarRolAUsuario(usuarioID, rolDAO.buscarRolPorNombre(rol.name()).getRolID());
                System.out.println("Rol ADMINISTRADOR asignado a John Doe.");

                // Mostrar mensaje de éxito
                System.out.println("Proceso completado exitosamente.");
            }

            // Cerrar la conexión a la base de datos
            conexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
