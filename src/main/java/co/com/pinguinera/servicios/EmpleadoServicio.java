package co.com.pinguinera.servicios;

import co.com.pinguinera.DAO.EmpleadoDAO;
import co.com.pinguinera.modelos.Empleado;

import java.sql.SQLException;
import java.util.List;

public class EmpleadoServicio {
    private EmpleadoDAO empleadoDAO;

    public EmpleadoServicio() {
        this.empleadoDAO = new EmpleadoDAO();
    }

    // Obtener todos los empleados
    public List<Empleado> obtenerTodosLosEmpleados() throws SQLException {
        return empleadoDAO.obtenerTodosLosEmpleados();
    }

    // Filtrar empleados por nombre
    /*
    public List<Empleado> filtrarEmpleadosPorNombre(String nombre) throws SQLException {
        // Implementar este método
        return null;
    }
    */

    // Filtrar empleados por rol
    /*
    public List<Empleado> filtrarEmpleadosPorRol(String rol) throws SQLException {
        // Implementar este método
        return null;
    }
    */

    // Otros métodos de lógica de negocio pueden ir aquí
    // Por ejemplo, podrías agregar métodos como eliminarEmpleado, actualizarEmpleado, etc.
}
