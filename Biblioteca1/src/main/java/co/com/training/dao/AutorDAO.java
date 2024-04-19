package co.com.training.dao;

import co.com.training.integration.database.mysql.MySqlOperation;
import co.com.training.modelo.Autor;

import java.sql.SQLException;

public class AutorDAO {

    private final MySqlOperation mySqlOperation;

    public AutorDAO(MySqlOperation mySqlOperation) {
        this.mySqlOperation = mySqlOperation;
    }

    public void insertarAutor(Autor autor) {
        String sentencia = String.format("insert into autor (id_autor, nombre_autor, apellido_autor) values ('%s', '%s', '%s')",
                autor.getId_autor(), autor.getNombre_autor(), autor.getApellido_autor());
        mySqlOperation.setSqlStatement(sentencia);
        mySqlOperation.executeSqlStatementVoid();
    }

    public void selectAllFromAutor() throws SQLException {
        mySqlOperation.setSqlStatement("select * from autor");
        mySqlOperation.executeSqlStatement();
        mySqlOperation.printResulset();
    }
}
