package co.com.pinguinera.interfaces;

public interface RolesRepositorio {
    // Métodos de RolesRepositorio
    boolean existeUsuarioConRolAdministrador();
    // Métodos de UsuarioRolesRepositorio
    void asignarRolAUsuario(int usuarioId, int rolId);

}
