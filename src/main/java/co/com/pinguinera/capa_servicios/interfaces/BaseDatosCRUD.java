package co.com.pinguinera.capa_servicios.interfaces;
import java.sql.SQLException;
import java.util.List;

public interface BaseDatosCRUD<T> {
    void insertar(T elemento) throws SQLException;
    List<T> obtenerTodos() throws SQLException;
    void actualizar(T elemento) throws SQLException;
    void eliminar(int id) throws SQLException;
}
