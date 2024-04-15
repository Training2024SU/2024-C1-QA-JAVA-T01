package com.sofkau.integration.repositorio;

import com.sofkau.integration.database.ConexionDatabase;
import com.sofkau.integration.database.mysql.MySqlOperation;
import com.sofkau.model.Empleado;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class EmpleadoRepositorio {

    private static MySqlOperation mySqlOperation = ConexionDatabase.getMySqlOperation();

    public static void crearEmpleado(Empleado empleado) throws SQLException {
        String query = String.format("INSERT INTO Empleado (idEmpleado, nombre, contrasena, correo, rol) VALUES ('%s', '%s', '%s', '%s', '%s')",
                empleado.getId(), empleado.getNombre(), empleado.getContrasena(), empleado.getCorreo(), empleado.getRol());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
    }

    public static HashMap<String, Empleado> consultarEmpleados() throws SQLException {
        String query = "SELECT * FROM Empleado";
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatement();
        ResultSet resultSet = mySqlOperation.getResulset();

        HashMap<String, Empleado> empleados = new HashMap<>();
        while (resultSet.next()) {
            String id = resultSet.getString("idEmpleado");
            String nombre = resultSet.getString("nombre");
            String correo = resultSet.getString("correo");
            String contrasena = resultSet.getString("contrasena");
            String rol = resultSet.getString("rol");

            Empleado empleado = new Empleado();
            empleado.setId(id);
            empleado.setNombre(nombre);
            empleado.setCorreo(correo);
            empleado.setContrasena(contrasena);
            empleado.setRol(rol);

            empleados.put(id, empleado);
        }

        return empleados;
    }

    public static Empleado consultarEmpleadoPorId(String id) throws SQLException {
        String query = String.format("Select * from Empleado where idEmpleado = '%s'",id);
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatement();

        ResultSet resultSet = mySqlOperation.getResulset();

        if (resultSet.next()) {
            String nombre = resultSet.getString("nombre");
            String correo = resultSet.getString("correo");
            String contrasena = resultSet.getString("contrasena");
            String rol = resultSet.getString("rol");

            Empleado empleado = new Empleado();
            empleado.setId(id);
            empleado.setNombre(nombre);
            empleado.setCorreo(correo);
            empleado.setContrasena(contrasena);
            empleado.setRol(rol);

            return empleado;
        } else {
            return null;
        }
    }

}
