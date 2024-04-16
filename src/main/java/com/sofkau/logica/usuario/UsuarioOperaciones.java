package com.sofkau.logica.usuario;

import com.sofkau.dialogo.MensajeOperacionBd;
import com.sofkau.dialogo.Menu;
import com.sofkau.integration.repositorio.UsuarioRepositorio;
import com.sofkau.model.Usuario;

import java.sql.ResultSet;
import java.util.HashMap;
import java.sql.SQLException;
import java.util.Optional;

public class UsuarioOperaciones {

    public UsuarioOperaciones() {
        getUsuarios();
    }

    private static Usuario usuarioActual = new Usuario();
    private static HashMap<String, Usuario> usuarios = new HashMap<>();

    public static void registrarUsuario(Usuario usuario) {
        UsuarioRepositorio.crearUsuario(usuario);
        usuarios.put(usuario.getCorreo(),usuario);
        MensajeOperacionBd.crearUsuario();
        System.out.println(usuario);
    }

    public static void getUsuarios() {
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


}
