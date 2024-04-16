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


    public AreaGeneroDAO(GestorBD gestorBD) {
        super(gestorBD);
    }

    @Override
    protected String obtenerConsultaTodos() {
        return CONSULTA_AREAS_GENERO;
    }

    @Override
    protected AreaGenero convertirFilaAObjeto(ResultSet resultSet) throws SQLException {
        return new AreaGenero(
                resultSet.getInt("idPublicacion"),
                resultSet.getString("areaGenero")
        );
    }

    @Override
    public void insertar(AreaGenero areaGenero) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(INSERTAR_AREA_GENERO)) {
            statement.setInt(1, areaGenero.getIdPublicacion());
            statement.setString(2, areaGenero.getAreaGenero());
            statement.executeUpdate();
        }
    }

    @Override
    public void actualizar(AreaGenero areaGenero) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(ACTUALIZAR_AREA_GENERO)) {
            statement.setString(1, areaGenero.getAreaGenero());
            statement.setInt(2, areaGenero.getIdPublicacion());
            statement.executeUpdate();
        }
    }


    @Override
    public void eliminar(AreaGenero areaGenero) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(ELIMINAR_AREA_GENERO)) {
            statement.setInt(1, areaGenero.getIdPublicacion());
            statement.executeUpdate();
        }
    }

}
