package co.com.biblioteca.DAO;

import co.com.biblioteca.modelo.Grabacion;
import co.com.biblioteca.modelo.Prestamo;
import co.com.biblioteca.modelo.Usuario;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static co.com.biblioteca.mysql.properties.*;


public class PrestamogDAO {
    private static final String INSERT_PRESTAMOG = "INSERT INTO prestamog (id_usuario, id_grabacion, fecha_prestamo, fecha_devolucion, estado) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_PRESTAMOG_POR_ID = "SELECT * FROM prestamog WHERE id=?";
    private static final String UPDATE_PRESTAMOG = "UPDATE prestamog SET id_usuario=?, id_grabacion=?, fecha_prestamo=?, fecha_devolucion=?, estado=? WHERE id=?";
    private static final String DELETE_PRESTAMOG_POR_ID = "DELETE FROM prestamog WHERE id=?";
    private static final String SELECT_TODOS_PRESTAMOGS = "SELECT * FROM prestamog";
    private static final String SELECT_PRESTAMOGS_ACTIVOS = "SELECT * FROM prestamog WHERE estado != 'FINALIZADO'";
    private static final String SELECT_PRESTAMOG_POR_USUARIO = "SELECT p.* FROM prestamog p " + "INNER JOIN usuario u ON p.id_usuario = u.id " + "WHERE u.correo = ?";


    private static final GrabacionDAO grabacionDAO = new GrabacionDAO();

    private UsuarioDAO usuarioDAO;

    public PrestamogDAO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public void crearPrestamog(Prestamo prestamog) {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             PreparedStatement statement = conn.prepareStatement(INSERT_PRESTAMOG)) {
            statement.setInt(1, prestamog.getUsuario());
            statement.setInt(2, prestamog.getLibro()); //getLibro= hacer referencia de ambos libro y grabacion
            statement.setString(3, prestamog.getFechaPrestamo());
            statement.setString(4, prestamog.getFechaDevolucion());
            statement.setString(5, prestamog.getEstado());
            statement.executeUpdate();
            Grabacion grabacionExistente = grabacionDAO.obtenerGrabacionPorId  (prestamog.getLibro());
            grabacionExistente.prestamo();
            grabacionDAO.actualizarGrabacion(grabacionExistente);
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
    }


    public Prestamo obtenerPrestamo(int id) {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             PreparedStatement statement = conn.prepareStatement(SELECT_PRESTAMOG_POR_ID)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return crearPrestamoDesdeResultSet(rs);
                }
            }
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
        return null;
    }

    public void actualizarPrestamo(Prestamo prestamog) {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             PreparedStatement statement = conn.prepareStatement(UPDATE_PRESTAMOG)) {
            prepararStatementParaPrestamo(statement, prestamog);
            statement.setInt(6, prestamog.getId());
            statement.executeUpdate();
            Grabacion grabacionExistente = grabacionDAO.obtenerGrabacionPorId  (prestamog.getLibro());
            grabacionExistente.devolver();
            grabacionDAO.actualizarGrabacion(grabacionExistente);
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
    }

    public void eliminarPrestamo(int id) {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             PreparedStatement statement = conn.prepareStatement(DELETE_PRESTAMOG_POR_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
    }

    public List<Prestamo> obtenerTodosLosPrestamos() {
        List<Prestamo> prestamogs = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             PreparedStatement statement = conn.prepareStatement(SELECT_TODOS_PRESTAMOGS);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                prestamogs.add(crearPrestamoDesdeResultSet(rs));
            }
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
        return prestamogs;
    }

    public List<Prestamo> obtenerPrestamosActivos() {
        List<Prestamo> prestamogs = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             PreparedStatement statement = conn.prepareStatement(SELECT_PRESTAMOGS_ACTIVOS);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                prestamogs.add(crearPrestamoDesdeResultSet(rs));
            }
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
        return prestamogs;
    }


    private void prepararStatementParaPrestamo(PreparedStatement statement, Prestamo prestamog) throws SQLException {
        if (prestamog != null) {
            statement.setInt(1, prestamog.getUsuario());
            statement.setInt(2, prestamog.getLibro());
            statement.setString(3, LocalDate.now().toString());
            statement.setString(4, LocalDate.now().plusDays(15).toString());
            statement.setString(5, prestamog.getEstado().toString());
        } else {
            throw new IllegalArgumentException("El objeto Prestamo, usuario o grabacion es nulo.");
        }
    }

    public List<Prestamo> obtenerPrestamosPorUsuario(String correoUsuario) {
        List<Prestamo> prestamos = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             PreparedStatement statement = conn.prepareStatement(SELECT_PRESTAMOG_POR_USUARIO)) {
            statement.setString(1, correoUsuario);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    prestamos.add(crearPrestamoDesdeResultSet(rs));
                }
            }
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
        return prestamos;
    }

    private Prestamo crearPrestamoDesdeResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int usuarioId = rs.getInt("id_usuario");
        int grabacionId = rs.getInt("id_grabacion");
        String fechaPrestamo = rs.getString("fecha_prestamo");
        String fechaDevolucion = rs.getString("fecha_devolucion");
        String estado = rs.getString("estado");

        Usuario usuario = usuarioDAO.obtenerUsuario(usuarioId);
        //Grabacion grabacion = Grabacion.obtenerGrabacionDesdeResultSet(rs); // Llama al método estático aquí

        return new Prestamo(id, usuario.getId(), grabacionId, fechaPrestamo, fechaDevolucion, estado);
    }

    private void manejarExcepcionSQL(SQLException e) {
        e.printStackTrace();
    }
}

