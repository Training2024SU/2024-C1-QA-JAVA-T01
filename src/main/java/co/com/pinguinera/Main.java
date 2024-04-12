package co.com.pinguinera;

import co.com.pinguinera.DAO.*;
import co.com.pinguinera.controladores.RegistrarLectorControlador;
import co.com.pinguinera.interfaces.LibroRepositorio;
import co.com.pinguinera.interfaces.NovelaRepositorio;
import co.com.pinguinera.interfaces.PrestamoRepositorio;
import co.com.pinguinera.interfaces.UsuarioRepositorio;
import co.com.pinguinera.interfaces.RolesRepositorio; // Importar RolesRepositorio
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
            RolesDAO rolesDAO = new RolesDAO(conexion);
            AutenticacionDAO autenticacionDAO = new AutenticacionDAO(conexion);
            LibroDAO libroDAO = new LibroDAO(conexion);
            NovelaDAO novelaDAO = new NovelaDAO(conexion);
            PrestamoDAO prestamoDAO = new PrestamoDAO(conexion); // Instancia de PrestamoDAO

            // Crear las interfaces necesarias a partir de los DAOs
            UsuarioRepositorio usuarioRepositorio = usuarioDAO;
            RolesRepositorio rolesRepositorio = rolesDAO; // Asignar rolesDAO a rolesRepositorio
            LibroRepositorio libroRepositorio = libroDAO;
            NovelaRepositorio novelaRepositorio = novelaDAO;
            PrestamoRepositorio prestamoRepositorio = prestamoDAO; // Crear PrestamoRepositorio

            // Crear una instancia de Scanner para usar en toda la aplicación
            Scanner scanner = new Scanner(System.in);

            // Crear instancia de RegistrarLectorControlador
            RegistrarLectorControlador registrarLectorControlador = new RegistrarLectorControlador(
                    usuarioDAO, rolesDAO, scanner
            );

            // Crear instancia de MenuPrincipal y pasar todas las dependencias requeridas
            MenuPrincipal menuPrincipal = new MenuPrincipal(
                    registrarLectorControlador,
                    autenticacionDAO,
                    scanner,
                    usuarioRepositorio,
                    rolesRepositorio, // Pasar rolesRepositorio
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
