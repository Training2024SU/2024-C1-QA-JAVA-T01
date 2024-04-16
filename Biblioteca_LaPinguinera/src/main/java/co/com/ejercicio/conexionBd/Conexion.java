package co.com.ejercicio.conexionBd;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    static Dotenv dotenv = Dotenv.configure().load();
        private static final String URL_BD = dotenv.get("DB_URL");
        private static final String USER = dotenv.get("DB_USER");
        private static final String PASSWORD = dotenv.get("DB_PASSWORD");

        public static Connection obtenerConexion() throws SQLException {
            return DriverManager.getConnection(URL_BD, USER, PASSWORD);
        }
    }

