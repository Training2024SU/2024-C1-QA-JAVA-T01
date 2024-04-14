package co.com.pinguinera.capa_servicios.CRUBBD;

import co.com.pinguinera.capa_servicios.interfaces.BaseDatosCRUD;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractBaseDatosCRUD<T> implements BaseDatosCRUD<T> {

    @Override
    public abstract void insertar(T elemento) throws SQLException;

    @Override
    public abstract List<T> obtenerTodos() throws SQLException;

    @Override
    public abstract void actualizar(T elemento) throws SQLException;

    @Override
    public abstract void eliminar(int id) throws SQLException;
}
