package co.com.pinguinera.capa_datos;

import co.com.pinguinera.capa_datos.interfaces.GestorBD;
import co.com.pinguinera.modelado.EdadSugerida;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EdadSugeridaDAO extends AbstractDAO<EdadSugerida> {

    private static final String CONSULTA_EDADES_SUGERIDAS = "SELECT * FROM EdadSugerida";
    private static final String INSERTAR_EDAD_SUGERIDA = "INSERT INTO EdadSugerida (idPublicacion, edad) VALUES (?, ?)";
    private static final String ACTUALIZAR_EDAD_SUGERIDA = "UPDATE EdadSugerida SET edad = ? WHERE idPublicacion = ?";
    private static final String ELIMINAR_EDAD_SUGERIDA = "DELETE FROM EdadSugerida WHERE idPublicacion = ?";

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
                resultSet.getInt("idPublicacion"), // Cambiar a obtener idPublicacion
                resultSet.getString("edad")
        );
        return edadSugerida;
    }

    // Método para insertar una nueva edad sugerida en la base de datos
    @Override
    public void insertar(EdadSugerida edadSugerida) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(INSERTAR_EDAD_SUGERIDA)) {
            // Usar setInt para idPublicacion
            statement.setInt(1, edadSugerida.getIdPublicacion());
            statement.setString(2, edadSugerida.getEdad());
            statement.executeUpdate();
        }
    }

    // Método para actualizar una edad sugerida existente en la base de datos
    @Override
    public void actualizar(EdadSugerida edadSugerida) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(ACTUALIZAR_EDAD_SUGERIDA)) {
            statement.setString(1, edadSugerida.getEdad());
            // Usar setInt para idPublicacion
            statement.setInt(2, edadSugerida.getIdPublicacion());
            statement.executeUpdate();
        }
    }

    // Método para eliminar una edad sugerida de la base de datos
    @Override
    public void eliminar(int idPublicacion) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(ELIMINAR_EDAD_SUGERIDA)) {
            // Usar setInt para un parámetro de tipo int
            statement.setInt(1, idPublicacion);
            statement.executeUpdate();
        }
    }

    @Override
    public int obtenerId(EdadSugerida edadSugerida) throws SQLException {
        // Devuelve el ID de la publicación asociada a la edad sugerida
        return edadSugerida.getIdPublicacion();
    }
}
