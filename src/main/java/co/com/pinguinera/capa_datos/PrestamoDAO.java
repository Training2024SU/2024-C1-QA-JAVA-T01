package co.com.pinguinera.capa_datos;

import co.com.pinguinera.capa_datos.interfaces.GestorBD;
import co.com.pinguinera.modelado.Prestamo;
import co.com.pinguinera.modelado.enums.EstadoPrestamo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO extends AbstractDAO<Prestamo> {

    private static final String CONSULTA_PRESTAMOS = "SELECT * FROM Prestamo";
    private static final String INSERTAR_PRESTAMO = "INSERT INTO Prestamo (Fecha_prestamo, Fecha_devolucion, Estado, idUsuario, idPublicacion) VALUES (?, ?, ?, ?, ?)";
    private static final String ACTUALIZAR_PRESTAMO = "UPDATE Prestamo SET Fecha_prestamo = ?, Fecha_devolucion = ?, Estado = ?, idUsuario = ?, idPublicacion = ? WHERE idPrestamo = ?";
    private static final String ELIMINAR_PRESTAMO = "DELETE FROM Prestamo WHERE idPrestamo = ?";

    public PrestamoDAO(GestorBD gestorBD) {
        super(gestorBD);
    }

    @Override
    protected String obtenerConsultaTodos() {
        return CONSULTA_PRESTAMOS;
    }

    @Override
    protected Prestamo convertirFilaAObjeto(ResultSet resultSet) throws SQLException {
        int idPrestamo = resultSet.getInt("idPrestamo");
        LocalDate fechaPrestamo = resultSet.getDate("Fecha_prestamo").toLocalDate();
        LocalDate fechaDevolucion = resultSet.getDate("Fecha_devolucion") != null ? resultSet.getDate("Fecha_devolucion").toLocalDate() : null;
        EstadoPrestamo estado = EstadoPrestamo.valueOf(resultSet.getString("Estado"));
        int idUsuario = resultSet.getInt("idUsuario");
        int idPublicacion = resultSet.getInt("idPublicacion");

        return new Prestamo(idPrestamo, fechaPrestamo, fechaDevolucion, estado, idUsuario, idPublicacion);
    }

    @Override
    public void insertar(Prestamo prestamo) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(INSERTAR_PRESTAMO)) {
            statement.setDate(1, Date.valueOf(prestamo.getFechaPrestamo()));
            statement.setDate(2, prestamo.getFechaDevolucion() != null ? Date.valueOf(prestamo.getFechaDevolucion()) : null);
            statement.setString(3, prestamo.getEstado().toString());
            statement.setInt(4, prestamo.getIdUsuario());
            statement.setInt(5, prestamo.getIdPublicacion());
            statement.executeUpdate();
        }
    }

    @Override
    public void actualizar(Prestamo prestamo) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(ACTUALIZAR_PRESTAMO)) {
            statement.setDate(1, Date.valueOf(prestamo.getFechaPrestamo()));
            statement.setDate(2, prestamo.getFechaDevolucion() != null ? Date.valueOf(prestamo.getFechaDevolucion()) : null);
            statement.setString(3, prestamo.getEstado().toString());
            statement.setInt(4, prestamo.getIdUsuario());
            statement.setInt(5, prestamo.getIdPublicacion());
            statement.setInt(6, prestamo.getIdPrestamo());
            statement.executeUpdate();
        }
    }

    @Override
    public void eliminar(int idPrestamo) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(ELIMINAR_PRESTAMO)) {
            statement.setInt(1, idPrestamo);
            statement.executeUpdate();
        }
    }

    @Override
    public int obtenerId(Prestamo prestamo) throws SQLException {
        // Devuelve el ID del objeto Prestamo
        return prestamo.getIdPrestamo();
    }
}
