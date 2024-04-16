package co.com.pinguinera.datos.conexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    // Nombres de las variables de entorno
    private static final String URL_ENV = "DB_URL";
    private static final String USUARIO_ENV = "DB_USUARIO";
    private static final String CONTRASEÑA_ENV = "DB_CONTRASENA";

    // Variable para la conexión
    private static Connection conexion;

    // Constructor privado para evitar instanciación externa
    private ConexionBD() {}

    // Método estático para obtener la conexión única a la base de datos
    public static Connection conectar() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            String url = System.getenv(URL_ENV);
            String usuario = System.getenv(USUARIO_ENV);
            String contraseña = System.getenv(CONTRASEÑA_ENV);

            // Establecemos la conexión con los valores obtenidos de las variables de entorno
            conexion = DriverManager.getConnection(url, usuario, contraseña);
        }
        return conexion;
    }

}
