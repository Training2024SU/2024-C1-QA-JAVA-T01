package co.com.ejercicio.menu.loguin;

import co.com.ejercicio.conexionBd.Conexion;
import co.com.ejercicio.util.enums.Roles;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import static co.com.ejercicio.menu.dialogos.MenuGestionUsuario.menuAccionesUsuario;
import static co.com.ejercicio.menu.dialogos.MenuPrincipal.mostrarMenu;
import static co.com.ejercicio.menu.loguin.InicioSesion.iniciarSesionAsistente;
import static co.com.ejercicio.menu.loguin.InicioSesion.iniciarSesionUsuario;
import static co.com.ejercicio.menu.menuPrincipal.MenuGestionAsistente.menuAsistente;
import static co.com.ejercicio.principal.Main.logger;

public class IngresoAsistema {

    public static void inicioSesionUsuario(){
        Connection conexion; {
            try {
                conexion = Conexion.obtenerConexion();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese su correo: ");
            String correo = scanner.nextLine();
            System.out.println("Ingrese su contraseña: ");
            String contraseniaUsuario = scanner.nextLine();

            try {
                if (iniciarSesionUsuario(conexion, correo, contraseniaUsuario)) {
                    logger.info("Inicio de sesión Usuario exitoso");
                    menuAccionesUsuario();
                } else {
                    System.out.println("Correo o contraseña incorrectos.");
                    mostrarMenu();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

    }




    public static void inicioSesionAsistente(){
        Connection conexion; {
            try {
                conexion = Conexion.obtenerConexion();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese su correo: ");
            String correo = scanner.nextLine();
            System.out.println("Ingrese su contraseña: ");
            String contraseniaAsistente = scanner.nextLine();


            try {
                if (iniciarSesionAsistente(conexion, correo, contraseniaAsistente)) {
                    logger.info("Inicio de sesión exitoso para el " + Roles.TIPO_DOS.getvalue());
                    menuAsistente();
                } else {
                    System.out.println("Correo o contraseña incorrectos.");
                    mostrarMenu();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

}
