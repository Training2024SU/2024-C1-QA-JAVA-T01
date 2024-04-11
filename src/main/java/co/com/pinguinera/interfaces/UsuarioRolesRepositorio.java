package co.com.pinguinera.interfaces;

import co.com.pinguinera.modelos.TipoRol;

import java.util.List;

public interface UsuarioRolesRepositorio {
    void asignarRolAUsuario(int usuarioId, int rolId);
    void eliminarRolDeUsuario(int usuarioId, int rolId);
    List<TipoRol> obtenerRolesDeUsuario(int usuarioId);
}
