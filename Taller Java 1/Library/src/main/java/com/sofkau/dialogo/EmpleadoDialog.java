package com.sofkau.dialogo;

import java.sql.SQLException;
import java.util.Scanner;

public class EmpleadoDialog {
    private static final String INSERT_ASISTENTE = "insert into mydb.Empleado (idEmpleado, Nombre, Contraseña, Correo, Rol) values ('%s', '%s', '%s', '%s', 'asistente');";

    public static String crearAsistente() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el id del asistente:");
        String id_usuario = scanner.nextLine();

        System.out.println("Ingrese el nombre del asistente:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el correo del asistente:");
        String correo = scanner.nextLine();

        System.out.println("Ingrese la contraseña del asistente:");
        String contrasena = scanner.nextLine();
        String sentencia;

        try {

            sentencia = String.format(INSERT_ASISTENTE, id_usuario, nombre, correo, contrasena);
            System.out.println("Empleado creado exitosamente");
            return sentencia;

        } catch (Exception e) {
            System.out.println("Error al crear el asistente: " + e.getMessage());
            return "";  // Valor predeterminado en caso de error
        }

    }
}
