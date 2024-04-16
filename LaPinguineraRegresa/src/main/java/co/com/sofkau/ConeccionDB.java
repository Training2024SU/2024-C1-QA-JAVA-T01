package co.com.sofkau;

import co.com.sofkau.integration.database.CrudClases.*;
import co.com.sofkau.integration.database.mysql.MySqlConstants;
import co.com.sofkau.integration.database.mysql.MySqlOperation;
import co.com.sofkau.logica.prestamo.PrestamoUtils;
import co.com.sofkau.logica.publicacion.LibroUtils;
import co.com.sofkau.logica.publicacion.NovelaUtils;
import co.com.sofkau.logica.publicacion.PublicacionUtils;
import co.com.sofkau.logica.usuario.UsuarioUtils;
import co.com.sofkau.model.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Scanner;

import static co.com.sofkau.util.enums.EstadoPrestamo.ESTADO_DOS;


public class ConeccionDB {

    private static final MySqlOperation mySqlOperation = new MySqlOperation();

    public static void comenzarConeccion() throws SQLException{
        openConnection();
        conectarClases();
        closeConnection();
    }



    public static void openConnection() {
        mySqlOperation.setServer(MySqlConstants.SERVER);
        mySqlOperation.setDataBaseName(MySqlConstants.DATA_BASE_NAME);
        mySqlOperation.setUser(MySqlConstants.USER);
        mySqlOperation.setPassword(MySqlConstants.PASSWORD);
    }

    public static void conectarClases(){
        Empleado.setMySqlOperation(mySqlOperation);
        Publicacion.setMySqlOperation(mySqlOperation);
        AreaGenero.setMySqlOperation(mySqlOperation);
        EdadSugerida.setMySqlOperation(mySqlOperation);
        Prestamo.setMySqlOperation(mySqlOperation);
        Usuario.setMySqlOperation(mySqlOperation);
    }


    public static void closeConnection() {
        mySqlOperation.close();
    }
}
