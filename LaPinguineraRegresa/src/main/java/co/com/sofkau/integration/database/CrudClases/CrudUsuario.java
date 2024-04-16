package co.com.sofkau.integration.database.CrudClases;

import co.com.sofkau.integration.database.mysql.MySqlOperation;
import co.com.sofkau.model.Empleado;
import co.com.sofkau.model.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUsuario {

    public static void consultarTodosUsuarios(MySqlOperation mySqlOperation) throws SQLException {
        mySqlOperation.setSqlStatement("SELECT * FROM usuario");
        mySqlOperation.executeSqlStatement();

        ResultSet resultSet = mySqlOperation.getResulset();


        while (resultSet.next()) {

            String nombre = resultSet.getString("nombre");
            String clave = resultSet.getString("clave");
            String correo = resultSet.getString("correo");


            Usuario usuarioAgregar = new Usuario(nombre, correo, clave);
            Usuario.usuarios.add(usuarioAgregar);
        }

    }

    public static void agregarUsuario(MySqlOperation mySqlOperation, String correo, String nombre, String clave) {

        mySqlOperation.setSqlStatement("INSERT INTO usuario (nombre, clave, correo) VALUES (?, ?, ?)");
        try {
            mySqlOperation.executeSqlStatementWithParameters(nombre,clave,correo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("usuario Registrado Exitosamente");
    }

    public static void actualizarUsuario(String correo, MySqlOperation mySqlOperation) {

        String nombre = "amanda";
        String clave = "696969";

        mySqlOperation.setSqlStatement("UPDATE usuario SET nombre = ?, clave = ? WHERE correo = ?");
        try {
            mySqlOperation.executeSqlStatementWithParameters(nombre,clave,correo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Usuario de correo " + correo + "actualizado correctamente");
    }

    public static void eliminarUsuario(String correo, MySqlOperation mySqlOperation) {

        mySqlOperation.setSqlStatement("DELETE FROM usuario WHERE correo = ?");
        try {
            mySqlOperation.executeSqlStatementWithParameters(correo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Usuario Eliminado del correo "+ correo + " de la base de datos");

    }
}
