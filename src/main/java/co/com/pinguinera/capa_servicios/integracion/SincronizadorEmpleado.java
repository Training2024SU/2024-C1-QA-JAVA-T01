package co.com.pinguinera.capa_servicios.integracion;

import co.com.pinguinera.capa_servicios.crud_base_datos.EmpleadoPersistencia;
import co.com.pinguinera.capa_servicios.crud_local.CRUDEmpleadosLocales;
import co.com.pinguinera.modelado.Empleado;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SincronizadorEmpleado {

    private final EmpleadoPersistencia empleadoPersistencia;
    private final CRUDEmpleadosLocales crudEmpleadosLocales;

    public SincronizadorEmpleado(EmpleadoPersistencia empleadoPersistencia, CRUDEmpleadosLocales crudEmpleadosLocales) {
        this.empleadoPersistencia = empleadoPersistencia;
        this.crudEmpleadosLocales = crudEmpleadosLocales;
    }

    public void sincronizarEmpleados() throws SQLException {
        // Obtén la lista de empleados de la base de datos
        List<Empleado> empleadosBD = empleadoPersistencia.obtenerTodos();

        // Obtén la lista de empleados locales
        List<Empleado> empleadosLocales = crudEmpleadosLocales.obtenerTodos();

        // Crea un mapa de empleados locales para búsquedas rápidas
        Map<Integer, Empleado> mapaEmpleadosLocales = new HashMap<>();
        for (Empleado empleadoLocal : empleadosLocales) {
            mapaEmpleadosLocales.put(empleadoLocal.getIdEmpleado(), empleadoLocal);
        }

        // Actualiza empleados en la base de datos basados en la lista local
        for (Empleado empleadoBD : empleadosBD) {
            Empleado empleadoLocal = mapaEmpleadosLocales.get(empleadoBD.getIdEmpleado());
            if (empleadoLocal != null) {
                // Si el empleado local está en la base de datos, actualízalo si hay diferencias
                if (!empleadoLocal.equals(empleadoBD)) {
                    empleadoPersistencia.actualizar(empleadoLocal);
                }
                // Elimina el empleado del mapa para que al final sepamos cuáles no estaban en la base de datos
                mapaEmpleadosLocales.remove(empleadoLocal.getIdEmpleado());
            }
        }

        // Inserta empleados locales que no están en la base de datos
        for (Empleado empleadoLocal : mapaEmpleadosLocales.values()) {
            empleadoPersistencia.insertar(empleadoLocal);
        }
    }
}
