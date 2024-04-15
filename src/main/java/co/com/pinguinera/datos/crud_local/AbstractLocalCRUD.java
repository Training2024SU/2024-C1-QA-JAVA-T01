package co.com.pinguinera.datos.crud_local;

import co.com.pinguinera.datos.interfaces.LocalCRUD;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractLocalCRUD<T> implements LocalCRUD<T> {
    protected List<T> datosLocales = new ArrayList<>();

    @Override
    public void agregar(T objeto) {
        if (!datosLocales.contains(objeto)) {
            datosLocales.add(objeto);
        }
    }

    @Override
    public void actualizar(T objeto) {
        int index = datosLocales.indexOf(objeto);
        if (index != -1) {
            datosLocales.set(index, objeto);
        }
    }

    @Override
    public void eliminar(T objeto) {
        datosLocales.remove(objeto);
    }
}
