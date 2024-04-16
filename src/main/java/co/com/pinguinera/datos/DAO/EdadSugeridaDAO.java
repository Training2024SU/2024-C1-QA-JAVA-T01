package co.com.pinguinera.datos.DAO;

import co.com.pinguinera.datos.interfaces.GestorBD;
import co.com.pinguinera.datos.model.EdadSugerida;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EdadSugeridaDAO extends AbstractDAO<EdadSugerida> {

    private static final String CONSULTA_EDADES_SUGERIDAS = "SELECT * FROM EdadSugerida";
    private static final String INSERTAR_EDAD_SUGERIDA = "INSERT INTO EdadSugerida (idPublicacion, edad) VALUES (?, ?)";
    private static final String ACTUALIZAR_EDAD_SUGERIDA = "UPDATE EdadSugerida SET edad = ? WHERE idPublicacion = ?";
    private static final String ELIMINAR_EDAD_SUGERIDA = "DELETE FROM EdadSugerida WHERE idPublicacion = ?";

    public EdadSugeridaDAO(GestorBD gestorBD) {
        super(gestorBD);
    }

    @Override
    protected String obtenerConsultaTodos() {
        return CONSULTA_EDADES_SUGERIDAS;
    }

    @Override
    protected EdadSugerida convertirFilaAObjeto(ResultSet resultSet) throws SQLException {
        EdadSugerida edadSugerida = new EdadSugerida(
                resultSet.getInt("idPublicacion"), // Cambiar a obtener idPublicacion
                resultSet.getString("edad")
        );
        return edadSugerida;
    }


    @Override
    public void insertar(EdadSugerida edadSugerida) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(INSERTAR_EDAD_SUGERIDA)) {
            statement.setInt(1, edadSugerida.getIdPublicacion());
            statement.setString(2, edadSugerida.getEdad());
            statement.executeUpdate();
        }
    }

    @Override
    public void actualizar(EdadSugerida edadSugerida) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(ACTUALIZAR_EDAD_SUGERIDA)) {
            statement.setString(1, edadSugerida.getEdad());
            statement.setInt(2, edadSugerida.getIdPublicacion());
            statement.executeUpdate();
        }
    }

    @Override
    public void eliminar(EdadSugerida edadSugerida) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(ELIMINAR_EDAD_SUGERIDA)) {
            statement.setInt(1, edadSugerida.getIdPublicacion());
            statement.executeUpdate();
        }
    }


}
