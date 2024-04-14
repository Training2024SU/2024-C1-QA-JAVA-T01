package co.com.pinguinera.capa_datos;

import co.com.pinguinera.capa_datos.interfaces.GestorBD;
import co.com.pinguinera.modelado.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO extends AbstractDAO<Usuario> {

    private static final String CONSULTA_USUARIOS = "SELECT * FROM Usuario";
    private static final String INSERTAR_USUARIO = "INSERT INTO Usuario (Correo, Nombre, Contraseña) VALUES (?, ?, ?)";
    private static final String ACTUALIZAR_USUARIO = "UPDATE Usuario SET Nombre = ?, Contraseña = ? WHERE idUsuario = ?";
    private static final String ELIMINAR_USUARIO = "DELETE FROM Usuario WHERE idUsuario = ?";

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
        usuario.setIdUsuario(resultSet.getInt("idUsuario")); // Ajuste para incluir idUsuario
        usuario.setCorreo(resultSet.getString("Correo"));
        usuario.setNombre(resultSet.getString("Nombre"));
        usuario.setContrasena(resultSet.getString("Contraseña"));
        return usuario;
    }

    // Implementación de los métodos CRUD

    // Método para insertar un nuevo usuario en la base de datos
    @Override
    public void insertar(Usuario usuario) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(INSERTAR_USUARIO)) {
            statement.setString(1, usuario.getCorreo());
            statement.setString(2, usuario.getNombre());
            statement.setString(3, usuario.getContrasena());
            statement.executeUpdate();
        }
    }

    // Método para actualizar un usuario existente en la base de datos
    @Override
    public void actualizar(Usuario usuario) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(ACTUALIZAR_USUARIO)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getContrasena());
            statement.setInt(3, usuario.getIdUsuario()); // Usar idUsuario en la actualización
            statement.executeUpdate();
        }
    }

    // Método para eliminar un usuario de la base de datos
    @Override
    public void eliminar(int idUsuario) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(ELIMINAR_USUARIO)) {
            statement.setInt(1, idUsuario); // Usar idUsuario en la eliminación
            statement.executeUpdate();
        }
    }

    @Override
    public int obtenerId(Usuario usuario) throws SQLException {
        // Devuelve el ID del objeto Usuario
        return usuario.getIdUsuario();
    }

}
