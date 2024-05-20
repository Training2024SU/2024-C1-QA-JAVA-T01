package co.com.biblioteca;

import co.com.biblioteca.modelo.Usuario;
import co.com.biblioteca.mysql.MySQLDB;

import static co.com.biblioteca.dialogo.ControlMenu.preguntarSalir;
import static co.com.biblioteca.ui.MenuUsuario.iniciarSesion;

public class Main {
    public static void main(String[] args) {
        MySQLDB.iniciarConexion();
        String salir;
        Usuario usuario;
        do {
            usuario = iniciarSesion();
            salir = preguntarSalir();
        } while (!(salir.equals("s") || salir.equals("S")));
        MySQLDB.cerrar();
    }
}

