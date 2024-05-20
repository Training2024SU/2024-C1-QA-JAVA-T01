package co.com.biblioteca.DAO;

import static co.com.biblioteca.mysql.properties.*;
import co.com.biblioteca.modelo.Grabacion;
import co.com.biblioteca.mysql.MySQLDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class GrabacionDAO {
        private static final String SELECT_GRABACION_POR_ID = "SELECT * FROM grabacion WHERE id=?";
        private static final String INSERT_GRABACION = "INSERT INTO grabacion (titulo, autor, genero, duracion, cantidad_ejemplares, cantidad_prestados, cantidad_disponible) VALUES (?, ?, ?, ?, ?, ?, ?)";
        private static final String UPDATE_GRABACION = "UPDATE grabacion SET titulo=?, autor=?, genero=?, duracion=?, cantidad_ejemplares=?, cantidad_prestados=?, cantidad_disponible=? WHERE id=?";
        private static final String DELETE_GRABACION_POR_ID = "DELETE FROM grabacion WHERE id=?";
        private static final String SELECT_TODOS_GRABACION = "SELECT * FROM grabacion";
        private static final String BUSCAR_GRABACION = "SELECT * FROM grabacion WHERE titulo LIKE ? OR autor LIKE ?";
        private static final String query = "SELECT * FROM grabacion WHERE id = ?";

        private static Connection conexion;

        public GrabacionDAO() {
            this.conexion = MySQLDB.obtenerConexion();
        }

        public Grabacion obtenerGrabacionPorId(int id) {
            try (PreparedStatement statement = conexion.prepareStatement(SELECT_GRABACION_POR_ID)) {
                statement.setInt(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        return crearGrabacionDesdeResultSet(rs);
                    }
                }
            } catch (SQLException e) {
                manejarExcepcionSQL(e);
            }
            return null;
        }

        public void agregarGrabacion(Grabacion grabacion) {
            try (PreparedStatement statement = conexion.prepareStatement(INSERT_GRABACION)) {
                prepararStatementParaGrabacion(statement, grabacion);
                statement.executeUpdate();
            } catch (SQLException e) {
                manejarExcepcionSQL(e);
            }
        }

        public void actualizarGrabacion(Grabacion grabacion) {
            try (PreparedStatement statement = conexion.prepareStatement(UPDATE_GRABACION)) {
                prepararStatementParaGrabacion(statement, grabacion);
                statement.setInt(8, grabacion.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                manejarExcepcionSQL(e);
            }
        }

        public void eliminarGrabacionPorId(int id) {
            try (PreparedStatement statement = conexion.prepareStatement(DELETE_GRABACION_POR_ID)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                manejarExcepcionSQL(e);
            }
        }

        public List<Grabacion> obtenerTodasLasGrabaciones() {
            List<Grabacion> Grabacion = new ArrayList<>();
            try (PreparedStatement statement = conexion.prepareStatement(SELECT_TODOS_GRABACION); ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Grabacion.add(crearGrabacionDesdeResultSet(rs));
                }
            } catch (SQLException e) {
                manejarExcepcionSQL(e);
            }
            return Grabacion;
        }

        public static Grabacion obtenerGrabacion(int id) {
            Grabacion grabacion = null;

            try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
                 PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        grabacion = new Grabacion();
                        grabacion.setId(rs.getInt("id"));
                        grabacion.setTitulo(rs.getString("titulo"));
                        grabacion.setAutor(rs.getString("autor"));
                        // Setear otros atributos...
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return grabacion;
        }

        public List<Grabacion> buscarGrabacion(String criterioBusqueda) {
            List<Grabacion> grabacionesEncontradas = new ArrayList<>();
            try (PreparedStatement statement = conexion.prepareStatement(BUSCAR_GRABACION)) {
                String criterio = "%" + criterioBusqueda + "%";
                statement.setString(1, criterio);
                statement.setString(2, criterio);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Grabacion grabacion = crearGrabacionDesdeResultSet(resultSet);
                        grabacionesEncontradas.add(grabacion);
                    }
                }
            } catch (SQLException e) {
                manejarExcepcionSQL(e);
            }
            return grabacionesEncontradas;
        }

        private void prepararStatementParaGrabacion(PreparedStatement statement, Grabacion grabacion) throws SQLException {
            statement.setString(1, grabacion.getTitulo());
            statement.setString(2, grabacion.getAutor());
            statement.setString(3, grabacion.getGenero());
            statement.setInt(4, grabacion.getDuracion());
            statement.setInt(5, grabacion.getCantidadEjemplares());
            statement.setInt(6, grabacion.getCantidadPrestados());
            statement.setInt(7, grabacion.getCantidadDisponible());
        }

        private void manejarExcepcionSQL(SQLException e) {
            e.printStackTrace();
        }

        private Grabacion crearGrabacionDesdeResultSet(ResultSet rs) throws SQLException {
            return new Grabacion(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getString("genero"),
                    rs.getInt("duracion"),
                    rs.getInt("cantidad_ejemplares"),
                    rs.getInt("cantidad_prestados"),
                    rs.getInt("cantidad_disponible")
            );
        }
}
