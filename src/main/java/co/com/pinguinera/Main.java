package co.com.pinguinera;

import co.com.pinguinera.datos.conexionBD.ConexionBD;
import co.com.pinguinera.vistas.MenuPrincipal;
import co.com.pinguinera.vistas.MenuPrincipalFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try (Connection conexion = ConexionBD.conectar()) {
            System.out.println("Conexión a la base de datos abierta con éxito.");

            // Llamar a la fábrica para obtener la instancia de MenuPrincipal
            MenuPrincipal menuPrincipal = MenuPrincipalFactory.crearMenuPrincipal(conexion);

            // Mostrar el menú principal
            menuPrincipal.mostrarMenu();
        } catch (SQLException e) {
            System.err.println("Error al interactuar con la base de datos: " + e.getMessage());
        }
    }
}
