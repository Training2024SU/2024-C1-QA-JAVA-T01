package co.com.training.operaciones.PublicacionesCRUD;

import co.com.training.integration.database.mysql.MySqlOperation;
import co.com.training.models.Publicacion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ObtenerPublicacion {

    public static List<Publicacion> obtenerTodasLasPublicaciones() throws SQLException {
        List<Publicacion> publicaciones = new ArrayList<>();
        MySqlOperation mySqlOperation = new MySqlOperation();
        mySqlOperation.configureDataBaseConnection();
        String selectQuery = "SELECT * FROM publicaciones";
        mySqlOperation.setSqlStatement(selectQuery);
        mySqlOperation.executeSqlStatement();
        ResultSet resultSet = mySqlOperation.getResulset();
        while (resultSet.next()) {
            Publicacion publicacion = new Publicacion(
                    resultSet.getString("titulo"),
                    resultSet.getString("autor"),
                    resultSet.getInt("cantidad_ejemplares"),
                    resultSet.getInt("cantidad_prestados")
            );
            publicaciones.add(publicacion);
        }
        mySqlOperation.close();
        return publicaciones;
    }
}
