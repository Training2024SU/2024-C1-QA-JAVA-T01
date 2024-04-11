package co.com.pinguinera.DAO;

import co.com.pinguinera.modelos.TipoRol;
import co.com.pinguinera.interfaces.RolesRepositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RolDAO implements RolesRepositorio {

    private final Connection conexion;

    // Constructor que recibe la conexión a la base de datos
    public RolDAO(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void agregarRol(TipoRol rol) {
        String sql = "INSERT INTO Roles (RolNombre) VALUES (?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, rol.name());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarRol(int rolId, TipoRol nuevoRol) {
        String sql = "UPDATE Roles SET RolNombre = ? WHERE RolID = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, nuevoRol.name());
            statement.setInt(2, rolId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarRol(int rolId) {
        String sql = "DELETE FROM Roles WHERE RolID = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, rolId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TipoRol> obtenerTodosLosRoles() {
        List<TipoRol> roles = new ArrayList<>();
        String sql = "SELECT * FROM Roles";
        try (PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String nombreRol = resultSet.getString("RolNombre");
                TipoRol rol = TipoRol.valueOf(nombreRol);
                roles.add(rol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    @Override
    public TipoRol buscarRolPorNombre(String rolNombre) {
        String sql = "SELECT * FROM Roles WHERE RolNombre = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, rolNombre);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nombreRol = resultSet.getString("RolNombre");
                    return TipoRol.valueOf(nombreRol);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean existeUsuarioConRolAdministrador() {
        String sql = "SELECT COUNT(*) FROM UsuarioRoles UR JOIN Roles R ON UR.RolID = R.RolID WHERE R.RolNombre = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, TipoRol.ADMINISTRADOR.name()); // Nombre del rol ADMINISTRADOR
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




}
