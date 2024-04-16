package com.sofkau.logica.usuario;

import com.sofkau.dialogo.MensajeOperacionBd;
import com.sofkau.integration.repositorio.UsuarioRepositorio;
import com.sofkau.model.Usuario;

import java.util.HashMap;
import java.util.Optional;

public class UsuarioOperaciones {

    public UsuarioOperaciones() {
        getUsuarios();
    }

    private Usuario usuarioActual = new Usuario();
    private HashMap<String, Usuario> usuarios = new HashMap<>();


    public void registrarUsuario(Usuario usuario) {
        UsuarioRepositorio.crearUsuario(usuario);
        usuarios.put(usuario.getCorreo(),usuario);
        MensajeOperacionBd.crearUsuario();
        System.out.println(usuario);
    }

    public void getUsuarios() {
        usuarios = UsuarioRepositorio.consultarUsuarios();
    }

    public boolean inicioSesion(String correo, String contrasena) {
        Optional<Usuario> usuarioVal = usuarios.values().stream()
                .filter(usuario -> usuario.getCorreo().equals(correo) && usuario.getContrasena().equals(contrasena))
                .findFirst();

        if(usuarioVal.isPresent()){
            usuarioActual = usuarioVal.get();
            return true;
        }
        return false;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
}
