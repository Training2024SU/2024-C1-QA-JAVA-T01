package com.sofkau.integration.repositorio;

import com.sofkau.integration.database.ConexionDatabase;
import com.sofkau.integration.database.mysql.MySqlOperation;
import com.sofkau.model.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class UsuarioRepositorio {
    private static MySqlOperation mySqlOperation =  ConexionDatabase.getMySqlOperation();

    public static void crearUsuario(Usuario usuario) throws SQLException {
        String query = String.format("INSERT INTO Usuario (correo, nombre, contrasena) VALUES ('%s', '%s', '%s')",
        usuario.getCorreo(), usuario.getNombre(), usuario.getContrasena());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
        ResultSet result = mySqlOperation.getResulset();
    }

    public static HashMap<String, Usuario> consultarUsuarios() throws SQLException {
        String query = "SELECT * FROM Usuario";
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatement();
        ResultSet resultSet = mySqlOperation.getResulset();

        HashMap<String, Usuario> usuarios = new HashMap<>();
        while (resultSet.next()) {
            String correo = resultSet.getString("correo");
            String nombre = resultSet.getString("nombre");
            String contrasena = resultSet.getString("contrasena");

            Usuario usuario = new Usuario();
            usuario.setCorreo(correo);
            usuario.setNombre(nombre);
            usuario.setContrasena(contrasena);

            usuarios.put(correo,usuario);
        }

        return usuarios;
    }

    
}
