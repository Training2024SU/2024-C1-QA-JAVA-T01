package co.com.pinguinera.datos.interfaces;

public interface LocalCRUD<T> {
    void agregar(T objeto);
    void actualizar(T objeto);
    void eliminar(T objeto);
}
