package co.com.pinguinera;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    // URL de conexión a la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca_la_pinguinera";

    // Nombre de usuario y contraseña para acceder a la base de datos
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "#32zvv48dH";

    // Instancia única de la conexión
    private static Connection conexion;

    // Constructor privado para evitar instanciación externa
    private DataBase() {}

    // Método estático para obtener la conexión única a la base de datos
    public static Connection conectar() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            // Si no hay conexión o está cerrada, establecer una nueva conexión
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
        }
        return conexion;
    }
}
