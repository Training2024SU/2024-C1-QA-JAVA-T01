package co.com.sofkau.integration.database.CrudClases;

import co.com.sofkau.integration.database.mysql.MySqlOperation;
import co.com.sofkau.model.Prestamo;
import co.com.sofkau.model.Publicacion;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static co.com.sofkau.util.enums.EstadoPrestamo.*;

public class CrudPrestamo {


    public static void nuevoPrestamo(MySqlOperation mySqlOperation, LocalDate fechaPrestamo) {

        LocalDate fechaDevolucion = fechaPrestamo.plusDays(15);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaPrestamoStr = fechaPrestamo.format(formatter);
        String fechaDevolucionStr = fechaDevolucion.format(formatter);

        String estado = ESTADO_UNO.getvalue(); // no borrar después
        String usuarioCorreo = "ana@example.com";
        String publicacionTitulo = "Alicia y la camara secreta";

        mySqlOperation.setSqlStatement("INSERT INTO prestamo (fechaPrestamo, fechaDevolucion, estado, Usuario_correo, Publicacion_titulo) VALUES (?, ?, ?, ?, ?)");
        try {
            mySqlOperation.executeSqlStatementWithParameters(fechaPrestamoStr, fechaDevolucionStr, estado, usuarioCorreo, publicacionTitulo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Prestamo Solicitado Exitosamente");
    }


    public static void consultarTodosPrestamos(MySqlOperation mySqlOperation) throws SQLException{
        mySqlOperation.setSqlStatement("SELECT * FROM prestamo");
        mySqlOperation.executeSqlStatement();

        ResultSet resultSet = mySqlOperation.getResulset();


        while (resultSet.next()) {

            int idPrestamo = resultSet.getInt("idPrestamo");
            String fechaPrestamo = resultSet.getString("fechaPrestamo");
            String fechaDevolucion = resultSet.getString("fechaDevolucion");
            String estado = resultSet.getString("estado");
            String Usuario_correo = resultSet.getString("Usuario_correo");
            String Publicacion_titulo = resultSet.getString("Publicacion_titulo");


            Prestamo prestamoAgregar = new Prestamo(idPrestamo,fechaPrestamo,fechaDevolucion,estado,Usuario_correo,Publicacion_titulo);

            Prestamo.prestamos.add(prestamoAgregar);
        }
    }

    public static void eliminarPrestamo(MySqlOperation mySqlOperation, int idPrestamo ){

        mySqlOperation.setSqlStatement("DELETE FROM prestamo WHERE idPrestamo = ?");
        try {
            mySqlOperation.executeSqlStatementWithParameters(idPrestamo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Prestamo eliminado con ID "+ idPrestamo + " de la base de datos");
    }

    public static void actualizarEstadoEntregado(MySqlOperation mySqlOperation,  int ID) {

        String estado = ESTADO_DOS.getvalue();

        actualizarEstado(mySqlOperation, ID, estado);
        System.out.println("Estado del prestamo a " + estado + " actualizado correctamente");

    }

    public static void actualizarEstadoRetornado(MySqlOperation mySqlOperation,  int ID) {

        String estado = ESTADO_TRES.getvalue();

        actualizarEstado(mySqlOperation, ID, estado);
        System.out.println("Estado del prestamo a " + estado + " actualizado correctamente");
    }

    private static void actualizarEstado(MySqlOperation mySqlOperation, int ID, String estado) {
        mySqlOperation.setSqlStatement("UPDATE prestamo SET estado = ? WHERE idPrestamo = ?");
        try {
            mySqlOperation.executeSqlStatementWithParameters(estado, ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
