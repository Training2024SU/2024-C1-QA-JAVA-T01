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

    // Método para establecer la conexión a la base de datos
    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
    }
}
