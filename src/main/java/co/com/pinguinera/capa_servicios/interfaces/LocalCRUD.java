package co.com.pinguinera.capa_servicios.interfaces;

public interface LocalCRUD<T> {
    void agregar(T objeto);
    void actualizar(T objeto);
    void eliminar(T objeto);
}
