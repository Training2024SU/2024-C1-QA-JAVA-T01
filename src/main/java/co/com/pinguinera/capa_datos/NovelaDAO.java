package co.com.pinguinera.capa_datos;

import co.com.pinguinera.capa_datos.interfaces.GestorBD;
import co.com.pinguinera.modelado.publicaciones.Novela;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NovelaDAO extends AbstractDAO<Novela> {

    private static final String CONSULTA_NOVELAS = "SELECT * FROM Publicacion WHERE tipo_publicacion = 'NOVELA'";
    private static final String INSERTAR_NOVELA = "INSERT INTO Publicacion (idPublicacion, Titulo, tipo_publicacion, Autor, Num_paginas, Cant_ejemplares, Cant_prestados) VALUES (?, ?, 'NOVELA', ?, ?, ?, ?)";
    private static final String ACTUALIZAR_NOVELA = "UPDATE Publicacion SET Titulo = ?, Autor = ?, Num_paginas = ?, Cant_ejemplares = ?, Cant_prestados = ? WHERE idPublicacion = ?";
    private static final String ELIMINAR_NOVELA = "DELETE FROM Publicacion WHERE idPublicacion = ?";

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
        Novela novela = new Novela();
        novela.setIdPublicacion(resultSet.getInt("idPublicacion")); // Ajuste para incluir idPublicacion
        novela.setTitulo(resultSet.getString("Titulo"));
        novela.setAutor(resultSet.getString("Autor"));
        novela.setNumPaginas(resultSet.getInt("Num_paginas"));
        novela.setCantEjemplares(resultSet.getInt("Cant_ejemplares"));
        novela.setCantPrestados(resultSet.getInt("Cant_prestados"));

        // `cant_disponible` es un campo calculado, se ajusta automáticamente en la base de datos
        // pero lo tomamos del resultado para tener la información actualizada
        novela.setCantDisponible(resultSet.getInt("Cant_disponible"));

        return novela;
    }

    // Implementación de los métodos CRUD

    // Método para insertar una nueva novela en la base de datos
    @Override
    public void insertar(Novela novela) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(INSERTAR_NOVELA)) {
            statement.setInt(1, novela.getIdPublicacion());
            statement.setString(2, novela.getTitulo());
            statement.setString(3, novela.getAutor());
            statement.setInt(4, novela.getNumPaginas());
            statement.setInt(5, novela.getCantEjemplares());
            statement.setInt(6, novela.getCantPrestados());
            statement.executeUpdate();
        }
    }

    // Método para actualizar una novela existente en la base de datos
    @Override
    public void actualizar(Novela novela) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(ACTUALIZAR_NOVELA)) {
            statement.setString(1, novela.getTitulo());
            statement.setString(2, novela.getAutor());
            statement.setInt(3, novela.getNumPaginas());
            statement.setInt(4, novela.getCantEjemplares());
            statement.setInt(5, novela.getCantPrestados());
            statement.setInt(6, novela.getIdPublicacion());
            statement.executeUpdate();
        }
    }

    // Método para eliminar una novela de la base de datos
    @Override
    public void eliminar(int idPublicacion) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(ELIMINAR_NOVELA)) {
            statement.setInt(1, idPublicacion);
            statement.executeUpdate();
        }
    }

}
