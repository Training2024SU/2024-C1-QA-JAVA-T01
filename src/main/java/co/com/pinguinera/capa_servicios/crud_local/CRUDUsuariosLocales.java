package co.com.pinguinera.capa_servicios.crud_local;

import co.com.pinguinera.modelado.Usuario;

import java.util.ArrayList;
import java.util.List;

public class CRUDUsuariosLocales extends AbstractLocalCRUD<Usuario> {
    // El constructor llama al constructor de la clase base
    public CRUDUsuariosLocales() {
        super();
    }

    // Método para obtener todos los usuarios de la lista local
    public List<Usuario> obtenerTodos() {
        // Devuelve la lista completa de usuarios locales
        return new ArrayList<>(datosLocales);
    }
}
