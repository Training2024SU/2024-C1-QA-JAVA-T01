package co.com.training;

import co.com.training.controlador.Menu;
import co.com.training.dao.AutorDAO;
import co.com.training.controlador.AutorC;
import co.com.training.dao.UsuarioDAO;
import co.com.training.integration.database.mysql.MySqlOperation;
import co.com.training.modelo.Rol;
import co.com.training.modelo.Usuario;



import java.sql.SQLException;
import java.util.Scanner;

import static co.com.training.controlador.Autenticador.solicitarInicioSesion;

public class Main {
    private static final String SERVER = "localhost";
    private static final String DATA_BASE_NAME = "biblioteca";
    private static final String USER = "root";
    private static final String PASSWORD = "Janet1976";
    private static final MySqlOperation mySqlOperation = new MySqlOperation();

    public static void main(String[] args) throws SQLException {
        openConnection();

        boolean inicioSesionValido = solicitarInicioSesion();
        if (inicioSesionValido) {
            System.out.println(); // Espacio en blanco
            Menu.mostrarMenu();
        }
        AutorDAO autorDAO = new AutorDAO(mySqlOperation);
        AutorC.ejecutarLogicaPrincipal(autorDAO);

        closeConnection();
    }

    private static void mostrarMenu() {
    }

    public static void openConnection() throws SQLException {
        mySqlOperation.setServer(SERVER);
        mySqlOperation.setDataBaseName(DATA_BASE_NAME);
        mySqlOperation.setUser(USER);
        mySqlOperation.setPassword(PASSWORD);
        mySqlOperation.configureDataBaseConnection(); // Abrir conexión
    }

    public static void closeConnection() throws SQLException {
        mySqlOperation.close(); // Cerrar conexión
    }
}
