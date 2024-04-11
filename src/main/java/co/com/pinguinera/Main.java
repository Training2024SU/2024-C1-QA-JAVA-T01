package co.com.pinguinera;

import java.sql.Connection;
import java.sql.SQLException;

import co.com.pinguinera.DataBase;
import co.com.pinguinera.controladores.RegistrarUsuarioControlador;
import co.com.pinguinera.modelos.DAO.RolDAO;
import co.com.pinguinera.modelos.DAO.UsuarioDAO;
import co.com.pinguinera.modelos.DAO.UsuarioRolesDAO;
import co.com.pinguinera.vistas.MenuPrincipal;
import co.com.pinguinera.modelos.DAO.AutenticacionDAO;
import co.com.pinguinera.DataBase;

public class Main {
    public static void main(String[] args) {
        try {
            // Intenta establecer la conexión a la base de datos
            Connection conexion = DataBase.conectar();

            // Si la conexión se establece con éxito, muestra un mensaje
            System.out.println("Conexión establecida correctamente.");

            // Crear instancias de los DAOs necesarios
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
            RolDAO rolDAO = new RolDAO(conexion);
            UsuarioRolesDAO usuarioRolesDAO = new UsuarioRolesDAO(conexion);

            // Crear una instancia de AutenticacionDAO
            AutenticacionDAO autenticacionDAO = new AutenticacionDAO(conexion);

            // Crear instancia de RegistrarUsuarioControlador y pasarle los DAOs como argumentos
            RegistrarUsuarioControlador registrarUsuarioControlador = new RegistrarUsuarioControlador(usuarioDAO, rolDAO, usuarioRolesDAO);

            // Inicio de la aplicación
            MenuPrincipal menuPrincipal = new MenuPrincipal(registrarUsuarioControlador, autenticacionDAO); // Pasar la instancia de AutenticacionDAO
            menuPrincipal.mostrarMenuPrincipal(); // Llamar al método mostrarMenuPrincipal en la instancia creada

            // Cerrar la conexión a la base de datos
            conexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
