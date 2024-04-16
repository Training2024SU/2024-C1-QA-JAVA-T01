package com.sofkau.integration.database.ClasesDB;

import com.sofkau.integration.database.mysql.MySqlOperation;

import java.sql.SQLException;

public class Usuarios {
    private static final MySqlOperation mySqlOperation = new MySqlOperation();

    public static void consultarTodosUsuarios() throws SQLException {
        mySqlOperation.setSqlStatement("SELECT * FROM Usuario");
        mySqlOperation.executeSqlStatement();
        mySqlOperation.printResulset();
    }

    public static void agregarUsuario() {


        String correo = "jorge@gmail.com";
        String nombre = "jorge";
        String clave = "23456";
        String rol = "lector";


        mySqlOperation.setSqlStatement("INSERT INTO Usuario (correo, nombre, clave, rol) VALUES (?, ?, ?, ?)");
        try {
            mySqlOperation.executeSqlStatementWithParameters(correo,nombre,clave,rol);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("usuario Registrado Exitosamente");
    }

    public static void agregarUsuarioLector() {


        String correo = "julio@gmail.com";
        String nombre = "julio";
        String clave = "23456";
        String rol = "lector"; //no modificar en este caso no seas pendejo


        mySqlOperation.setSqlStatement("INSERT INTO Usuario (correo, nombre, clave, rol) VALUES (?, ?, ?, ?)");
        try {
            mySqlOperation.executeSqlStatementWithParameters(correo,nombre,clave,rol);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("usuario Registrado Exitosamente");
    }

    public static void actualizarUsuario(String correo) {


        String nombre = "julien";
        String clave = "98765";
        String rol = "lector";


        mySqlOperation.setSqlStatement("UPDATE Usuario SET nombre = ?, clave = ?, rol = ? WHERE correo = ?");
        try {
            mySqlOperation.executeSqlStatementWithParameters(nombre,clave,rol,correo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Usuario de correo " + correo + "actualizado correctament");
    }


    public static void eliminarUsuario(String correo) {

        mySqlOperation.setSqlStatement("DELETE FROM Usuario WHERE correo = ?");
        try {
            mySqlOperation.executeSqlStatementWithParameters(correo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Usuario Eliminado del correo "+ correo + " de la base de datos");
    }


    public static void consultarUsuario(String correo) throws SQLException {

        mySqlOperation.setSqlStatement("SELECT * FROM Usuario WHERE correo = ?");
        try {
            mySqlOperation.executeSqlStatementWithParametersConsult(correo);
            mySqlOperation.printResulset();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void iniciarSesion(String correo, String clave) {
        try {
            mySqlOperation.setSqlStatement("SELECT * FROM Usuario WHERE correo = ? AND clave = ?");
            mySqlOperation.executeSqlStatementWithParametersConsult(correo, clave);


            if (mySqlOperation.getResulset().next()) {
                System.out.println("Inicio de sesión exitoso para el usuario con correo " + correo);

            } else {
                System.out.println("El usuario con correo " + correo + " y clave proporcionada no existe o es incorrecta.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

