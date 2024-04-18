package org.moreno.cristian.servicios;

import org.moreno.cristian.modelos.Prestamo;
import org.moreno.cristian.modelos.Publicacion;
import org.moreno.cristian.repositorios.RepositorioPrestamo;
import org.moreno.cristian.repositorios.RepositorioPublicacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ServicioPrestamo implements RepositorioPrestamo {

    private Connection conn;
    private RepositorioPublicacion servicioPublicacion;

    public ServicioPrestamo(ServicioPublicacion servicioPublicacion) throws SQLException {
        this.conn = ConexionBD.obtenerConexion();
        this.servicioPublicacion = servicioPublicacion;
    }

    @Override
    public boolean guardarPrestamo(Prestamo nuevoPrestamo) {

        String sqlPrestamo = "INSERT INTO prestamo (id, fechaRealizado, fechaExpiracion, usuario_id, estado) " +
                "VALUES (?, ?, ?, ?, ?)";

        String sqlPublicacionPorPrestamo = "INSERT INTO `Publicacion x Prestamo` (prestamo_id, publicacion_id) " +
                "VALUES (?, ?)";

        boolean crearPrestamo =
                ejecutarActualizacion(sqlPrestamo, nuevoPrestamo.getId(), nuevoPrestamo.getFechaRealizado(), nuevoPrestamo.getFechaExpiracion(), nuevoPrestamo.getUsuario().getId(), nuevoPrestamo.getEstado());

        boolean crearPublicacionPorPrestamo = true;

        List<? extends Publicacion> publicacionesEnPrestamo = nuevoPrestamo.getPublicaciones();

        for (Publicacion publicacion : publicacionesEnPrestamo) {

            crearPublicacionPorPrestamo = crearPublicacionPorPrestamo &&
                    ejecutarActualizacion(sqlPublicacionPorPrestamo, nuevoPrestamo.getId(), publicacion.getId());

        }

        if (crearPrestamo && crearPublicacionPorPrestamo) {
            return true;
        }

        return false; // Algun paso en el proceso falló

        // TODO: Manejar todo como una única transacción

    }

    @Override
    public boolean actualizarPrestamo(Prestamo prestamo) {
        return false;
    }

    @Override
    public Optional<List<Prestamo>> listarPrestamo() {
        return Optional.empty();
    }

    private boolean ejecutarActualizacion(String sql, Object... params) {
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            setearParametros(pstmt, params);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void setearParametros(PreparedStatement pstmt, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            pstmt.setObject(i + 1, params[i]);
        }
    }


}
