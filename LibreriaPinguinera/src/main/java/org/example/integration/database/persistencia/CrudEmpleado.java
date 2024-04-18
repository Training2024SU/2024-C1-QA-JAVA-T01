package org.example.integration.database.persistencia;

import org.example.integration.database.mysql.MySqlOperation;
import org.example.model.Empleado;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.integration.database.mysql.MySqlConstants.*;
import static org.example.logica.control.ConstantesLogica.TIPO_EMPLEADO;

public class CrudEmpleado {
    MySqlOperation mySqlOperation;

    public CrudEmpleado() {
        mySqlOperation=new MySqlOperation();
    }

    public void seleccionarDatos() throws SQLException {
        mySqlOperation.setSqlStatement(String.format(SELECT, TIPO_EMPLEADO));
        mySqlOperation.executeSqlStatement();
        ResultSet resultSet=mySqlOperation.getResulset();
        llenarEmpleados(resultSet);
    }

    public void crearEntidad(Empleado empleado) {
        mySqlOperation.executeSqlStatementVoid(String.format(CREATE_EMPLEADO,empleado.getNombre(), empleado.getCorreo(), empleado.getContrasenna(), empleado.getRol()));
    }
    public void eliminarEmpleado(Empleado empleado){
        mySqlOperation.executeSqlStatementVoid(String.format(DELETE_EMPLEADO, empleado.getCorreo()));
    }
    private void llenarEmpleados(ResultSet resultSet) throws SQLException {
        while (resultSet.next()){
            String nombre=resultSet.getString("nombre");
            String contrasena=resultSet.getString("contrasena");
            String correo=resultSet.getString("correo");
            String rol=resultSet.getString("rol");

            Empleado empleado=new Empleado( nombre, correo, contrasena, rol);
            Empleado.empleados.add(empleado);
        }
    }

}
