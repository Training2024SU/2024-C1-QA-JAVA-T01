package co.com.training.operaciones.LibrosCRUD;

import co.com.training.integration.database.mysql.MySqlOperation;
import co.com.training.models.Libro;

import java.sql.SQLException;

public class InsertarLibro {

    public static void insertarLibro(Libro libro) throws SQLException {
        MySqlOperation mySqlOperation = new MySqlOperation();
        mySqlOperation.configureDataBaseConnection();

        String insertQuery = String.format("INSERT INTO libros (tituloPublicacion, autorPublicacion, cantidadEjemplares, cantidadPrestados, areaConocimiento, numeroPaginas) VALUES ('%s', '%s', %d, %d, '%s', %d)",
                libro.getTituloPublicacion(), libro.getAutorPublicacion(), libro.getCantidadEjemplares(), libro.getCantidadPrestados(), libro.getAreaConocimiento(), libro.getNumeroPaginas());

        mySqlOperation.setSqlStatement(insertQuery);
        mySqlOperation.executeSqlStatementVoid();

        mySqlOperation.close();
    }
}

