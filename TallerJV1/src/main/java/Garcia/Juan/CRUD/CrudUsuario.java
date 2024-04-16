package Garcia.Juan.CRUD;

import java.sql.ResultSet;
import java.sql.SQLException;

import Garcia.Juan.database.mysql.MySqlOperation;
import Garcia.Juan.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class CrudUsuario {
    private static MySqlOperation mySqlOperation;

    private static final String GET_USERS = "SELECT * FROM bibliotecapingu.usuario";
    protected static final String INSERT_USER = "INSERT INTO bibliotecapingu.usuario (correo, contrasenha ,nombre, rol) VALUES ('%s','%s','%s','%s')";
    protected static final String INSERT_ADMIN = "INSERT IGNORE INTO bibliotecapingu.usuario (correo, contrasenha ,nombre, rol) VALUES ('%s','%s','%s','%s')";

    public CrudUsuario(MySqlOperation mySqlOperation) {
        this.mySqlOperation = mySqlOperation;
    }

    public static List<Usuario> getUsersFromTable(MySqlOperation mySqlOperation) throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            mySqlOperation.setSqlStatement(String.format(GET_USERS));
            mySqlOperation.executeSqlStatement();
            ResultSet resultSet = mySqlOperation.getResulset();

            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setCorreo(resultSet.getString(1));
                usuario.setContrasena(resultSet.getString(2));
                usuario.setNombre(resultSet.getString(3));
                usuario.setRol(resultSet.getString(4));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener usuarios: " + e.getMessage());
        }
        return usuarios;
    }

    public static void insertUser(MySqlOperation mySqlOperation, List<String> credenciales){
        String correo = credenciales.get(0);
        String contrasena = credenciales.get(1);
        String nombre = credenciales.get(2);
        String rol = credenciales.get(3);
        String statement = String.format(INSERT_USER,correo,contrasena,nombre,rol);
        mySqlOperation.setSqlStatement(statement);
        mySqlOperation.executeSqlStatementVoid();
    }

    public static void crearAdmin(MySqlOperation mySqlOperation){
        String correo = "administrador@pingu.com.co";
        String contrasena = "contrasenasegura123456";
        String nombre = "John Doe";
        String rol = "ADMINISTRADOR";
        String statement = String.format(INSERT_ADMIN,correo,contrasena,nombre,rol);
        mySqlOperation.setSqlStatement(statement);
        mySqlOperation.executeSqlStatementVoid();

    }
}
