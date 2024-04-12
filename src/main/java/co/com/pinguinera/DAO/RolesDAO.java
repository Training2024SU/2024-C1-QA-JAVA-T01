package co.com.pinguinera.DAO;

import co.com.pinguinera.modelos.TipoRol;
import co.com.pinguinera.interfaces.RolesRepositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RolesDAO implements RolesRepositorio {

    private final Connection conexion;

    // Constructor que recibe la conexión a la base de datos
    public RolesDAO(Connection conexion) {
        this.conexion = conexion;
    }


    @Override
    public boolean existeUsuarioConRolAdministrador() {
        String sql = "SELECT COUNT(*) FROM UsuarioRoles UR JOIN Roles R ON UR.RolID = R.RolID WHERE R.RolNombre = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, TipoRol.ADMINISTRADOR.name());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Implementación del método asignarRolAUsuario
    @Override
    public void asignarRolAUsuario(int usuarioId, int rolId) {
        String sql = "INSERT INTO UsuarioRoles (UsuarioID, RolID) VALUES (?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, usuarioId);
            statement.setInt(2, rolId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
