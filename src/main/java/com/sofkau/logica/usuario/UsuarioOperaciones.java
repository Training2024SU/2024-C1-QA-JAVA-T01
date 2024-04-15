package com.sofkau.logica.usuario;

import com.sofkau.integration.repositorio.UsuarioRepositorio;
import com.sofkau.model.Usuario;

import java.sql.ResultSet;
import java.util.HashMap;
import java.sql.SQLException;

public class UsuarioOperaciones {

    private static HashMap<String, Usuario> usuarios = new HashMap<>();

    public static void registrarUsuario(Usuario usuario) {
        UsuarioRepositorio.crearUsuario(usuario);
    }

    public static void getUsuarios() {
        usuarios = UsuarioRepositorio.consultarUsuarios();
    }

}
