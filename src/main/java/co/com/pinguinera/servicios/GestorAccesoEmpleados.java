package co.com.pinguinera.servicios;

import co.com.pinguinera.datos.DAO.EmpleadoDAO;
import co.com.pinguinera.datos.model.Empleado;

import java.sql.SQLException;
import java.util.List;

public class GestorAccesoEmpleados {
    private EmpleadoDAO empleadoDAO;
    private List<Empleado> listaEmpleados; // Lista de empleados

    public GestorAccesoEmpleados(EmpleadoDAO empleadoDAO) {
        this.empleadoDAO = empleadoDAO;
        this.listaEmpleados = List.of(); // Inicializar la lista vacía
    }

    /**
     * Carga la lista de todos los empleados desde la base de datos.
     */
    public void cargarEmpleados() {
        try {
            listaEmpleados = empleadoDAO.obtenerTodos(); // Llamar a `obtenerTodos` en `EmpleadoDAO`
        } catch (SQLException e) {
            System.err.println("Error al cargar empleados: " + e.getMessage());
        }
    }

    /**
     * Verifica si un empleado especificado (correo y contraseña) se encuentra en la lista de empleados y devuelve su rol.
     *
     * @param correo      Correo del empleado a verificar.
     * @param contrasena  Contraseña del empleado a verificar.
     * @return El rol del empleado si está en la lista, null en caso contrario.
     */
    public String verificarEmpleado(String correo, String contrasena) {
        cargarEmpleados(); // Cargar la lista de empleados desde la base de datos


        for (Empleado empleado : listaEmpleados) {
            if (empleado.getCorreo().equals(correo) && empleado.getContrasena().equals(contrasena)) {
                return empleado.getRol();
            }
        }
        return null; // Empleado no encontrado
    }
}
