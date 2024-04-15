package co.com.pinguinera.datos.crud_base_datos;

import co.com.pinguinera.datos.DAO.PrestamoDAO;
import co.com.pinguinera.datos.model.Prestamo;

import java.sql.SQLException;
import java.util.List;

public class PrestamoPersistencia extends AbstractBaseDatosCRUD<Prestamo> {
    private final PrestamoDAO prestamoDAO;

    public PrestamoPersistencia(PrestamoDAO prestamoDAO) {
        this.prestamoDAO = prestamoDAO;
    }

    @Override
    public void insertar(Prestamo prestamo) throws SQLException {
        prestamoDAO.insertar(prestamo);
    }

    @Override
    public List<Prestamo> obtenerTodos() throws SQLException {
        return prestamoDAO.obtenerTodos();
    }

    @Override
    public void actualizar(Prestamo prestamo) throws SQLException {
        prestamoDAO.actualizar(prestamo);
    }

    @Override
    public void eliminar(int id) throws SQLException {
        prestamoDAO.eliminar(id);
    }
}
