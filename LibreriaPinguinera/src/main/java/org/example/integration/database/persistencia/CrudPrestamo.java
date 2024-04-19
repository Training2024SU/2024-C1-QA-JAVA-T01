package org.example.integration.database.persistencia;

import org.example.model.Prestamo;
import org.example.model.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.example.integration.database.mysql.MySqlConstants.*;
import static org.example.integration.database.persistencia.CrudUsuario.mySqlOperation;
import static org.example.logica.control.ConstantesLogica.TIPO_USUARIO;
import static org.example.model.Prestamo.prestamos;
import static org.example.model.Usuario.usuarios;

public class CrudPrestamo {
    public void seleccionarDatos() throws SQLException {
        mySqlOperation.setSqlStatement(String.format(SELECT, PRESTAMO));
        mySqlOperation.executeSqlStatement();
        ResultSet resultSet=mySqlOperation.getResulset();
        llenarUsuarios(resultSet);
    }
    public int crearEntidad(Prestamo prestamo) {
        return mySqlOperation.executeSqlStatementVoidForKeys(String.format(CREATE_PRESTAMO,prestamo.getFechaPrestamo(), prestamo.getFechaDevolucion(),
                prestamo.getEstadoPrestamo(), prestamo.getCorreoUsuario(), prestamo.getTituloPublicacion()));
    }
    public void actualizarPrestamo(Prestamo prestamo){
        mySqlOperation.executeSqlStatementVoid(String.format(UPDATE_PRESTAMO, prestamo.getFechaPrestamo(), prestamo.getFechaDevolucion(),
                prestamo.getEstadoPrestamo(), prestamo.getTituloPublicacion(), prestamo.getId()));
    }
    private void llenarUsuarios(ResultSet resultSet) throws SQLException {
        while (resultSet.next()){
            int idPrestamo=resultSet.getInt("idPrestamo");
            LocalDate fechaPrestamo= resultSet.getDate("Fecha_prestamo").toLocalDate();
            LocalDate fechaDevolucion= resultSet.getDate("Fecha_devolucion").toLocalDate();
            String estado=resultSet.getString("estado");
            String correo=resultSet.getString("correo_usuario");
            String titulo=resultSet.getString("titulo_publicacion");

            Prestamo prestamo=new Prestamo(idPrestamo, fechaPrestamo, fechaDevolucion, estado, correo, titulo);
            prestamos.add(prestamo);
        }
    }

}
