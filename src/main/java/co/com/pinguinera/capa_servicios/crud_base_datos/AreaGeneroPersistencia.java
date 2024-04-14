package co.com.pinguinera.capa_servicios.crud_base_datos;
import co.com.pinguinera.capa_datos.AreaGeneroDAO;
import co.com.pinguinera.modelado.AreaGenero;

import java.sql.SQLException;
import java.util.List;

public class AreaGeneroPersistencia extends AbstractBaseDatosCRUD<AreaGenero> {
    private final AreaGeneroDAO areaGeneroDAO;

    public AreaGeneroPersistencia(AreaGeneroDAO areaGeneroDAO) {
        this.areaGeneroDAO = areaGeneroDAO;
    }

    @Override
    public void insertar(AreaGenero areaGenero) throws SQLException {
        // Implementa la lógica para insertar un objeto `AreaGenero` usando `AreaGeneroDAO`
        areaGeneroDAO.insertar(areaGenero);
    }

    @Override
    public List<AreaGenero> obtenerTodos() throws SQLException {
        // Implementa la lógica para obtener todos los objetos `AreaGenero` de la base de datos
        return areaGeneroDAO.obtenerTodos();
    }

    @Override
    public void actualizar(AreaGenero areaGenero) throws SQLException {
        // Implementa la lógica para actualizar un objeto `AreaGenero` usando `AreaGeneroDAO`
        areaGeneroDAO.actualizar(areaGenero);
    }

    @Override
    public void eliminar(int id) throws SQLException {
        // Implementa la lógica para eliminar un objeto `AreaGenero` por ID usando `AreaGeneroDAO`
        areaGeneroDAO.eliminar(id);
    }
}
