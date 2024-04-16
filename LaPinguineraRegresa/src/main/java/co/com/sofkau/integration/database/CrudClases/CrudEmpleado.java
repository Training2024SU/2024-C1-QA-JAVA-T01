package co.com.sofkau.integration.database.CrudClases;

import co.com.sofkau.integration.database.mysql.MySqlOperation;
import co.com.sofkau.model.Empleado;

import java.security.spec.RSAOtherPrimeInfo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CrudEmpleado {


    public static void consultarTodosEmpleados(MySqlOperation mySqlOperation) throws SQLException {
        mySqlOperation.setSqlStatement("SELECT * FROM Empleado");
        mySqlOperation.executeSqlStatement();

        ResultSet resultSet = mySqlOperation.getResulset();


        while (resultSet.next()) {
            int id = resultSet.getInt("idEmpleado");
            String nombre = resultSet.getString("nombre");
            String clave = resultSet.getString("clave");
            String correo = resultSet.getString("correo");
            String rol = resultSet.getString("rol");

            Empleado empleadoAgregar = new Empleado(id, nombre, correo, clave, rol);
            Empleado.empleados.add(empleadoAgregar);
        }

    }




    public static void agregarEmpleado(MySqlOperation mySqlOperation) {


        String correo = "jorge@gmail.com";
        String nombre = "jorge";
        String clave = "23456";
        String rol = "Asistente";


        mySqlOperation.setSqlStatement("INSERT INTO empleado (nombre, clave, correo, rol) VALUES (?, ?, ?, ?)");
        try {
            mySqlOperation.executeSqlStatementWithParameters(nombre,clave,correo,rol);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Empleado Registrado Exitosamente");
    }

    public static void actualizarEmpleado(String correo, MySqlOperation mySqlOperation) {


        String nombre = "amanda";
        String clave = "696969";
        String rol = "Asistente";


        mySqlOperation.setSqlStatement("UPDATE empleado SET nombre = ?, clave = ?, rol = ? WHERE correo = ?");
        try {
            mySqlOperation.executeSqlStatementWithParameters(nombre,clave,rol,correo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Usuario de correo " + correo + "actualizado correctamente");
    }

    public static void eliminarEmpleado(String correo, MySqlOperation mySqlOperation) {

        mySqlOperation.setSqlStatement("DELETE FROM empleado WHERE correo = ?");
        try {
            mySqlOperation.executeSqlStatementWithParameters(correo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Empleado Eliminado del correo "+ correo + " de la base de datos");
    }

}


