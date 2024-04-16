package co.com.pinguinera.datos.DAO;

import co.com.pinguinera.datos.interfaces.GestorBD;
import co.com.pinguinera.datos.model.Prestamo;
import co.com.pinguinera.datos.model.enums.EstadoPrestamo;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO extends AbstractDAO<Prestamo> {
    private static final String CONSULTA_PRESTAMOS = "SELECT * FROM Prestamo";
    private static final String INSERTAR_PRESTAMO = "INSERT INTO Prestamo (Fecha_prestamo, Fecha_devolucion, Estado, idUsuario, idPublicacion) VALUES (?, ?, ?, ?, ?)";
    private static final String ACTUALIZAR_PRESTAMO = "UPDATE Prestamo SET Fecha_prestamo = ?, Fecha_devolucion = ?, Estado = ?, idUsuario = ?, idPublicacion = ? WHERE idPrestamo = ?";
    private static final String ELIMINAR_PRESTAMO = "DELETE FROM Prestamo WHERE idPrestamo = ?";
    private static final String CONSULTAR_PRESTAMOS_POR_CORREO = "SELECT p.* FROM Prestamo p JOIN Usuario u ON p.idUsuario = u.idUsuario WHERE u.Correo = ?";
    private static final String CAMBIAR_ESTADO_PRESTAMO = "UPDATE Prestamo SET Estado = ? WHERE idPrestamo = ?";
    private static final String ACTUALIZAR_CANTIDAD_LIBROS_DISPONIBLES_REALIZADO = "UPDATE Publicacion SET cant_prestados = cant_prestados + 1 WHERE idPublicacion = ?";
    private static final String ACTUALIZAR_CANTIDAD_LIBROS_DISPONIBLES_FINALIZADO = "UPDATE Publicacion SET cant_prestados = cant_prestados - 1 WHERE idPublicacion = ?";
    private static final String CONSULTA_PRESTAMO_POR_ID = "SELECT * FROM Prestamo WHERE idPrestamo = ?";

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
        if (existePrestamoDuplicado(prestamo)) {
            return;
        }
        try (PreparedStatement statement = prepararConsulta(INSERTAR_PRESTAMO)) {
            statement.setDate(1, Date.valueOf(prestamo.getFechaPrestamo()));
            statement.setDate(2, prestamo.getFechaDevolucion() != null ? Date.valueOf(prestamo.getFechaDevolucion()) : null);
            statement.setString(3, prestamo.getEstado().toString());
            statement.setInt(4, prestamo.getIdUsuario());
            statement.setInt(5, prestamo.getIdPublicacion());
            statement.executeUpdate();
        }
    }

    private boolean existePrestamoDuplicado(Prestamo prestamo) throws SQLException {
        String consultaDuplicado = "SELECT COUNT(*) FROM Prestamo WHERE idUsuario = ? AND idPublicacion = ?";
        try (PreparedStatement statement = prepararConsulta(consultaDuplicado)) {
            statement.setInt(1, prestamo.getIdUsuario());
            statement.setInt(2, prestamo.getIdPublicacion());
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() && resultSet.getInt(1) > 0;
            }
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
    public void eliminar(Prestamo prestamo) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(ELIMINAR_PRESTAMO)) {
            statement.setInt(1, prestamo.getIdPrestamo());
            statement.executeUpdate();
        }
    }

    public List<Prestamo> consultarPrestamosPorCorreo(String correo) throws SQLException {
        List<Prestamo> prestamos = new ArrayList<>();
        try (PreparedStatement statement = prepararConsulta(CONSULTAR_PRESTAMOS_POR_CORREO)) {
            statement.setString(1, correo);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Prestamo prestamo = convertirFilaAObjeto(resultSet);
                    prestamos.add(prestamo);
                }
            }
        }
        return prestamos;
    }

    public void cambiarEstadoPrestamo(int idPrestamo, EstadoPrestamo nuevoEstado) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(CAMBIAR_ESTADO_PRESTAMO)) {
            statement.setString(1, nuevoEstado.toString());
            statement.setInt(2, idPrestamo);
            statement.executeUpdate();
        }
        actualizarCantidadLibrosDisponibles(idPrestamo, nuevoEstado);
    }

    public void actualizarCantidadLibrosDisponibles(int idPrestamo, EstadoPrestamo nuevoEstado) throws SQLException {
        String consulta = (nuevoEstado == EstadoPrestamo.REALIZADO) ? ACTUALIZAR_CANTIDAD_LIBROS_DISPONIBLES_REALIZADO : ACTUALIZAR_CANTIDAD_LIBROS_DISPONIBLES_FINALIZADO;
        try (PreparedStatement statement = prepararConsulta(consulta)) {
            int idPublicacion = obtenerIdPublicacionDePrestamo(idPrestamo);
            statement.setInt(1, idPublicacion);
            statement.executeUpdate();
        }
    }

    public Prestamo obtenerPorId(int idPrestamo) throws SQLException {
        Prestamo prestamo = null;
        try (PreparedStatement statement = prepararConsulta(CONSULTA_PRESTAMO_POR_ID)) {
            statement.setInt(1, idPrestamo);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    prestamo = convertirFilaAObjeto(resultSet);
                }
            }
        }
        return prestamo;
    }

    private int obtenerIdPublicacionDePrestamo(int idPrestamo) throws SQLException {
        String consulta = "SELECT idPublicacion FROM Prestamo WHERE idPrestamo = ?";
        try (PreparedStatement statement = prepararConsulta(consulta)) {
            statement.setInt(1, idPrestamo);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() ? resultSet.getInt("idPublicacion") : -1;
            }
        }
    }
}
