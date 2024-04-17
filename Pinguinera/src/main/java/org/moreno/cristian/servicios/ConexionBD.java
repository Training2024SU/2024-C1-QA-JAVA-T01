package org.moreno.cristian.servicios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBD {

    private static String URL;
    private static String USER;
    private static String PASSWORD;

    private static String propertiesFilePath = "C:\\Users\\crism\\Desktop\\sofka\\Trabajos\\2024-C1-QA-JAVA-T01\\Pinguinera\\src\\main\\resources\\app.properties";


    private static Connection connection;

    public static Connection obtenerConexion() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {

                Properties appProps = new Properties();
                appProps.load(new FileInputStream(propertiesFilePath));

                URL = System.getenv(appProps.get("db.url").toString());

                USER = System.getenv(appProps.get("db.user").toString());

                PASSWORD = System.getenv(appProps.get("db.password").toString());


            } catch (FileNotFoundException e) {
                throw new RuntimeException("app.properties file not found", e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }

    public static void cerrarConexion() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
