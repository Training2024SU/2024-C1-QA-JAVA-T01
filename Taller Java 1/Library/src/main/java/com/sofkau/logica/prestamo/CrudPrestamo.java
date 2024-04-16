package com.sofkau.logica.prestamo;

import com.sofkau.model.Prestamo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import static com.sofkau.Main.mySqlOperation;

public class CrudPrestamo {

    private static final String SELECT_PRESTAMO_BY_ID = "select * from mydb.prestamo where idPrestamo = '%s';";
    private static final String UPTADATE_PRESTAMO_ESTADO = "update mydb.prestamo SET Estado = '%s' where idPrestamo = '%s';";

    private static final String INSERT_PRESTAMO = "insert into mydb.prestamo (idPrestamo, Fecha_prestamo, Fecha_devolucion, Estado, correo_usuario, titulo_publicacion) values('%s', '%s', '%s', '%s', '%s', '%s');";

    public static Prestamo findPrestamoById(String idPrestamo) throws SQLException {
        Prestamo prestamo = new Prestamo();
        try {
            mySqlOperation.setSqlStatement(String.format(SELECT_PRESTAMO_BY_ID, idPrestamo));
            mySqlOperation.executeSqlStatement();
            ResultSet resultSet = mySqlOperation.getResulset();
            while (resultSet.next()) {
                prestamo.setIdPrestamo(resultSet.getString(1));
                prestamo.setFechaPrestamo(resultSet.getString(2));
                prestamo.setFechaDevolucion(resultSet.getString(3));
                prestamo.setEstadoPrestamo(resultSet.getString(4));
                prestamo.setCorreoUsuario(resultSet.getString(5));
                prestamo.setTituloPublicacion(resultSet.getString(6));

            }
        } catch (SQLException e) {
            System.out.println("Error al obtener prestamo: " + e.getMessage());
        }
        return prestamo;
    }

    public static void changeStatusPrestamo(String newState, String idPrestamo) {
        mySqlOperation.setSqlStatement(String.format(UPTADATE_PRESTAMO_ESTADO, newState, idPrestamo));
        mySqlOperation.executeSqlStatementVoid();
    }

    public static void createPrestamo(Prestamo prestamo) throws SQLException {
        try {
            mySqlOperation.setSqlStatement(String.format(INSERT_PRESTAMO, prestamo.getIdPrestamo(), prestamo.getFechaPrestamo(), prestamo.getFechaDevolucion(), prestamo.getEstadoPrestamo(), prestamo.getCorreoUsuario(), prestamo.getTituloPublicacion()));
            mySqlOperation.executeSqlStatementVoid();
            System.out.println("Se creo el prestamo con id: " + prestamo.getIdPrestamo());
            System.out.println("La fecha de devolucion es: " + prestamo.getFechaDevolucion());
            System.out.println("Pase por el asistente para que le entrege el libro");
        } catch (Exception e) {
            System.out.println("No se creo el prestamo");
        }

    }
}
