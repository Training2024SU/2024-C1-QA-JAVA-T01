package co.com.pinguinera.capa_servicios;

import co.com.pinguinera.capa_datos.EmpleadoDAO;
import co.com.pinguinera.capa_servicios.interfaces.GestorBD;
import co.com.pinguinera.modelado.Empleado;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ServicioCRUDEmpleado {
    private GestorBD gestorBD;
    private EmpleadoDAO empleadoDAO;

    // Constructor que recibe una instancia de GestorBD
    public ServicioCRUDEmpleado(GestorBD gestorBD) {
        this.gestorBD = gestorBD;
        this.empleadoDAO = new EmpleadoDAO(); // Crear una instancia de EmpleadoDAO
    }


    private static final String INSERTAR_EMPLEADO = "INSERT INTO Empleado (Nombre, Contraseña, Correo, Rol, EsAdministrativo) VALUES (?, ?, ?, ?, ?)";
    private static final String ACTUALIZAR_EMPLEADO = "UPDATE Empleado SET Nombre = ?, Contraseña = ?, Correo = ?, Rol = ?, EsAdministrativo = ? WHERE idEmpleado = ?";
    private static final String ELIMINAR_EMPLEADO = "DELETE FROM Empleado WHERE idEmpleado = ?";

    // Método para agregar un nuevo empleado a la base de datos
    public void agregar(Empleado empleado) throws SQLException {
        try (PreparedStatement statement = gestorBD.prepararConsulta(INSERTAR_EMPLEADO)) {
            statement.setString(1, empleado.getNombre());
            statement.setString(2, empleado.getContrasena());
            statement.setString(3, empleado.getCorreo());
            statement.setString(4, empleado.getRol());
            statement.setBoolean(5, empleado.isEsAdministrativo());
            statement.executeUpdate();
        }
    }

    // Método para actualizar un empleado existente en la base de datos
    public void actualizar(Empleado empleado) throws SQLException {
        try (PreparedStatement statement = gestorBD.prepararConsulta(ACTUALIZAR_EMPLEADO)) {
            statement.setString(1, empleado.getNombre());
            statement.setString(2, empleado.getContrasena());
            statement.setString(3, empleado.getCorreo());
            statement.setString(4, empleado.getRol());
            statement.setBoolean(5, empleado.isEsAdministrativo());
            statement.setInt(6, empleado.getIdEmpleado());
            statement.executeUpdate();
        }
    }

    // Método para eliminar un empleado de la base de datos
    public void eliminar(int idEmpleado) throws SQLException {
        try (PreparedStatement statement = gestorBD.prepararConsulta(ELIMINAR_EMPLEADO)) {
            statement.setInt(1, idEmpleado);
            statement.executeUpdate();
        }
    }

    // Para obtener todos los empleados, puedes usar el método del DAO directamente
    public List<Empleado> obtenerTodos() throws SQLException {
        return empleadoDAO.obtenerTodosLosEmpleados();
    }
}
