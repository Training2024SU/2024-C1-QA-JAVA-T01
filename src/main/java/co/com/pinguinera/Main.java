package co.com.pinguinera;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import co.com.pinguinera.modelos.DAO.PrestamoDAO;
import co.com.pinguinera.modelos.EstadoPrestamo;
import co.com.pinguinera.modelos.Prestamo;

public class Main {
    public static void main(String[] args) {
        try {
            // Intenta establecer la conexión a la base de datos
            Connection conexion = DataBase.conectar();

            // Si la conexión se establece con éxito, muestra un mensaje
            System.out.println("Conexión establecida correctamente.");

            Scanner scanner = new Scanner(System.in);

            System.out.println("Bienvenido a la Biblioteca La Pingüinera");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrarse como nuevo usuario");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    //iniciarSesion();
                    break;
                case 2:
                    //registrarUsuario();
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}