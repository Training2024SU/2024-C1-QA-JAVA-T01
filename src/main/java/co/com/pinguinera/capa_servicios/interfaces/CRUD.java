package co.com.pinguinera.capa_servicios.interfaces;

public interface CRUD<T> {
    void agregar(T objeto);
    void actualizar(T objeto);
    void eliminar(T objeto);
}
