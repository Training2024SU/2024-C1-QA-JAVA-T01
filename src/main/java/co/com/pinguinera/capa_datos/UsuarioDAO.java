package co.com.pinguinera.capa_datos;

import co.com.pinguinera.capa_datos.interfaces.GestorBD;
import co.com.pinguinera.modelado.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends AbstractDAO<Usuario> {

    private static final String CONSULTA_USUARIOS = "SELECT * FROM Usuario";

    // Constructor que recibe un objeto GestorBD para establecer la conexión
    public UsuarioDAO(GestorBD gestorBD) {
        super(gestorBD);
    }

    @Override
    protected String obtenerConsultaTodos() {
        // Devuelve la consulta SQL específica para obtener todos los registros de la tabla Usuario
        return CONSULTA_USUARIOS;
    }

    @Override
    protected Usuario convertirFilaAObjeto(ResultSet resultSet) throws SQLException {
        // Crea un objeto Usuario a partir de una fila del ResultSet
        Usuario usuario = new Usuario();
        usuario.setCorreo(resultSet.getString("Correo"));
        usuario.setNombre(resultSet.getString("Nombre"));
        usuario.setContrasena(resultSet.getString("Contraseña"));
        // Otros campos de Usuario se pueden establecer aquí

        return usuario;
    }
}
