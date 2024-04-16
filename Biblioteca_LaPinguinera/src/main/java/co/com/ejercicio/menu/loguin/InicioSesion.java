package co.com.ejercicio.menu.loguin;

import co.com.ejercicio.menu.autenticacion.Autenticacion;
import co.com.ejercicio.util.enums.Roles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static co.com.ejercicio.conexionBd.constantesCRUD.QueryConstante.SELECT_LOGUIN_ASISTENTE;
import static co.com.ejercicio.conexionBd.constantesCRUD.QueryConstante.SELECT_LOGUIN_USER;
import static co.com.ejercicio.menu.dialogos.MenuPrincipal.mostrarMenu;
import static co.com.ejercicio.menu.menuPrincipal.MenuGestionAdministrador.menuAdministrador;
import static co.com.ejercicio.principal.Main.logger;


public class InicioSesion {

    public static void ingresarComoAdministrador() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingresando como administrador");
        System.out.println("Por favor, inicie sesión.");
        System.out.println("Ingrese su correo electrónico:");
        String correo = scanner.nextLine();
        System.out.println("Ingrese su contraseña:");
        String contrasena = scanner.nextLine();

        boolean autenticado = Autenticacion.autenticarAdmin(correo, contrasena);
        if (autenticado) {
            System.out.println("¡Bienvenido al sistema como administrador!");
            logger.info("Inicio de sesión exitoso para el " + Roles.TIPO_UNO.getvalue());
            menuAdministrador();
        } else {
            System.out.println("Credenciales incorrectas. Acceso denegado.");
            mostrarMenu();
        }

        scanner.close();
    }

    public static boolean iniciarSesionUsuario(Connection conn, String correo, String contrasenia) throws SQLException {
        try (PreparedStatement statement = conn.prepareStatement(SELECT_LOGUIN_USER)) {
            statement.setString(1, correo);
            statement.setString(2, contrasenia);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
    public static boolean iniciarSesionAsistente(Connection conn, String correo, String contrasenia) throws SQLException {
        try (PreparedStatement statement = conn.prepareStatement(SELECT_LOGUIN_ASISTENTE)) {
            statement.setString(1, correo);
            statement.setString(2, contrasenia);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
}

