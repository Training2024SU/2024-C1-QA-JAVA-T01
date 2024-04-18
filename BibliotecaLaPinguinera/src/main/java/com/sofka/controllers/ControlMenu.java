package com.sofka.controllers;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Logger;

import static com.sofka.controllers.ControlValidar.validarEmpleado;
import static com.sofka.controllers.ControlValidar.validarUsuario;

public class ControlMenu {

    private static final Logger logger = Logger.getLogger(ControlMenu.class.getName());

    public static void iniciarSesion(String rol) throws SQLException {
        String usuario = JOptionPane.showInputDialog(null, "Ingrese su nombre: ");
        String contrasena = JOptionPane.showInputDialog(null, "Ingrese su contraseña: ");
        if (rol.equals("1")) validarEmpleado(usuario, contrasena, "ADMINISTRADOR");
        if (rol.equals("2")) validarEmpleado(usuario, contrasena, "ASISTENTE");
        if (Objects.equals(rol, "3")) validarUsuario(usuario, contrasena);
    }

    public static String preguntarSalir() throws NullPointerException{
        return pedirEntrada( "¿Seguro que desea salir? (S/N):");
    }

    public static int preguntarCantidadAlUsuario() {
        return Integer.parseInt(pedirEntrada( "Cual es la cantidad que desea generar: "));
    }


    public static void mostrarMensaje(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public static String pedirEntrada(String message){
//        try {
//            String entrada =
//        }catch (String){
//            logger.warning("Error: "+ e);
//        }
        return JOptionPane.showInputDialog(null,message);
    }

}
