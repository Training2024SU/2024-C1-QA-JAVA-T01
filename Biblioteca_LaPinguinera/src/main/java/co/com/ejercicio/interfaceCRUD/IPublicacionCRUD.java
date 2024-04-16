package co.com.ejercicio.interfaceCRUD;

import co.com.ejercicio.modelo.Publicacion;

import java.sql.SQLException;
import java.util.List;

public interface IPublicacionCRUD {
    public void insertarPublicacion(Publicacion publicacion) throws SQLException;
    public void actualizarPublicacion(Publicacion publicacion) throws SQLException;
    public void eliminarPublicacion(String titulo) throws SQLException;
    public List<Publicacion> obtenerTodasLasPublicaciones() throws SQLException;
}
