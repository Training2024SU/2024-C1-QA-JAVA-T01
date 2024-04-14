package co.com.pinguinera.capa_datos;

import co.com.pinguinera.capa_datos.interfaces.GestorBD;
import co.com.pinguinera.modelado.EdadSugerida;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EdadSugeridaDAO extends AbstractDAO<EdadSugerida> {

    private static final String CONSULTA_EDADES_SUGERIDAS = "SELECT * FROM EdadSugerida";

    // Constructor que recibe un objeto GestorBD para establecer la conexión
    public EdadSugeridaDAO(GestorBD gestorBD) {
        super(gestorBD);
    }

    @Override
    protected String obtenerConsultaTodos() {
        // Devuelve la consulta SQL específica para obtener todas las edades sugeridas
        return CONSULTA_EDADES_SUGERIDAS;
    }

    @Override
    protected EdadSugerida convertirFilaAObjeto(ResultSet resultSet) throws SQLException {
        // Crea un objeto EdadSugerida a partir de una fila del ResultSet
        EdadSugerida edadSugerida = new EdadSugerida(
                resultSet.getString("tituloPublicacion"),
                resultSet.getString("edad")
        );
        return edadSugerida;
    }
}
