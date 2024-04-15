package org.moreno.cristian;

import org.moreno.cristian.servicios.ConexionBD;
import org.moreno.cristian.servicios.ScannerUtil;
import org.moreno.cristian.ui.MenuPrincipal;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        System.out.println("Hello world! ñññ");
        MenuPrincipal.menuInicial();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            ConexionBD.cerrarConexion();
            System.out.println("Database connection closed.");
        }));


//        ConexionBD.cerrarConexion();
        ScannerUtil.cerrarScanner();
    }

}