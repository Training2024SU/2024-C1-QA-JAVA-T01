package co.com.ejercicio.modeloAccesoBD;

import co.com.ejercicio.modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static co.com.ejercicio.conexionBd.MapeoBD.MapearTablasBD.mapearResultSetAUsuario;
import static co.com.ejercicio.conexionBd.constantesCRUD.QueryConstante.*;
import static co.com.ejercicio.menu.constantesMenu.OperacionExitosaOFallida.OPERACION_EXITOSA;
import static co.com.ejercicio.menu.constantesMenu.OperacionExitosaOFallida.OPERACION_FALLIDA;

public class UsuarioAccesoBD {
    private Connection conexion;

    public UsuarioAccesoBD(Connection conexion) {
        this.conexion = conexion;
    }

    public void agregarUsuario(Usuario usuario) {
        try (PreparedStatement statement = conexion.prepareStatement(INSERT_USUARIO)) {
            statement.setString(1, usuario.getCorreo());
            statement.setString(2, usuario.getNombre());
            statement.setString(3, usuario.getContrasenia());
            statement.executeUpdate();
            System.out.println(OPERACION_EXITOSA);
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + e);;
        }
    }
    public List<Usuario> obtenerTodosLosUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            PreparedStatement statement = conexion.prepareStatement(SELECT_ALL_FROM_USUARIO);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                usuarios.add(mapearResultSetAUsuario(resultSet));
            }
            System.out.println(OPERACION_EXITOSA);
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + e);
        }
        return usuarios;
    }

    public void actualizarUsuario(Usuario usuario) throws SQLException {
        try (PreparedStatement statement = conexion.prepareStatement(UPDATE_USUARIO)) {
            statement.setString(1, usuario.getCorreo());
            statement.setString(2, usuario.getNombre());
            statement.setString(3, usuario.getContrasenia());

            statement.executeUpdate();
            System.out.println(OPERACION_EXITOSA);
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + (e));
        }
    }
    public void eliminarUsuario(String correo) throws SQLException {
        try (PreparedStatement statement = conexion.prepareStatement(DELETE_FROM_USUARIO)) {
            statement.setString(1, correo);
            statement.executeUpdate();
            System.out.println(OPERACION_EXITOSA);
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + (e));
        }
    }
}


