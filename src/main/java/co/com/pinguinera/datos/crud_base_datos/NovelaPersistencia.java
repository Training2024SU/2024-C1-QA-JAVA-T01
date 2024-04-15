package co.com.pinguinera.datos.crud_base_datos;

import co.com.pinguinera.datos.DAO.NovelaDAO;
import co.com.pinguinera.datos.model.publicaciones.Novela;
import java.sql.SQLException;
import java.util.List;

public class NovelaPersistencia extends AbstractBaseDatosCRUD<Novela> {
    private final NovelaDAO novelaDAO;

    public NovelaPersistencia(NovelaDAO novelaDAO) {
        this.novelaDAO = novelaDAO;
    }

    @Override
    public void insertar(Novela novela) throws SQLException {
        // Implementa la lógica para insertar una novela usando novelaDAO
        novelaDAO.insertar(novela);
    }

    @Override
    public List<Novela> obtenerTodos() throws SQLException {
        // Implementa la lógica para obtener todas las novelas de la base de datos
        return novelaDAO.obtenerTodos();
    }

    @Override
    public void actualizar(Novela novela) throws SQLException {
        // Implementa la lógica para actualizar una novela usando novelaDAO
        novelaDAO.actualizar(novela);
    }

    @Override
    public void eliminar(int id) throws SQLException {
        // Implementa la lógica para eliminar una novela por ID usando novelaDAO
        novelaDAO.eliminar(id);
    }
}
