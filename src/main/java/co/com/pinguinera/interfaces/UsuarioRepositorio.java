package co.com.pinguinera.interfaces;

import java.util.List;

public interface UsuarioRepositorio {
    void agregarUsuario(Usuario usuario);
    void actualizarUsuario(Usuario usuario);
    void eliminarUsuario(int usuarioId);
    List<Usuario> obtenerTodosLosUsuarios();
    Usuario buscarUsuarioPorCorreo(String correo);
}
