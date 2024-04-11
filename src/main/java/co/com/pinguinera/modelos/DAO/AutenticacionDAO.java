package co.com.pinguinera.modelos.DAO;

import co.com.pinguinera.modelos.TipoRol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutenticacionDAO {

    private final Connection conexion;

    public AutenticacionDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public boolean autenticarUsuario(String correo, String contraseña) {
        String sql = "SELECT COUNT(*) AS count FROM Usuarios WHERE Correo = ? AND Contraseña = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, correo);
            statement.setString(2, contraseña);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public TipoRol obtenerRolUsuario(String correo) {
        // Consulta SQL para obtener el RolNombre del usuario autenticado a través de la tabla intermedia UsuarioRoles
        String sql = "SELECT r.RolNombre "
                + "FROM Usuarios u "
                + "JOIN UsuarioRoles ur ON u.UsuarioID = ur.UsuarioID "
                + "JOIN Roles r ON ur.RolID = r.RolID "
                + "WHERE u.Correo = ?";

        // Ejecuta la consulta
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            // Establece el correo del usuario en el parámetro de la consulta
            statement.setString(1, correo);

            // Ejecuta la consulta y obtiene el resultado
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Obtiene el nombre del rol del resultado
                    String rolNombre = resultSet.getString("RolNombre");

                    // Convierte el nombre del rol a TipoRol y lo retorna
                    return TipoRol.valueOf(rolNombre);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Si no se encuentra un rol, retorna null
        return null;
    }

}

