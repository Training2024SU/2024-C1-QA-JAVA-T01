package com.sofkau.integration.database.ClasesDB;

import com.sofkau.integration.database.mysql.MySqlOperation;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Objects;

public class Prestamos {
    private static final MySqlOperation mySqlOperation = new MySqlOperation();

    public static void nuevoPrestamo() {

        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //String fechaNacimiento = dateFormat.format(faker.date().birthday());

        Date fechaEntrega = Date.valueOf("2024-04-11");
        Date fechaDevolucion = Date.valueOf("2024-04-26");
        String EstadoPrestamo = "Realizado";
        String Usuario_correo = "jorge@gmail.com";
        String novelas_publicacion_titulo = "NA";
        String libros_publicacion_titulo = "Alicia a travez del espejo";


        mySqlOperation.setSqlStatement("INSERT INTO prestamo (fechaEntrega, fechaDevolucion, EstadoPrestamo, Usuario_correo, novelas_publicacion_titulo, libros_publicacion_titulo) VALUES (?, ?, ?, ?, ?, ?)");
        try {
            mySqlOperation.executeSqlStatementWithParameters(fechaEntrega,fechaDevolucion,EstadoPrestamo,Usuario_correo,novelas_publicacion_titulo,libros_publicacion_titulo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Prestamo Solicitado Exitosamente");
    }

    public static void actualizarEstadoPrestamo(String EstadoPrestamo) {


        mySqlOperation.setSqlStatement("UPDATE prestamo SET EstadoPrestamo = ? WHERE id = ?");
        try {
            mySqlOperation.executeSqlStatementWithParameters(EstadoPrestamo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Estado del prestamo " + EstadoPrestamo + "actualizado correctamente");

        if (Objects.equals(EstadoPrestamo, "Finalizado")){

        }
    }

    public static void consultarPrestamo(String correo) throws SQLException {

        mySqlOperation.setSqlStatement("SELECT * FROM prestamo WHERE Usuario_correo = ?");
        try {
            mySqlOperation.executeSqlStatementWithParametersConsult(correo);
            mySqlOperation.printResulset();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
