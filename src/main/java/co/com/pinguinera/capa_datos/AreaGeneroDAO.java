package co.com.pinguinera.capa_datos;

import co.com.pinguinera.capa_datos.interfaces.GestorBD;
import co.com.pinguinera.modelado.AreaGenero;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AreaGeneroDAO extends AbstractDAO<AreaGenero> {

    private static final String CONSULTA_AREAS_GENERO = "SELECT * FROM AreaGenero";

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
                resultSet.getString("tituloPublicacion"),
                resultSet.getString("areaGenero")
        );
    }
}
