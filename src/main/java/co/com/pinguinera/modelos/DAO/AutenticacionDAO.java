package co.com.pinguinera.modelos.DAO;

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
}
