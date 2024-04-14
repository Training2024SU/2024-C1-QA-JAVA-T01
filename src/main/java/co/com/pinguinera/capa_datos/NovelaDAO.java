package co.com.pinguinera.capa_datos;

import co.com.pinguinera.capa_datos.interfaces.GestorBD;
import co.com.pinguinera.modelado.publicaciones.Novela;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class NovelaDAO extends AbstractDAO<Novela> {

    private static final String CONSULTA_NOVELAS = "SELECT * FROM Publicacion WHERE tipo_publicacion = 'NOVELA'";

    // Constructor que recibe un objeto GestorBD para establecer la conexión
    public NovelaDAO(GestorBD gestorBD) {
        super(gestorBD);
    }

    @Override
    protected String obtenerConsultaTodos() {
        // Devuelve la consulta SQL específica para obtener todas las novelas de la base de datos
        return CONSULTA_NOVELAS;
    }

    @Override
    protected Novela convertirFilaAObjeto(ResultSet resultSet) throws SQLException {
        // Crea un objeto Novela a partir de una fila del ResultSet
        Novela novela = new Novela(
                resultSet.getString("Titulo"),
                resultSet.getString("Autor"),
                resultSet.getInt("Num_paginas"),
                resultSet.getInt("Cant_ejemplares"),
                resultSet.getInt("Cant_prestados"),
                resultSet.getInt("Cant_disponible"),
                null, // áreas
                null  // edades
        );
        // Puedes asignar áreas y edades según lo necesites aquí

        return novela;
    }
}
