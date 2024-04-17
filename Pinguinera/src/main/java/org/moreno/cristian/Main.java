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

        ConexionBD.cerrarConexion();
        ScannerUtil.cerrarScanner();
    }

}