package co.com.pinguinera.capa_servicios;

import co.com.pinguinera.capa_datos.PrestamoDAO;
import co.com.pinguinera.capa_servicios.interfaces.GestorBD;
import co.com.pinguinera.modelado.Prestamo;
import co.com.pinguinera.modelado.enums.EstadoPrestamo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ServicioCRUDPrestamos {

    private GestorBD gestorBD;
    private PrestamoDAO prestamoDAO;

    // Constructor que recibe una instancia de GestorBD
    public ServicioCRUDPrestamos(GestorBD gestorBD) {
        this.gestorBD = gestorBD;
        this.prestamoDAO = new PrestamoDAO();  // Crear una instancia de PrestamoDAO
    }


    private static final String INSERTAR_PRESTAMO = "INSERT INTO Prestamo (Fecha_prestamo, Fecha_devolucion, Estado, correo_usuario, titulo_publicacion) VALUES (?, ?, ?, ?, ?)";
    private static final String ACTUALIZAR_PRESTAMO = "UPDATE Prestamo SET Fecha_prestamo = ?, Fecha_devolucion = ?, Estado = ?, correo_usuario = ?, titulo_publicacion = ? WHERE idPrestamo = ?";
    private static final String ELIMINAR_PRESTAMO = "DELETE FROM Prestamo WHERE idPrestamo = ?";

    // Método para obtener todos los préstamos desde la base de datos utilizando PrestamoDAO
    public List<Prestamo> obtenerTodos() throws SQLException {
        return prestamoDAO.obtenerTodosLosPrestamos();
    }

    // Método para agregar un nuevo préstamo a la base de datos
    public void agregar(Prestamo prestamo) throws SQLException {
        try (PreparedStatement statement = gestorBD.prepararConsulta(INSERTAR_PRESTAMO)) {
            statement.setDate(1, Date.valueOf(prestamo.getFechaPrestamo()));
            statement.setDate(2, prestamo.getFechaDevolucion() != null ? Date.valueOf(prestamo.getFechaDevolucion()) : null);
            statement.setString(3, prestamo.getEstado().name());
            statement.setString(4, prestamo.getCorreoUsuario());
            statement.setString(5, prestamo.getTituloPublicacion());
            statement.executeUpdate();
        }
    }


    // Método para actualizar un préstamo existente en la base de datos
    public void actualizar(Prestamo prestamo) throws SQLException {
        try (PreparedStatement statement = gestorBD.prepararConsulta(ACTUALIZAR_PRESTAMO)) {
            statement.setDate(1, Date.valueOf(prestamo.getFechaPrestamo()));
            statement.setDate(2, prestamo.getFechaDevolucion() != null ? Date.valueOf(prestamo.getFechaDevolucion()) : null);
            statement.setString(3, prestamo.getEstado().name());
            statement.setString(4, prestamo.getCorreoUsuario());
            statement.setString(5, prestamo.getTituloPublicacion());
            statement.setInt(6, prestamo.getIdPrestamo());
            statement.executeUpdate();
        }
    }

    // Método para eliminar un préstamo de la base de datos
    public void eliminar(int idPrestamo) throws SQLException {
        try (PreparedStatement statement = gestorBD.prepararConsulta(ELIMINAR_PRESTAMO)) {
            statement.setInt(1, idPrestamo);
            statement.executeUpdate();
        }
    }
}
