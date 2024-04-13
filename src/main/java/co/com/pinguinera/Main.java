package co.com.pinguinera;

import co.com.pinguinera.DAO.UsuarioDAO;
import co.com.pinguinera.modelos.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Obtén la conexión a la base de datos
            Connection conexion = DataBase.conectar();

            // Crea una instancia de UsuarioDAO
            UsuarioDAO usuarioDAO = new UsuarioDAO();

            // Cuando la aplicación termine, cierra la conexión a la base de datos
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
