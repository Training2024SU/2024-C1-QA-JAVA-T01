package co.com.pinguinera.datos.conexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    // Variables para la conexión
    private static final String URL = "jdbc:mysql://localhost:3306/pinguinera";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "#32zvv48dH";

    // Variable para la conexión
    private static Connection conexion;

    // Constructor privado para evitar instanciación externa
    private ConexionBD() {}

    // Método estático para obtener la conexión única a la base de datos
    public static Connection conectar() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            // Establecemos la conexión con los valores directamente especificados
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
        }
        return conexion;
    }
}
