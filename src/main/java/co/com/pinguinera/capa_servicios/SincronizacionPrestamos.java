package co.com.pinguinera.capa_servicios;

import co.com.pinguinera.capa_datos.PrestamoDAO;
import co.com.pinguinera.capa_servicios.interfaces.GestorBD;
import co.com.pinguinera.modelado.Prestamo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SincronizacionPrestamos {

    private GestorBD gestorBD;
    private PrestamoDAO prestamoDAO;
    private ServicioCRUDPrestamos servicioCRUDPrestamos; // Servicio local de préstamos

    // Consultas SQL para manipulación de préstamos
    private static final String INSERTAR_PRESTAMO = "INSERT INTO Prestamo (Fecha_prestamo, Fecha_devolucion, Estado, correo_usuario, titulo_publicacion) VALUES (?, ?, ?, ?, ?)";
    private static final String ACTUALIZAR_PRESTAMO = "UPDATE Prestamo SET Fecha_prestamo = ?, Fecha_devolucion = ?, Estado = ?, correo_usuario = ?, titulo_publicacion = ? WHERE idPrestamo = ?";
    private static final String ELIMINAR_PRESTAMO = "DELETE FROM Prestamo WHERE idPrestamo = ?";

    public SincronizacionPrestamos(GestorBD gestorBD, ServicioCRUDPrestamos servicioCRUDPrestamos) {
        this.gestorBD = gestorBD;
        this.prestamoDAO = new PrestamoDAO();
        this.servicioCRUDPrestamos = servicioCRUDPrestamos;
    }

    // Sincroniza la colección local de préstamos con la base de datos
    public void sincronizarConBaseDatos() throws SQLException {
        List<Prestamo> prestamosLocales = servicioCRUDPrestamos.obtenerTodos();
        List<Prestamo> prestamosBD = prestamoDAO.obtenerTodosLosPrestamos();

        // Mapeo de IDs de préstamos locales para acceso eficiente
        Map<Integer, Prestamo> mapaPrestamosLocales = new HashMap<>();
        for (Prestamo prestamoLocal : prestamosLocales) {
            mapaPrestamosLocales.put(prestamoLocal.getIdPrestamo(), prestamoLocal);
        }

        // Sincroniza préstamos existentes entre base de datos y colección local
        for (Prestamo prestamoBD : prestamosBD) {
            int idPrestamo = prestamoBD.getIdPrestamo();

            if (mapaPrestamosLocales.containsKey(idPrestamo)) {
                // Actualiza préstamo existente en la base de datos
                actualizarPrestamo(mapaPrestamosLocales.get(idPrestamo));
                mapaPrestamosLocales.remove(idPrestamo); // Remueve del mapa
            } else {
                // Elimina préstamos de la base de datos no presentes localmente
                eliminarPrestamo(idPrestamo);
            }
        }

        // Inserta préstamos restantes locales en la base de datos
        for (Prestamo prestamoLocal : mapaPrestamosLocales.values()) {
            agregarPrestamo(prestamoLocal);
        }
    }

    private void agregarPrestamo(Prestamo prestamo) throws SQLException {
        try (PreparedStatement statement = gestorBD.prepararConsulta(INSERTAR_PRESTAMO)) {
            // Configura los parámetros de la consulta
            statement.setDate(1, Date.valueOf(prestamo.getFechaPrestamo()));
            statement.setDate(2, prestamo.getFechaDevolucion() != null ? Date.valueOf(prestamo.getFechaDevolucion()) : null);
            statement.setString(3, prestamo.getEstado().name());
            statement.setString(4, prestamo.getCorreoUsuario());
            statement.setString(5, prestamo.getTituloPublicacion());
            statement.executeUpdate();
        }
    }

    private void actualizarPrestamo(Prestamo prestamo) throws SQLException {
        try (PreparedStatement statement = gestorBD.prepararConsulta(ACTUALIZAR_PRESTAMO)) {
            // Configura los parámetros de la consulta
            statement.setDate(1, Date.valueOf(prestamo.getFechaPrestamo()));
            statement.setDate(2, prestamo.getFechaDevolucion() != null ? Date.valueOf(prestamo.getFechaDevolucion()) : null);
            statement.setString(3, prestamo.getEstado().name());
            statement.setString(4, prestamo.getCorreoUsuario());
            statement.setString(5, prestamo.getTituloPublicacion());
            statement.setInt(6, prestamo.getIdPrestamo());
            statement.executeUpdate();
        }
    }

    private void eliminarPrestamo(int idPrestamo) throws SQLException {
        try (PreparedStatement statement = gestorBD.prepararConsulta(ELIMINAR_PRESTAMO)) {
            // Configura el parámetro de la consulta
            statement.setInt(1, idPrestamo);
            statement.executeUpdate();
        }
    }
}

