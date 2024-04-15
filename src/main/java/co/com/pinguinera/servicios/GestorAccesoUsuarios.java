package co.com.pinguinera.servicios;

import co.com.pinguinera.datos.UsuarioDAO;
import co.com.pinguinera.datos.model.Usuario;

import java.sql.SQLException;
import java.util.List;

public class GestorAccesoUsuarios {
    private UsuarioDAO usuarioDAO;
    private List<Usuario> listaUsuarios; // Cambiar a `List<Usuario>`

    public GestorAccesoUsuarios(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
        this.listaUsuarios = List.of(); // Inicializar la lista vacía
    }

    /**
     * Carga la lista de todos los usuarios desde la base de datos.
     */
    public void cargarUsuarios() {
        try {
            listaUsuarios = usuarioDAO.obtenerTodos(); // Llamar a `obtenerTodos` en `UsuarioDAO`
        } catch (SQLException e) {
            System.err.println("Error al cargar usuarios: " + e.getMessage());
        }
    }

    /**
     * Verifica si un usuario especificado (correo y contraseña) se encuentra en la lista de usuarios.
     *
     * @param correo      Correo del usuario a verificar.
     * @param contrasena  Contraseña del usuario a verificar.
     * @return true si el usuario está en la lista, false en caso contrario.
     */
    public boolean verificarUsuario(String correo, String contrasena) {
        cargarUsuarios(); // Cargar la lista de usuarios desde la base de datos

        // Verificar si un usuario con el correo y contraseña especificados está presente en la lista
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getCorreo().equals(correo) && usuario.getContrasena().equals(contrasena)) {
                return true; // Usuario encontrado
            }
        }
        return false; // Usuario no encontrado
    }
}
