package co.com.ejercicio.principal;

import co.com.ejercicio.conexionBd.Conexion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.Connection;
import java.sql.SQLException;

import static co.com.ejercicio.menu.dialogos.MenuPrincipal.mostrarMenu;
import static co.com.ejercicio.menu.loguin.RegistroUsuario.registrarUsuario;


public class Main {
    public static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {

        Connection conexion = Conexion.obtenerConexion();

        mostrarMenu();

        conexion.close();
    }

}









