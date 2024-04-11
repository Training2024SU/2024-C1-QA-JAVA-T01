package co.com.pinguinera;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import co.com.pinguinera.controladores.GestionUsuario;
import co.com.pinguinera.modelos.DAO.RolDAO;
import co.com.pinguinera.modelos.DAO.UsuarioDAO;
import co.com.pinguinera.modelos.DAO.UsuarioRolesDAO;
import co.com.pinguinera.vistas.BibliotecaVista;

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

            // Crear instancia de GestionUsuario y pasarle los DAOs como argumentos
            GestionUsuario gestionUsuario = new GestionUsuario(usuarioDAO, rolDAO, usuarioRolesDAO);

            // Inicio de la aplicación
            boolean continuar = true;
            Scanner scanner = new Scanner(System.in);

            while (continuar) {
                BibliotecaVista bibliotecaVista = new BibliotecaVista(gestionUsuario); // Crear una instancia de BibliotecaVista
                bibliotecaVista.mostrarMenuPrincipal(); // Llamar al método mostrarMenuPrincipal en la instancia creada

                System.out.println("¿Permanecer en el sistema? (SI/NO)");
                if (scanner.hasNextLine()) {
                    String opcion = scanner.nextLine();
                    if (opcion.equalsIgnoreCase("NO")) {
                        continuar = false;
                    }
                }
            }

            // Cerrar la conexión a la base de datos
            conexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
