package co.com.pinguinera.capa_datos;

import co.com.pinguinera.capa_datos.conexionBD.ConexionBD;
import co.com.pinguinera.modelado.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {
    private static final String CONSULTA_EMPLEADOS = "SELECT * FROM Empleado";

    public List<Empleado> obtenerTodosLosEmpleados() throws SQLException {
        List<Empleado> empleados = new ArrayList<>();

        Connection conexion = ConexionBD.conectar();

        try (PreparedStatement statement = conexion.prepareStatement(CONSULTA_EMPLEADOS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Empleado empleado = new Empleado();
                empleado.setIdEmpleado(resultSet.getInt("idEmpleado"));
                empleado.setNombre(resultSet.getString("Nombre"));
                empleado.setContrasena(resultSet.getString("Contraseña"));
                empleado.setCorreo(resultSet.getString("Correo"));
                empleado.setRol(resultSet.getString("Rol"));
                empleado.setEsAdministrativo(resultSet.getBoolean("EsAdministrativo"));

                empleados.add(empleado);
            }
        }

        return empleados;
    }
}
