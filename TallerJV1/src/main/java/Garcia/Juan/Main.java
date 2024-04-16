package Garcia.Juan;

import Garcia.Juan.CRUD.CrudPrestamo;
import Garcia.Juan.CRUD.CrudProducto;
import Garcia.Juan.CRUD.CrudUsuario;
import Garcia.Juan.logica.*;
import Garcia.Juan.database.ConexionBD;

import Garcia.Juan.database.mysql.MySqlOperation;

import java.sql.SQLException;
import java.text.ParseException;

import static Garcia.Juan.database.ConexionBD.*;
import static Garcia.Juan.dialogo.Menu.*;
import static Garcia.Juan.logica.MetodosMain.iniciarPrograma;

public class Main {

    public static final MySqlOperation mySqlOperation = new MySqlOperation();

    public static void main(String[] args) throws SQLException, ParseException {
        openConnection(mySqlOperation);
        ConexionBD conexion = new ConexionBD(mySqlOperation);
        MetodosMain metodosMain = new MetodosMain(mySqlOperation);
        CrudUsuario crudUsuario = new CrudUsuario(mySqlOperation);
        CrudProducto crudProducto = new CrudProducto(mySqlOperation);
        CrudPrestamo crudPrestamo = new CrudPrestamo(mySqlOperation);
        MetodosUsuario metodosUsuario = new MetodosUsuario(mySqlOperation);
        MetodosProducto metodosProducto = new MetodosProducto(mySqlOperation);
        MetodosPrestamo metodosPrestamo = new MetodosPrestamo(mySqlOperation);
        SesionIniciada sesionIniciada = new SesionIniciada(mySqlOperation);
        iniciarPrograma(mySqlOperation);
        closeConnection(mySqlOperation);
        loggerFinal();
    }
}
