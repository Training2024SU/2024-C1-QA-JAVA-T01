package co.com.training.dao;

import co.com.training.integration.database.mysql.MySqlOperation;
import co.com.training.modelo.Rol;
import co.com.training.modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    private final MySqlOperation mySqlOperation;

    public UsuarioDAO(MySqlOperation mySqlOperation) {
        this.mySqlOperation = mySqlOperation;
    }

    public void insertarUsuario(Usuario usuario) throws SQLException {
        String query = "INSERT INTO usuario (nombre_usuario, correo_usuario, contrasena_usuario, rol) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = mySqlOperation.getConnection().prepareStatement(query)) {
            stmt.setString(1, usuario.getNombre_usuario());
            stmt.setString(2, usuario.getCorreo_usuario());
            stmt.setString(3, usuario.getContrasena_usuario());
            stmt.setString(4, usuario.getRol().toString());
            stmt.executeUpdate();
        }
    }

    public void actualizarUsuario(Usuario usuario) throws SQLException {
        String query = "UPDATE usuario SET nombre_usuario=?, correo_usuario=?, contrasena_usuario=?, rol=? WHERE id_usuario=?";
        try (PreparedStatement stmt = mySqlOperation.getConnection().prepareStatement(query)) {
            stmt.setString(1, usuario.getNombre_usuario());
            stmt.setString(2, usuario.getCorreo_usuario());
            stmt.setString(3, usuario.getContrasena_usuario());
            stmt.setString(4, usuario.getRol().toString());
            stmt.setInt(5, usuario.getId_usuario());
            stmt.executeUpdate();
        }
    }

    public void eliminarUsuario(int idUsuario) throws SQLException {
        String query = "DELETE FROM usuario WHERE id_usuario=?";
        try (PreparedStatement stmt = mySqlOperation.getConnection().prepareStatement(query)) {
            stmt.setInt(1, idUsuario);
            stmt.executeUpdate();
        }
    }

    public Usuario consultarUsuario(int correoUsuario) throws SQLException {
        String query = "SELECT * FROM usuario WHERE correo_usuario=?";
        try (PreparedStatement stmt = mySqlOperation.getConnection().prepareStatement(query)) {
            stmt.setInt(1, correoUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getInt("id_usuario"),
                            rs.getString("nombre_usuario"),
                            rs.getString("correo_usuario"),
                            rs.getString("contrasena_usuario"),
                            Rol.valueOf(rs.getString("rol"))
                    );
                }
            }
        }
        return null;
    }

}

