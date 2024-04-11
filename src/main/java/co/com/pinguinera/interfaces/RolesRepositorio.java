package co.com.pinguinera.interfaces;

import co.com.pinguinera.modelos.TipoRol;

import java.util.List;

public interface RolesRepositorio {
    void agregarRol(TipoRol rol);
    void actualizarRol(int rolId, TipoRol nuevoRol);
    void eliminarRol(int rolId);
    List<TipoRol> obtenerTodosLosRoles();
    TipoRol buscarRolPorNombre(String rolNombre);
    boolean existeUsuarioConRolAdministrador();
}
