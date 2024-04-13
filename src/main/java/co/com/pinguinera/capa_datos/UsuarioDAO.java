package co.com.pinguinera.capa_datos;

import co.com.pinguinera.capa_datos.conexionBD.ConexionBD;
import co.com.pinguinera.modelado.enums.RolEmpleado;
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

        Connection conexion = ConexionBD.conectar();

        try (PreparedStatement statement = conexion.prepareStatement(CONSULTA_USUARIOS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setCorreo(resultSet.getString("Correo"));
                usuario.setNombre(resultSet.getString("Nombre"));
                usuario.setContrasena(resultSet.getString("Contraseña"));

                usuarios.add(usuario);
            }
        }

        return usuarios;
    }
}
