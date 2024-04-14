package co.com.pinguinera.capa_datos;

import co.com.pinguinera.capa_datos.interfaces.GestorBD;
import co.com.pinguinera.modelado.Prestamo;
import co.com.pinguinera.modelado.enums.EstadoPrestamo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO extends AbstractDAO<Prestamo> {

    private static final String CONSULTA_PRESTAMOS = "SELECT * FROM Prestamo";
    private static final String INSERTAR_PRESTAMO = "INSERT INTO Prestamo (Fecha_prestamo, Fecha_devolucion, Estado, correo_usuario, titulo_publicacion) VALUES (?, ?, ?, ?, ?)";
    private static final String ACTUALIZAR_PRESTAMO = "UPDATE Prestamo SET Fecha_prestamo = ?, Fecha_devolucion = ?, Estado = ?, correo_usuario = ?, titulo_publicacion = ? WHERE idPrestamo = ?";
    private static final String ELIMINAR_PRESTAMO = "DELETE FROM Prestamo WHERE idPrestamo = ?";

    // Constructor que recibe un objeto GestorBD para establecer la conexión
    public PrestamoDAO(GestorBD gestorBD) {
        super(gestorBD);
    }

    @Override
    protected String obtenerConsultaTodos() {
        // Devuelve la consulta SQL específica para obtener todos los registros de la tabla Prestamo
        return CONSULTA_PRESTAMOS;
    }

    @Override
    protected Prestamo convertirFilaAObjeto(ResultSet resultSet) throws SQLException {
        // Crea un objeto Prestamo a partir de una fila del ResultSet
        Prestamo prestamo = new Prestamo();
        prestamo.setIdPrestamo(resultSet.getInt("idPrestamo"));
        prestamo.setFechaPrestamo(resultSet.getDate("Fecha_prestamo").toLocalDate());
        prestamo.setFechaDevolucion(resultSet.getDate("Fecha_devolucion") != null ? resultSet.getDate("Fecha_devolucion").toLocalDate() : null);
        prestamo.setEstado(EstadoPrestamo.valueOf(resultSet.getString("Estado")));
        prestamo.setCorreoUsuario(resultSet.getString("correo_usuario"));
        prestamo.setTituloPublicacion(resultSet.getString("titulo_publicacion"));
        return prestamo;
    }

    // Implementación de los métodos CRUD

    // Método para insertar un nuevo préstamo en la base de datos
    @Override
    public void insertar(Prestamo prestamo) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(INSERTAR_PRESTAMO)) {
            statement.setDate(1, java.sql.Date.valueOf(prestamo.getFechaPrestamo()));
            statement.setDate(2, prestamo.getFechaDevolucion() != null ? java.sql.Date.valueOf(prestamo.getFechaDevolucion()) : null);
            statement.setString(3, prestamo.getEstado().toString());
            statement.setString(4, prestamo.getCorreoUsuario());
            statement.setString(5, prestamo.getTituloPublicacion());
            statement.executeUpdate();
        }
    }

    // Método para actualizar un préstamo existente en la base de datos
    @Override
    public void actualizar(Prestamo prestamo) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(ACTUALIZAR_PRESTAMO)) {
            statement.setDate(1, java.sql.Date.valueOf(prestamo.getFechaPrestamo()));
            statement.setDate(2, prestamo.getFechaDevolucion() != null ? java.sql.Date.valueOf(prestamo.getFechaDevolucion()) : null);
            statement.setString(3, prestamo.getEstado().toString());
            statement.setString(4, prestamo.getCorreoUsuario());
            statement.setString(5, prestamo.getTituloPublicacion());
            statement.setInt(6, prestamo.getIdPrestamo());
            statement.executeUpdate();
        }
    }

    // Método para eliminar un préstamo de la base de datos
    @Override
    public void eliminar(int idPrestamo) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(ELIMINAR_PRESTAMO)) {
            statement.setInt(1, idPrestamo);
            statement.executeUpdate();
        }
    }
}
