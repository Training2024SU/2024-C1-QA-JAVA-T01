package co.com.training.dao;

import co.com.training.integration.database.mysql.MySqlOperation;
import co.com.training.modelo.Libro;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LibroDAO {
    private final MySqlOperation mySqlOperation;

    public LibroDAO(MySqlOperation mySqlOperation) {
        this.mySqlOperation = mySqlOperation;
    }



    private void insertarLibroEnBd(Libro libro) throws SQLException {
        // Crear una instancia de LibroDAO
        LibroDAO libroDAO = new LibroDAO(mySqlOperation);

        // Insertar el libro en la base de datos
        libroDAO.agregarLibro(libro);
    }

    public void agregarLibro(Libro libro) {
    }
}
