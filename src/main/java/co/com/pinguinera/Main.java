package co.com.pinguinera;

import co.com.pinguinera.DAO.*;
import co.com.pinguinera.interfaces.LibroRepositorio;
import co.com.pinguinera.interfaces.NovelaRepositorio;
import co.com.pinguinera.interfaces.UsuarioRepositorio;
import co.com.pinguinera.interfaces.UsuarioRolesRepositorio;
import co.com.pinguinera.interfaces.PrestamoRepositorio;
import co.com.pinguinera.controladores.RegistrarUsuarioControlador;
import co.com.pinguinera.vistas.MenuPrincipal;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            // Establecer la conexión a la base de datos
            Connection conexion = DataBase.conectar();
            System.out.println("Conexión establecida correctamente.");

            // Crear instancias de los DAOs necesarios
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
            RolDAO rolDAO = new RolDAO(conexion);
            UsuarioRolesDAO usuarioRolesDAO = new UsuarioRolesDAO(conexion);
            AutenticacionDAO autenticacionDAO = new AutenticacionDAO(conexion);
            LibroDAO libroDAO = new LibroDAO(conexion);
            NovelaDAO novelaDAO = new NovelaDAO(conexion);
            PrestamoDAO prestamoDAO = new PrestamoDAO(conexion); // Instancia de PrestamoDAO

            // Crear las interfaces necesarias a partir de los DAOs
            UsuarioRepositorio usuarioRepositorio = usuarioDAO;
            UsuarioRolesRepositorio usuarioRolesRepositorio = usuarioRolesDAO;
            LibroRepositorio libroRepositorio = libroDAO;
            NovelaRepositorio novelaRepositorio = novelaDAO;
            PrestamoRepositorio prestamoRepositorio = prestamoDAO; // Crear PrestamoRepositorio

            // Crear una instancia de Scanner para usar en toda la aplicación
            Scanner scanner = new Scanner(System.in);

            // Crear instancia de RegistrarUsuarioControlador
            RegistrarUsuarioControlador registrarUsuarioControlador = new RegistrarUsuarioControlador(usuarioDAO, rolDAO, usuarioRolesDAO, scanner);

            // Crear instancia de MenuPrincipal y pasar todas las dependencias requeridas
            MenuPrincipal menuPrincipal = new MenuPrincipal(
                    registrarUsuarioControlador,
                    autenticacionDAO,
                    scanner,
                    usuarioRepositorio,
                    usuarioRolesRepositorio,
                    libroRepositorio,
                    novelaRepositorio,
                    prestamoRepositorio // Pasar PrestamoRepositorio a MenuPrincipal
            );

            // Iniciar la aplicación
            menuPrincipal.mostrarMenuPrincipal();

            // Cerrar la conexión a la base de datos
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
