package co.com.pinguinera.modelos.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioRolesDAO {

    private final Connection conexion;

    public UsuarioRolesDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void asignarRolAUsuario(int usuarioID, int rolID) {
        String sql = "INSERT INTO UsuarioRoles (UsuarioID, RolID) VALUES (?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, usuarioID);
            statement.setInt(2, rolID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarRolDeUsuario(int usuarioID, int rolID) {
        String sql = "DELETE FROM UsuarioRoles WHERE UsuarioID = ? AND RolID = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, usuarioID);
            statement.setInt(2, rolID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
