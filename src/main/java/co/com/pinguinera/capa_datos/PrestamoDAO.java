package co.com.pinguinera.capa_datos;

import co.com.pinguinera.capa_datos.interfaces.GestorBD;
import co.com.pinguinera.modelado.Prestamo;
import co.com.pinguinera.modelado.enums.EstadoPrestamo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PrestamoDAO extends AbstractDAO<Prestamo> {

    private static final String CONSULTA_PRESTAMOS = "SELECT * FROM Prestamo";

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
}
