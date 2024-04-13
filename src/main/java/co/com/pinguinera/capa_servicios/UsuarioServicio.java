package co.com.pinguinera.capa_servicios;

import co.com.pinguinera.capa_datos.UsuarioDAO;
import co.com.pinguinera.modelado.Usuario;

import java.sql.SQLException;
import java.util.List;

public class UsuarioServicio {
    private UsuarioDAO usuarioDAO;

    public UsuarioServicio() {
        this.usuarioDAO = new UsuarioDAO();
    }

    // Obtener todos los usuarios
    public List<Usuario> obtenerTodosLosUsuarios() throws SQLException {
        return usuarioDAO.obtenerTodosLosUsuarios();
    }

    // Filtrar usuarios por nombre
    /*
    public List<Usuario> filtrarUsuariosPorNombre(String nombre) throws SQLException {
        // Implementar este método
        return null;
    }
    */

    // Encontrar un usuario por correo
    /*
    public Usuario encontrarUsuarioPorCorreo(String correo) throws SQLException {
        // Implementar este método
        return null;
    }
    */

    // Contar usuarios por rol
    /*
    public long contarUsuariosPorRol(String rol) throws SQLException {
        // Implementar este método
        return 0;
    }
    */

    // Otros métodos de lógica de negocio pueden ir aquí
    // Por ejemplo, podrías agregar métodos como eliminarUsuario, actualizarUsuario, etc.
}
