package org.moreno.cristian;

import org.moreno.cristian.modelos.Usuario;
import org.moreno.cristian.servicios.ConexionBD;
import org.moreno.cristian.servicios.excel.EscribirExcel;
import org.moreno.cristian.servicios.ScannerUtil;
import org.moreno.cristian.servicios.excel.LeerExcel;
import org.moreno.cristian.ui.MenuPrincipal;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {

//        EscribirExcel.migrarUsuarios();

        LeerExcel.leer();

        MenuPrincipal.menuInicial();

        ConexionBD.cerrarConexion();
        ScannerUtil.cerrarScanner();
    }

}