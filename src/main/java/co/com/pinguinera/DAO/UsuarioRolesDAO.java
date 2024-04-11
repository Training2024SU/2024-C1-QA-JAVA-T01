package co.com.pinguinera.DAO;

import co.com.pinguinera.interfaces.UsuarioRolesRepositorio;
import co.com.pinguinera.modelos.TipoRol;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRolesDAO implements UsuarioRolesRepositorio {
    private final Connection conexion;

    public UsuarioRolesDAO(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
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

    @Override
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

    @Override
    public List<TipoRol> obtenerRolesDeUsuario(int usuarioId) {
        List<TipoRol> roles = new ArrayList<>();
        String sql = "SELECT r.Nombre FROM Roles r JOIN UsuarioRoles ur ON r.ID = ur.RolID WHERE ur.UsuarioID = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, usuarioId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    // Obtén el nombre del rol desde el resultado de la consulta
                    String nombreRol = resultSet.getString("Nombre");

                    // Usa el método valueOf() de la enumeración TipoRol para obtener el valor correcto
                    TipoRol rol = TipoRol.valueOf(nombreRol.toUpperCase());

                    roles.add(rol);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roles;
    }
}
