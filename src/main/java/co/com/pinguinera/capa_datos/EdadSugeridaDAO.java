package co.com.pinguinera.capa_datos;

import co.com.pinguinera.capa_datos.conexionBD.ConexionBD;
import co.com.pinguinera.modelado.EdadSugerida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EdadSugeridaDAO {
    private static final String CONSULTA_EDADES_SUGERIDAS = "SELECT * FROM EdadSugerida";

    public List<EdadSugerida> obtenerTodasLasEdadesSugeridas() throws SQLException {
        List<EdadSugerida> edadesSugeridas = new ArrayList<>();

        Connection conexion = ConexionBD.conectar();

        try (PreparedStatement statement = conexion.prepareStatement(CONSULTA_EDADES_SUGERIDAS);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                EdadSugerida edadSugerida = new EdadSugerida(
                        resultSet.getString("tituloPublicacion"),
                        resultSet.getString("edad")
                );

                // Añadir la edad sugerida a la lista
                edadesSugeridas.add(edadSugerida);
            }
        }

        return edadesSugeridas;
    }
}
