package co.com.training.operaciones.PublicacionesCRUD;

import co.com.training.integration.database.mysql.MySqlOperation;

import java.sql.SQLException;

public class EliminarPublicacion {

    public static void eliminarPublicacion(int idPublicacion) throws SQLException {
        MySqlOperation mySqlOperation = new MySqlOperation();
        mySqlOperation.configureDataBaseConnection();
        String deleteQuery = "DELETE FROM publicaciones WHERE id=?";
        mySqlOperation.setSqlStatement(deleteQuery);
        mySqlOperation.executeSqlStatementVoid();
        mySqlOperation.close();
    }
}

