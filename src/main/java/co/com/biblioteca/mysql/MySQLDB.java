package co.com.biblioteca.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDB {

        private static final String URL = "jdbc:mysql://localhost:3306/bibliotecapingu";
        private static final String USUARIO = "root";
        private static final String CONTRASENA = "Kathe7557k*";

        public static Connection conexion;

        public static void iniciarConexion() {
            try {
                conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            } catch (Exception e) {
                System.out.println("Error de conexion Base de datos: " + e.getMessage());
            }
        }

         public static Connection obtenerConexion() {
            if (conexion == null) {
                iniciarConexion();
            }
            return conexion;
        }

        public static void cerrar() {
            try {
                conexion.close();
               conexion = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

