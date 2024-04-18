package com.sofka.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.sofka.BibliotecaLaPinguinera.mySqlOperation;
import static com.sofka.constants.SelectConstants.SELECT_ALL_FROM_EMPLEADO;
import static com.sofka.constants.SelectConstants.SELECT_ALL_FROM_USUARIO;
import static com.sofka.controllers.ControlMenu.mostrarMensaje;
import static com.sofka.menu.menuDeAdministrador.menuAdministrador;
import static com.sofka.menu.menuDeAsistente.menuAsistente;
import static com.sofka.menu.menuDeUsuario.menuUsuario;

public class ControlValidar {

    public static void validarEmpleado(String usuario, String contrasena, String rol) throws SQLException {
        // Aquí se procede a validar las credenciales con una base de datos
        mySqlOperation.setSqlStatement(SELECT_ALL_FROM_EMPLEADO);
        mySqlOperation.executeSqlStatement();
        ResultSet empleados = mySqlOperation.getResulset();
        boolean encontrado = false;
        encontrado = isEncontrado(usuario, contrasena, rol, empleados, encontrado);
        if (!encontrado) {
            mostrarMensaje("Usuario o contraseña incorrectos.");
        }
    }

    private static boolean isEncontrado(String usuario, String contrasena, String rol, ResultSet empleados, boolean encontrado) throws SQLException {
        while (empleados.next()) {
            String nombre = empleados.getString("nombre");
            String contrasenha = empleados.getString("contrasenha");
            String roles = empleados.getString("rol");
            if (nombre.equals(usuario) && contrasenha.equals(contrasena) && roles.equals(rol)) {
                encontrado = true;
                mostrarMensaje("¡Inicio de sesión exitoso para el empleado: " + usuario + "!");
                empleadoConRol(roles);
            }
        }
        return encontrado;
    }

    private static void empleadoConRol(String roles) throws SQLException {
        if (roles.equals("ADMINISTRADOR")) menuAdministrador();
        if (roles.equals("ASISTENTE")) menuAsistente();
    }

    public static void validarUsuario(String usuario, String contrasena) throws SQLException {
        // Aquí se procede a validar las credenciales con una base de datos
        mySqlOperation.setSqlStatement(SELECT_ALL_FROM_USUARIO);
        mySqlOperation.executeSqlStatement();
        ResultSet usuarios = mySqlOperation.getResulset();
        boolean encontrado = false;
        while (usuarios.next()) {
            String nombre = usuarios.getString("nombre");
            String contrasenha = usuarios.getString("contrasenha");
            if (nombre.equals(usuario) && contrasenha.equals(contrasena)) {
                encontrado = true;
                mostrarMensaje("¡Inicio de sesión exitoso para el usuario: " + usuario + "!");
                menuUsuario();
            }
        }
        if (!encontrado) {
            mostrarMensaje("Usuario o contraseña incorrectos.");
        }
    }

}
