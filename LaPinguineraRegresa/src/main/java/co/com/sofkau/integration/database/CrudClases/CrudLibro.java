package co.com.sofkau.integration.database.CrudClases;

import co.com.sofkau.integration.database.mysql.MySqlOperation;
import co.com.sofkau.model.Publicacion;

import java.security.spec.RSAOtherPrimeInfo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import static co.com.sofkau.util.enums.TipoPublicacion.TIPO_UNO;


public class CrudLibro {



    public static void agregarLibro(MySqlOperation mySqlOperation) {


        String titulo = "Alicia y la camara secreta";
        String tipoPublicacion = TIPO_UNO.getvalue(); //no borrar cuando hagas el menu
        String autor = "Lewis Carroll";
        int numPagina = 484;
        int cantEjemplares = 45;
        int cantPrestados = 45;

        String areaGenero = "Matematicas";

        CrudPublicacion.insertIntoPublicacion(mySqlOperation, titulo, tipoPublicacion, autor, numPagina, cantEjemplares, cantPrestados);
        CrudAreaGenero.insertIntoAreaGenero(mySqlOperation,areaGenero, titulo );

    }


    public static void eliminarLibroAreaGenero(MySqlOperation mySqlOperation, String titulo){

        CrudAreaGenero.eliminarAreaGenero(mySqlOperation, titulo);
        CrudPublicacion.eliminarPublicacion(mySqlOperation,titulo);

    }

    public static void actualizarLibroAreaGenero(MySqlOperation mySqlOperation , String titulo){

        String autor = "Lewis Carroll";
        int numPagina = 245;
        int cantEjemplares = 20;
        int cantPrestados = 7;

        String areaGenero = "historia";

        CrudPublicacion.ActualizarPublicacion(mySqlOperation, titulo, autor, numPagina, cantEjemplares, cantPrestados);
        CrudAreaGenero.ActualizarAreaGenero(mySqlOperation, areaGenero, titulo);

    }




}
