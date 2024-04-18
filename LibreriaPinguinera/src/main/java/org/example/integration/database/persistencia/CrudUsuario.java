package org.example.integration.database.persistencia;

import org.example.integration.database.mysql.MySqlOperation;
import org.example.model.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.integration.database.mysql.MySqlConstants.*;
import static org.example.logica.control.ConstantesLogica.TIPO_USUARIO;
import static org.example.model.Usuario.usuarios;

public class CrudUsuario {

    static MySqlOperation mySqlOperation;

    public CrudUsuario() {
        mySqlOperation=new MySqlOperation();
    }

    public void seleccionarDatos() throws SQLException {
        mySqlOperation.setSqlStatement(String.format(SELECT, TIPO_USUARIO));
        mySqlOperation.executeSqlStatement();
        ResultSet resultSet=mySqlOperation.getResulset();
        llenarUsuarios(resultSet);
    }

    public void crearEntidad(Usuario usuario) {
        mySqlOperation.executeSqlStatementVoid(String.format(CREATE_USUARIO, usuario.getCorreo(), usuario.getNombre(), usuario.getContrasenna()));
    }
    private void llenarUsuarios(ResultSet resultSet) throws SQLException {
        while (resultSet.next()){
            String correo=resultSet.getString("correo");
            String nombre=resultSet.getString("nombre");
            String contrasena=resultSet.getString("contrasena");

            Usuario usuario=new Usuario(correo, nombre, contrasena);
            usuarios.add(usuario);
        }
    }
}
