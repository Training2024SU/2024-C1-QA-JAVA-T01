package co.com.sofkau.integration.database.CrudClases;

import co.com.sofkau.integration.database.mysql.MySqlOperation;
import co.com.sofkau.logica.publicacion.PublicacionUtils;
import co.com.sofkau.model.Publicacion;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudPublicacion {

    public static void consultarTodasPublicaciones(MySqlOperation mySqlOperation) throws SQLException {
        mySqlOperation.setSqlStatement("SELECT * FROM publicacion");
        mySqlOperation.executeSqlStatement();

        ResultSet resultSet = mySqlOperation.getResulset();


        while (resultSet.next()) {

            String titulo = resultSet.getString("titulo");
            String tipoPublicacion = resultSet.getString("tipoPublicacion");
            String autor = resultSet.getString("autor");
            int numPagina = resultSet.getInt("numPagina");
            int cantEjemplares = resultSet.getInt("cantEjemplares");
            int cantprestados = resultSet.getInt("cantprestados");
            int cantDisponible = resultSet.getInt("cantDisponible");

            Publicacion publicacionAgregar = new Publicacion(titulo,tipoPublicacion,autor,numPagina,cantEjemplares,cantprestados,cantDisponible);
            Publicacion.publicaciones.add(publicacionAgregar);
        }

    }

    public static void insertIntoPublicacion(MySqlOperation mySqlOperation, String titulo, String tipoPublicacion, String autor, int numPagina, int cantEjemplares, int cantPrestados) {

        mySqlOperation.setSqlStatement("INSERT INTO publicacion (titulo, tipoPublicacion, autor, numPagina, cantEjemplares, cantPrestados) VALUES (?, ?, ?, ?, ?, ?)");
        try {
            mySqlOperation.executeSqlStatementWithParameters(titulo,tipoPublicacion,autor,numPagina,cantEjemplares,cantPrestados);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Publicacion insertada a la base de datos exitosamente");
    }


    public static void eliminarPublicacion(MySqlOperation mySqlOperation, String titulo){

        mySqlOperation.setSqlStatement("DELETE FROM publicacion WHERE titulo = ?");
        try {
            mySqlOperation.executeSqlStatementWithParameters(titulo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("publicacion eliminada con titulo "+ titulo + " de la base de datos");
    }


    public static void ActualizarPublicacion(MySqlOperation mySqlOperation, String titulo,String autor, int numPagina, int cantEjemplares, int cantPrestados){

        mySqlOperation.setSqlStatement("UPDATE publicacion SET autor = ?, numPagina = ?, cantEjemplares = ?, cantPrestados = ? WHERE titulo = ?");
        try {
            mySqlOperation.executeSqlStatementWithParameters(autor, numPagina, cantEjemplares, cantPrestados, titulo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Libro con el titulo "+ titulo + " actualizado exitosamente");
    }

    public static void nuevaDisponibilidadPrestamoConfirmado(MySqlOperation mySqlOperation,int cantidad, String titulo){

        int nuevaCantPrestados = PublicacionUtils.filtrarPorDisponibilidadTitulo(Publicacion.getPublicaciones(), titulo) - cantidad;

        mySqlOperation.setSqlStatement("UPDATE publicacion SET cantPrestados = ? WHERE titulo = ?");
        try {
            mySqlOperation.executeSqlStatementWithParameters(nuevaCantPrestados, titulo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void nuevaDisponibilidadPrestamoRetornado(MySqlOperation mySqlOperation,int cantidad, String titulo){

        int nuevaCantPrestados = PublicacionUtils.filtrarPorDisponibilidadTitulo(Publicacion.getPublicaciones(), titulo) + cantidad;

        mySqlOperation.setSqlStatement("UPDATE publicacion SET cantPrestados = ? WHERE titulo = ?");
        try {
            mySqlOperation.executeSqlStatementWithParameters(nuevaCantPrestados, titulo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
