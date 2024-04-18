package org.moreno.cristian.repositorios;

import org.moreno.cristian.modelos.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface RepositorioUsuario {
    Optional<Usuario> validarCredenciales(String correo, String contrasenia);
    Optional<Usuario> guardarUsuario(Usuario nuevoUsuario);
    Optional<List<Usuario>> listarUsuarios();
}
