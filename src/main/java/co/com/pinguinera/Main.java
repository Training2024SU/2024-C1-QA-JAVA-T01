package co.com.pinguinera;

import co.com.pinguinera.capa_datos.conexionBD.ConexionBD;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try (Connection conexion = ConexionBD.conectar()) {
            // La conexión a la base de datos se ha abierto exitosamente.
            System.out.println("Conexión a la base de datos abierta con éxito.");

            // La conexión se cerrará automáticamente al final del bloque try-with-resources.
        } catch (SQLException e) {
            System.err.println("Error al interactuar con la base de datos: " + e.getMessage());
        }
    }
}
