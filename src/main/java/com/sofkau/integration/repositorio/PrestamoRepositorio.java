package com.sofkau.integration.repositorio;

import com.sofkau.integration.database.ConexionDatabase;
import com.sofkau.integration.database.mysql.MySqlOperation;
import com.sofkau.model.Prestamo;
import com.sofkau.util.CommonOperacion.IngresoQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class PrestamoRepositorio {

    private static MySqlOperation mySqlOperation = ConexionDatabase.getMySqlOperation();

    public static void crearPrestamo(Prestamo prestamo) {
        String query = String.format("INSERT INTO Prestamo (id_prestamo, fecha_prestamo, fecha_devolucion, estado, correo_usuario, titulo_publicacion) VALUES ('%s', '%s', '%s', '%s', '%s', '%s')",
                prestamo.getId(),
                new java.sql.Date(prestamo.getFechaPrestamo().getTime()),
                new java.sql.Date(prestamo.getFechaDevolucion().getTime()),
                prestamo.getEstadoPrestamo(),
                prestamo.getCorreoUsuario(),
                prestamo.getTituloPublicacion());
        IngresoQuery.ejecutarIngresoQuery(query);
    }

    public static HashMap<String, Prestamo> consultarPrestamos() {
        String query = "SELECT * FROM Prestamo";
        IngresoQuery.ejecutarConsultaQuery(query);
        ResultSet resultSet = mySqlOperation.getResulset();

        HashMap<String, Prestamo> prestamos = new HashMap<>();

        try {
            while (resultSet.next()) {
                String id = resultSet.getString("id_prestamo");
                String fechaPrestamoStr = resultSet.getString("fecha_prestamo");
                String fechaDevolucionStr = resultSet.getString("fecha_devolucion");
                String estado = resultSet.getString("estado");
                String correoUsuario = resultSet.getString("correo_usuario");
                String tituloPublicacion = resultSet.getString("titulo_publicacion");

                // Convertir las cadenas de fecha a objetos Date
                // Date fechaPrestamo = convertirAFecha(fechaPrestamoStr);
                // Date fechaDevolucion = convertirAFecha(fechaDevolucionStr);

                Prestamo prestamo = new Prestamo();
                prestamo.setId(id);
                // Asignar las demás propiedades al objeto prestamo

                prestamos.put(id, prestamo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return prestamos;
    }
}
