package co.com.pinguinera.DAO;

import co.com.pinguinera.modelos.Usuario;
import co.com.pinguinera.interfaces.UsuarioRepositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements UsuarioRepositorio {

    private final Connection conexion;

    // Constructor que recibe la conexión a la base de datos
    public UsuarioDAO(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void agregarUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuarios (Nombre, Correo, Contraseña) VALUES (?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getCorreo());
            statement.setString(3, usuario.getContraseña());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                usuario.setUsuarioID(generatedKeys.getInt(1)); // Obtener el ID generado para el usuario
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE Usuarios SET Nombre = ?, Correo = ?, Contraseña = ? WHERE UsuarioID = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getCorreo());
            statement.setString(3, usuario.getContraseña());
            statement.setInt(4, usuario.getUsuarioID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarUsuario(int usuarioId) {
        String sql = "DELETE FROM Usuarios WHERE UsuarioID = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, usuarioId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuarios";
        try (PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setUsuarioID(resultSet.getInt("UsuarioID"));
                usuario.setNombre(resultSet.getString("Nombre"));
                usuario.setCorreo(resultSet.getString("Correo"));
                usuario.setContraseña(resultSet.getString("Contraseña"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public Usuario buscarUsuarioPorCorreo(String correo) {
        String sql = "SELECT * FROM Usuarios WHERE Correo = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, correo);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setUsuarioID(resultSet.getInt("UsuarioID"));
                    usuario.setNombre(resultSet.getString("Nombre"));
                    usuario.setCorreo(resultSet.getString("Correo"));
                    usuario.setContraseña(resultSet.getString("Contraseña"));
                    return usuario;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
