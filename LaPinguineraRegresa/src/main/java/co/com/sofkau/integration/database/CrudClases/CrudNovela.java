package co.com.sofkau.integration.database.CrudClases;

import co.com.sofkau.integration.database.mysql.MySqlOperation;
import co.com.sofkau.model.Publicacion;

import java.sql.ResultSet;
import java.sql.SQLException;

import static co.com.sofkau.util.enums.TipoPublicacion.TIPO_DOS;
import static co.com.sofkau.util.enums.TipoPublicacion.TIPO_UNO;


public class CrudNovela {


    public static void agregarNovela(MySqlOperation mySqlOperation) {


        String titulo = "Alicia a travez del espejo";
        String tipoPublicacion = TIPO_DOS.getvalue(); //no borrar cuando hagas el menu
        String autor = "Lewis Carroll";
        int numPagina = 321;
        int cantEjemplares = 32;
        int cantPrestados = 17;

        String edadSugeridad = "12";

        CrudPublicacion.insertIntoPublicacion(mySqlOperation, titulo, tipoPublicacion, autor, numPagina, cantEjemplares, cantPrestados);
        CrudEdadSugerida.insertIntoEdadSugerida(mySqlOperation,edadSugeridad, titulo );

    }


    public static void eliminarNovelaEdadSugerida(MySqlOperation mySqlOperation, String titulo){

        CrudEdadSugerida.eliminarEdadSugerida(mySqlOperation, titulo);
        CrudPublicacion.eliminarPublicacion(mySqlOperation,titulo);

    }


    public static void actualizarNovelaEdadSugerida(MySqlOperation mySqlOperation , String titulo){

        String autor = "Lewis Carroll";
        int numPagina = 245;
        int cantEjemplares = 20;
        int cantPrestados = 7;

        String edad = "12";

        CrudPublicacion.ActualizarPublicacion(mySqlOperation, titulo, autor, numPagina, cantEjemplares, cantPrestados);
        CrudEdadSugerida.ActualizarEdadSugerida(mySqlOperation,edad, titulo );

    }

}
