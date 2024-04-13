package co.com.pinguinera.DAO;

import co.com.pinguinera.DataBase;
import co.com.pinguinera.modelos.Prestamo;
import co.com.pinguinera.enums.EstadoPrestamo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {
    private static final String CONSULTA_PRESTAMOS = "SELECT * FROM Prestamo";

    public List<Prestamo> obtenerTodosLosPrestamos() throws SQLException {
        List<Prestamo> prestamos = new ArrayList<>();

        Connection conexion = DataBase.conectar();

        try (PreparedStatement statement = conexion.prepareStatement(CONSULTA_PRESTAMOS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Prestamo prestamo = new Prestamo();
                prestamo.setIdPrestamo(resultSet.getInt("idPrestamo"));
                prestamo.setFechaPrestamo(resultSet.getDate("Fecha_prestamo").toLocalDate());
                prestamo.setFechaDevolucion(resultSet.getDate("Fecha_devolucion") != null ? resultSet.getDate("Fecha_devolucion").toLocalDate() : null);
                prestamo.setEstado(EstadoPrestamo.valueOf(resultSet.getString("Estado")));
                prestamo.setCorreoUsuario(resultSet.getString("correo_usuario"));
                prestamo.setTituloPublicacion(resultSet.getString("titulo_publicacion"));

                prestamos.add(prestamo);
            }
        }

        return prestamos;
    }
}
