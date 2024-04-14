package co.com.pinguinera.capa_datos;

import co.com.pinguinera.capa_datos.interfaces.GestorBD;
import co.com.pinguinera.modelado.publicaciones.Libro;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LibroDAO extends AbstractDAO<Libro> {

    private static final String CONSULTA_LIBROS = "SELECT * FROM Publicacion WHERE tipo_publicacion = 'LIBRO'";

    // Constructor que recibe un objeto GestorBD para establecer la conexión
    public LibroDAO(GestorBD gestorBD) {
        super(gestorBD);
    }

    @Override
    protected String obtenerConsultaTodos() {
        // Devuelve la consulta SQL específica para obtener todos los libros de la base de datos
        return CONSULTA_LIBROS;
    }

    @Override
    protected Libro convertirFilaAObjeto(ResultSet resultSet) throws SQLException {
        // Crea un objeto Libro a partir de una fila del ResultSet
        Libro libro = new Libro(
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

        return libro;
    }
}
