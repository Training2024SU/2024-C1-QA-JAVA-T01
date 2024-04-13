package co.com.pinguinera.capa_datos;

import co.com.pinguinera.capa_datos.conexionBD.DataBase;
import co.com.pinguinera.modelado.enums.RolUsuario;
import co.com.pinguinera.modelado.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private static final String CONSULTA_USUARIOS = "SELECT * FROM Usuario";

    public List<Usuario> obtenerTodosLosUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();

        Connection conexion = DataBase.conectar();

        try (PreparedStatement statement = conexion.prepareStatement(CONSULTA_USUARIOS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setCorreo(resultSet.getString("Correo"));
                usuario.setNombre(resultSet.getString("Nombre"));
                usuario.setContrasena(resultSet.getString("Contraseña"));
                usuario.setRol(RolUsuario.valueOf(resultSet.getString("Rol")));

                usuarios.add(usuario);
            }
        }

        return usuarios;
    }
}
