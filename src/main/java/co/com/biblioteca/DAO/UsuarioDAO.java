package co.com.biblioteca.DAO;

import co.com.biblioteca.modelo.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static co.com.biblioteca.mysql.MySQLDB.conexion;


public class UsuarioDAO {
    private static final String INSERT_USUARIO = "INSERT INTO usuario (nombre, correo, contrasena) VALUES (?, ?, ?)";
    private static final String INICIAR_SESION = "SELECT * FROM usuario WHERE correo = ? AND contrasena = ? ";
    private static final String ACTUALIZAR_USUARIO = "UPDATE usuario SET nombre = ?, correo = ?, contrasena = ? WHERE id = ?";
    private static final String OBTENER_USUARIO = "SELECT * FROM usuario WHERE id = ?";
    private static final String OBTENER_TODOS_USUARIOS = "SELECT * FROM usuario";
    private static final String ELIMINAR_USUARIO = "DELETE FROM usuario WHERE id = ?";



    public void crearUsuario(Usuario usuario) {
        try (PreparedStatement statement = conexion.prepareStatement(INSERT_USUARIO, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getCorreo());
            statement.setString(3, usuario.getContrasena());
            statement.executeUpdate();
            //asignarIdGenerado(usuario, statement);
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
    }

    public Usuario iniciarSesion(String correo, String contrasena) throws SQLException {
        try (PreparedStatement statement = conexion.prepareStatement(INICIAR_SESION)) {
            statement.setString(1, correo);
            statement.setString(2, contrasena);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    //usuario encontrado
                    String nombre = rs.getString("nombre");
                    String contra = rs.getString("contrasena");
                    String email = rs.getString("correo");
                    int id = rs.getInt("id");
                    Usuario usuario = new Usuario(id, nombre, email, contra);
                    return usuario;
                } else {
                    System.out.println("Usuario con correo o contraseña incorrecta");
                    return null;

                }
            }
        }
    }

    public Usuario obtenerUsuario(int id) {
        try (PreparedStatement statement = conexion.prepareStatement(OBTENER_USUARIO)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return crearUsuarioDesdeResultSet(rs);
                }
            }
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
        return null;
    }

    public void actualizarUsuario(Usuario usuario) {
        try (PreparedStatement statement = conexion.prepareStatement(ACTUALIZAR_USUARIO)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getCorreo());
            statement.setString(3, usuario.getContrasena());
            statement.setInt(4, usuario.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
    }

    public void eliminarUsuario(int id) {
        try (PreparedStatement statement = conexion.prepareStatement(ELIMINAR_USUARIO)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try (PreparedStatement statement = conexion.prepareStatement(OBTENER_TODOS_USUARIOS); ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                usuarios.add(crearUsuarioDesdeResultSet(rs));
            }
        } catch (SQLException e) {
            manejarExcepcionSQL(e);
        }
        return usuarios;
    }

    private Usuario crearUsuarioDesdeResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        String correo = rs.getString("correo");
        String contrasena = rs.getString("contrasena");
        return new Usuario(id, nombre, correo, contrasena);
    }

    private void manejarExcepcionSQL(SQLException e) {
        e.printStackTrace();
    }
}

