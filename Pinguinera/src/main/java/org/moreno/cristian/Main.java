package org.moreno.cristian;

import org.moreno.cristian.servicios.ConexionBD;
import org.moreno.cristian.servicios.ScannerUtil;
import org.moreno.cristian.ui.MenuPrincipal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {

        MenuPrincipal.menuInicial();

//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            ConexionBD.cerrarConexion();
//            System.out.println("Database connection closed.");
//        }));

        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "app.properties";
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(appConfigPath));

        ConexionBD.cerrarConexion();
        ScannerUtil.cerrarScanner();
    }

}