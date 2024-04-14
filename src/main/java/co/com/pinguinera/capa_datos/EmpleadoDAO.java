package co.com.pinguinera.capa_datos;

import co.com.pinguinera.capa_datos.interfaces.GestorBD;
import co.com.pinguinera.modelado.Empleado;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO extends AbstractDAO<Empleado> {

    private static final String CONSULTA_EMPLEADOS = "SELECT * FROM Empleado";

    // Constructor que recibe un objeto GestorBD para establecer la conexión
    public EmpleadoDAO(GestorBD gestorBD) {
        super(gestorBD);
    }

    @Override
    protected String obtenerConsultaTodos() {
        // Devuelve la consulta SQL específica para obtener todos los empleados
        return CONSULTA_EMPLEADOS;
    }

    @Override
    protected Empleado convertirFilaAObjeto(ResultSet resultSet) throws SQLException {
        // Crea un objeto Empleado a partir de una fila del ResultSet
        Empleado empleado = new Empleado();
        empleado.setIdEmpleado(resultSet.getInt("idEmpleado"));
        empleado.setNombre(resultSet.getString("Nombre"));
        empleado.setContrasena(resultSet.getString("Contraseña"));
        empleado.setCorreo(resultSet.getString("Correo"));
        empleado.setRol(resultSet.getString("Rol"));
        empleado.setEsAdministrativo(resultSet.getBoolean("EsAdministrativo"));

        return empleado;
    }
}
