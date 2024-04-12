package co.com.training.dao;

import co.com.training.integration.database.mysql.MySqlOperation;
import co.com.training.modelo.Material;
import java.sql.SQLException;

public class MaterialDAO {
    private final MySqlOperation mySqlOperation;

    public MaterialDAO(MySqlOperation mySqlOperation) {
        this.mySqlOperation = mySqlOperation;
    }

    public void insertarMaterial(Material material) {
        String sentencia = String.format("insert into material (id_material, titulo, autor, cantidad_ejemplares, cantidad_prestados) values ('%s', '%s', '%s', %d, %d)",
                material.getId_material(), material.getTitulo(), material.getAutor().getId_autor(), material.getCantidad_ejemplares(), material.getCantidad_prestados());
        mySqlOperation.setSqlStatement(sentencia);
        mySqlOperation.executeSqlStatementVoid();
    }

    public void selectAllFromMaterial() throws SQLException {
        mySqlOperation.setSqlStatement("select * from material");
        mySqlOperation.executeSqlStatement();
        mySqlOperation.printResulset();
    }
}
