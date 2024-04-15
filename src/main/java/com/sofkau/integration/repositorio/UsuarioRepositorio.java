package com.sofkau.integration.repositorio;

import com.sofkau.integration.database.ConexionDatabase;
import com.sofkau.integration.database.mysql.MySqlOperation;
import com.sofkau.model.Usuario;
import com.sofkau.util.CommonOperacion.IngresoQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class UsuarioRepositorio {
    private static MySqlOperation mySqlOperation =  ConexionDatabase.getMySqlOperation();

    public static void crearUsuario(Usuario usuario) {
        String query = String.format("INSERT INTO Usuario (correo, nombre, contrasena) VALUES ('%s', '%s', '%s')",
        usuario.getCorreo(), usuario.getNombre(), usuario.getContrasena());
        IngresoQuery.ejecutarIngresoQuery(query);
    }

    public static HashMap<String, Usuario> consultarUsuarios() {
        String query = "SELECT * FROM Usuario";
        IngresoQuery.ejecutarConsultaQuery(query);
        ResultSet resultSet = mySqlOperation.getResulset();

        HashMap<String, Usuario> usuarios = new HashMap<>();
        try {
            while (resultSet.next()) {
                String correo = resultSet.getString("correo");
                String nombre = resultSet.getString("nombre");
                String contrasena = resultSet.getString("contrasena");

                Usuario usuario = new Usuario();
                usuario.setCorreo(correo);
                usuario.setNombre(nombre);
                usuario.setContrasena(contrasena);

                usuarios.put(correo, usuario);
            }
            return usuarios;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    }
