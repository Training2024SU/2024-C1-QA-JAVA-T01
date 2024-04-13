package co.com.pinguinera.controladores;
import co.com.pinguinera.interfaces.RolesRepositorio;
import co.com.pinguinera.interfaces.UsuarioRepositorio;

public class MenuAdministradorControlador {

    private UsuarioRepositorio usuarioRepositorio;
    private RolesRepositorio rolesRepositorio;

    public MenuAdministradorControlador(UsuarioRepositorio usuarioRepositorio, RolesRepositorio rolesRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.rolesRepositorio = rolesRepositorio;
    }
    // Método para actualizar un usuario
    public void actualizarUsuario(Usuario usuario) {
        usuarioRepositorio.actualizarUsuario(usuario);
        System.out.println("Usuario actualizado con éxito.");
    }

    // Método para eliminar un usuario
    public void eliminarUsuario(int usuarioId) {
        usuarioRepositorio.eliminarUsuario(usuarioId);
        System.out.println("Usuario eliminado con éxito.");
    }

}
