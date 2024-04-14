package co.com.pinguinera.capa_servicios.crud_local;

import co.com.pinguinera.modelado.Empleado;

import java.util.ArrayList;
import java.util.List;

public class CRUDEmpleadosLocales extends AbstractLocalCRUD<Empleado> {
    // El constructor llama al constructor de la clase base
    public CRUDEmpleadosLocales() {
        super();
    }

    // Método para obtener todos los empleados de la lista local
    public List<Empleado> obtenerTodos() {
        // Devuelve la lista completa de empleados locales
        return new ArrayList<>(datosLocales);
    }
}
