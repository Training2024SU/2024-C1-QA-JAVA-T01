package co.com.pinguinera.DAO;

import co.com.pinguinera.DataBase;
import co.com.pinguinera.modelos.AreaGenero;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AreaGeneroDAO {
    private static final String CONSULTA_AREAS_GENERO = "SELECT * FROM AreaGenero";

    public List<AreaGenero> obtenerTodasLasAreasGeneros() throws SQLException {
        List<AreaGenero> areasGeneros = new ArrayList<>();

        Connection conexion = DataBase.conectar();

        try (PreparedStatement statement = conexion.prepareStatement(CONSULTA_AREAS_GENERO);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                AreaGenero areaGenero = new AreaGenero(
                        resultSet.getString("tituloPublicacion"),
                        resultSet.getString("areaGenero")
                );

                // Añadir el área o género a la lista
                areasGeneros.add(areaGenero);
            }
        }

        return areasGeneros;
    }
}
