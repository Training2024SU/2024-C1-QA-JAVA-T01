package co.com.pinguinera;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            // Intenta establecer la conexión a la base de datos
            Connection conexion = DataBase.conectar();

            // Si la conexión se establece con éxito, muestra un mensaje
            System.out.println("Conexión establecida correctamente.");

            // Cierra la conexión
            conexion.close();
        } catch (SQLException e) {
            // Si ocurre algún error al intentar establecer la conexión, muestra el mensaje de error
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            // También puedes imprimir la traza completa del error llamando a e.printStackTrace()
            // e.printStackTrace();
        }
    }
}
