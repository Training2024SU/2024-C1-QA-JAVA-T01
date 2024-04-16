package com.sofkau.dialogo;

import java.sql.SQLException;
import java.util.Scanner;

public class UsuarioDialog {
    private static final String INSERT_USUARIO = "insert into mydb.usuario ( Correo, Nombre, Contraseña, ) values ('%s', '%s', '%s');";

    public static String crearUsuario() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el id del usuario:");
        String correo = scanner.nextLine();
        System.out.println("Ingrese el nombre del usuario:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la contraseña del usuario:");
        String contrasena = scanner.nextLine();
        String sentencia;

        try {

            sentencia = String.format(INSERT_USUARIO, correo, nombre, contrasena);
            System.out.println("Empleado creado exitosamente");
            return sentencia;

        } catch (Exception e) {
            System.out.println("Error al crear el asistente: " + e.getMessage());
            return "";  // Valor predeterminado en caso de error
        }

    }
}
