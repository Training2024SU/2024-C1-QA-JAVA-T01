package co.com.pinguinera.capa_datos;

import co.com.pinguinera.capa_datos.interfaces.GestorBD;
import co.com.pinguinera.modelado.Empleado;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO extends AbstractDAO<Empleado> {

    private static final String CONSULTA_EMPLEADOS = "SELECT * FROM Empleado";
    private static final String INSERTAR_EMPLEADO = "INSERT INTO Empleado (idEmpleado, Nombre, Contraseña, Correo, Rol, EsAdministrativo) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String ACTUALIZAR_EMPLEADO = "UPDATE Empleado SET Nombre = ?, Contraseña = ?, Correo = ?, Rol = ?, EsAdministrativo = ? WHERE idEmpleado = ?";
    private static final String ELIMINAR_EMPLEADO = "DELETE FROM Empleado WHERE idEmpleado = ?";

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

    // Implementación de los métodos CRUD

    // Método para insertar un nuevo empleado en la base de datos
    @Override
    public void insertar(Empleado empleado) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(INSERTAR_EMPLEADO)) {
            statement.setInt(1, empleado.getIdEmpleado());
            statement.setString(2, empleado.getNombre());
            statement.setString(3, empleado.getContrasena());
            statement.setString(4, empleado.getCorreo());
            statement.setString(5, empleado.getRol());
            statement.setBoolean(6, empleado.isEsAdministrativo());
            statement.executeUpdate();
        }
    }

    // Método para actualizar un empleado existente en la base de datos
    @Override
    public void actualizar(Empleado empleado) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(ACTUALIZAR_EMPLEADO)) {
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
    @Override
    public void eliminar(int idEmpleado) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(ELIMINAR_EMPLEADO)) {
            statement.setInt(1, idEmpleado);
            statement.executeUpdate();
        }
    }
}
