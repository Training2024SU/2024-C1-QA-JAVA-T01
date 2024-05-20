package co.com.biblioteca.DAO;

import co.com.biblioteca.modelo.Libro;
import co.com.biblioteca.modelo.Prestamo;
import co.com.biblioteca.modelo.Usuario;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static co.com.biblioteca.mysql.properties.*;


public class PrestamoDAO {
    private static final String INSERT_PRESTAMO = "INSERT INTO prestamo (id_usuario, id_libro, fecha_prestamo, fecha_devolucion, estado) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_PRESTAMO_POR_ID = "SELECT * FROM prestamo WHERE id=?";
    private static final String UPDATE_PRESTAMO = "UPDATE prestamo SET id_usuario=?, id_libro=?, fecha_prestamo=?, fecha_devolucion=?, estado=? WHERE id=?";
    private static final String DELETE_PRESTAMO_POR_ID = "DELETE FROM prestamo WHERE id=?";
    private static final String SELECT_TODOS_PRESTAMOS = "SELECT * FROM prestamo";
    private static final String SELECT_PRESTAMOS_ACTIVOS = "SELECT * FROM prestamo WHERE estado != 'FINALIZADO'";
    private static final String SELECT_PRESTAMO_POR_USUARIO = "SELECT p.* FROM prestamo p " + "INNER JOIN usuario u ON p.id_usuario = u.id " + "WHERE u.correo = ?";


    private static final LibroDAO libroDAO = new LibroDAO();

    private UsuarioDAO usuarioDAO;

    public PrestamoDAO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public void crearPrestamo(Prestamo prestamo) {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             PreparedStatement statement = conn.prepareStatement(INSERT_PRESTAMO)) {
            statement.setInt(1, prestamo.getUsuario());
            statement.setInt(2, prestamo.getLibro());
            statement.setString(3, prestamo.getFechaPrestamo());
            statement.setString(4, prestamo.getFechaDevolucion());
            statement.setString(5, prestamo.getEstado());
            statement.executeUpdate();
            Libro libroExistente = libroDAO.obtenerLibroPorId  (prestamo.getLibro());
            libroExistente.prestamo();
            libroDAO.actualizarLibro(libroExistente);
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
    }


    public Prestamo obtenerPrestamo(int id) {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             PreparedStatement statement = conn.prepareStatement(SELECT_PRESTAMO_POR_ID)) {
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

    public void actualizarPrestamo(Prestamo prestamo) {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             PreparedStatement statement = conn.prepareStatement(UPDATE_PRESTAMO)) {
            prepararStatementParaPrestamo(statement, prestamo);
            statement.setInt(6, prestamo.getId());
            statement.executeUpdate();
            Libro libroExistente = libroDAO.obtenerLibroPorId  (prestamo.getLibro());
            libroExistente.devolver();
            libroDAO.actualizarLibro(libroExistente);
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
    }
    public void entregarPrestamo(Prestamo prestamo) {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             PreparedStatement statement = conn.prepareStatement(UPDATE_PRESTAMO)) {
            prepararStatementParaPrestamo(statement, prestamo);
            statement.setInt(6, prestamo.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
    }

    public void eliminarPrestamo(int id) {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             PreparedStatement statement = conn.prepareStatement(DELETE_PRESTAMO_POR_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
    }

    public List<Prestamo> obtenerTodosLosPrestamos() {
        List<Prestamo> prestamos = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             PreparedStatement statement = conn.prepareStatement(SELECT_TODOS_PRESTAMOS);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                prestamos.add(crearPrestamoDesdeResultSet(rs));
            }
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
        return prestamos;
    }

    public List<Prestamo> obtenerPrestamosActivos() {
        List<Prestamo> prestamos = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             PreparedStatement statement = conn.prepareStatement(SELECT_PRESTAMOS_ACTIVOS);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                prestamos.add(crearPrestamoDesdeResultSet(rs));
            }
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
        return prestamos;
    }

    private void prepararStatementParaPrestamo(PreparedStatement statement, Prestamo prestamo) throws SQLException {
        if (prestamo != null) {
            statement.setInt(1, prestamo.getUsuario());
            statement.setInt(2, prestamo.getLibro());
            statement.setString(3, LocalDate.now().toString());
            statement.setString(4, LocalDate.now().plusDays(15).toString());
            statement.setString(5, prestamo.getEstado().toString());
        } else {
            throw new IllegalArgumentException("El objeto Prestamo, usuario o libro es nulo.");
        }
    }
//lo agregue 03/05 ojoo
    public List<Prestamo> obtenerPrestamosPorUsuario(String correoUsuario) {
        List<Prestamo> prestamos = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             PreparedStatement statement = conn.prepareStatement(SELECT_PRESTAMO_POR_USUARIO)) {
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
        int libroId = rs.getInt("id_libro");
        String fechaPrestamo = rs.getString("fecha_prestamo");
        String fechaDevolucion = rs.getString("fecha_devolucion");
        String estado = rs.getString("estado");

        Usuario usuario = usuarioDAO.obtenerUsuario(usuarioId);
        //Libro libro = Libro.obtenerLibroDesdeResultSet(rs); // Llama al método estático aquí

        return new Prestamo(id, usuario.getId(), libroId, fechaPrestamo, fechaDevolucion, estado);
    }

    private void manejarExcepcionSQL(SQLException e) {
        e.printStackTrace();
    }
}
