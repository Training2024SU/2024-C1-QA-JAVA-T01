package co.com.pinguinera.datos.DAO;

import co.com.pinguinera.datos.interfaces.GestorBD;
import co.com.pinguinera.datos.model.AreaGenero;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AreaGeneroDAO extends AbstractDAO<AreaGenero> {

    private static final String CONSULTA_AREAS_GENERO = "SELECT * FROM AreaGenero";
    private static final String INSERTAR_AREA_GENERO = "INSERT INTO AreaGenero (idPublicacion, areaGenero) VALUES (?, ?)";
    private static final String ACTUALIZAR_AREA_GENERO = "UPDATE AreaGenero SET areaGenero = ? WHERE idPublicacion = ?";
    private static final String ELIMINAR_AREA_GENERO = "DELETE FROM AreaGenero WHERE idPublicacion = ?";

    // Constructor que recibe un objeto GestorBD para establecer la conexión
    public AreaGeneroDAO(GestorBD gestorBD) {
        super(gestorBD);
    }

    @Override
    protected String obtenerConsultaTodos() {
        // Devuelve la consulta SQL específica para obtener todas las áreas y géneros
        return CONSULTA_AREAS_GENERO;
    }

    @Override
    protected AreaGenero convertirFilaAObjeto(ResultSet resultSet) throws SQLException {
        // Crea un objeto AreaGenero a partir de una fila del ResultSet
        return new AreaGenero(
                resultSet.getInt("idPublicacion"), // Cambia a obtener idPublicacion
                resultSet.getString("areaGenero")
        );
    }

    // Método para insertar un nuevo AreaGenero en la base de datos
    @Override
    public void insertar(AreaGenero areaGenero) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(INSERTAR_AREA_GENERO)) {
            statement.setInt(1, areaGenero.getIdPublicacion()); // Cambia a setInt para idPublicacion
            statement.setString(2, areaGenero.getAreaGenero());
            statement.executeUpdate();
        }
    }

    // Método para actualizar un AreaGenero existente en la base de datos
    @Override
    public void actualizar(AreaGenero areaGenero) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(ACTUALIZAR_AREA_GENERO)) {
            statement.setString(1, areaGenero.getAreaGenero());
            statement.setInt(2, areaGenero.getIdPublicacion()); // Cambia a setInt para idPublicacion
            statement.executeUpdate();
        }
    }

    // Método para eliminar un AreaGenero de la base de datos
    @Override
    public void eliminar(int idPublicacion) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(ELIMINAR_AREA_GENERO)) {
            statement.setInt(1, idPublicacion); // Cambia a setInt para idPublicacion
            statement.executeUpdate();
        }
    }

}
