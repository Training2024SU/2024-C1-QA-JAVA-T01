package co.com.pinguinera.capa_servicios;

import co.com.pinguinera.modelado.Empleado;

import java.util.ArrayList;
import java.util.List;

public class ServicioCRUDEmpleado {
    private List<Empleado> empleados; // Colección local para almacenar los empleados

    public ServicioCRUDEmpleado() {
        this.empleados = new ArrayList<>(); // Inicializa la colección local
    }

    // Método para agregar un nuevo empleado a la colección local
    public void agregar(Empleado empleado) {
        empleados.add(empleado);
    }

    // Método para obtener todos los empleados desde la colección local
    public List<Empleado> obtenerTodos() {
        return empleados;
    }

    // Método para actualizar un empleado existente en la colección local
    public void actualizar(Empleado empleado) {
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getIdEmpleado() == empleado.getIdEmpleado()) {
                empleados.set(i, empleado);
                break;
            }
        }
    }

    // Método para eliminar un empleado de la colección local
    public void eliminar(int idEmpleado) {
        empleados.removeIf(empleado -> empleado.getIdEmpleado() == idEmpleado);
    }
}

