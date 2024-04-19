package co.com.training.operaciones.PublicacionesCRUD;

import co.com.training.integration.database.mysql.MySqlOperation;
import co.com.training.models.Publicacion;

import java.sql.SQLException;

public class InsertarPublicacion {

    public static void insertarPublicacion(Publicacion publicacion) throws SQLException {
        MySqlOperation mySqlOperation = new MySqlOperation();
        mySqlOperation.configureDataBaseConnection();
        String insertQuery = "INSERT INTO publicaciones (tituloPublicacion, autorPublicacion, cantidadEjemplares, cantidadPrestados) VALUES (?, ?, ?, ?)";
        mySqlOperation.setSqlStatement(insertQuery);
        mySqlOperation.executeSqlStatementVoid();
        mySqlOperation.close();
    }
}

