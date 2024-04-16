package co.com.pinguinera.servicios.integracion;

import co.com.pinguinera.datos.DAO.EmpleadoDAO;
import co.com.pinguinera.datos.crud_local.CRUDEmpleadosLocales;
import co.com.pinguinera.datos.model.Empleado;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SincronizadorEmpleado {
    private final EmpleadoDAO empleadoDAO;
    private final CRUDEmpleadosLocales crudEmpleadosLocales;

    public SincronizadorEmpleado(EmpleadoDAO empleadoDAO, CRUDEmpleadosLocales crudEmpleadosLocales) {
        this.empleadoDAO = empleadoDAO;
        this.crudEmpleadosLocales = crudEmpleadosLocales;
    }

    public void sincronizarEmpleados() throws SQLException {
        List<Empleado> empleadosBD = empleadoDAO.obtenerTodos();
        List<Empleado> empleadosLocales = crudEmpleadosLocales.obtenerTodos();

        Map<Integer, Empleado> mapaLocales = new HashMap<>();
        for (Empleado empleadoLocal : empleadosLocales) {
            mapaLocales.put(empleadoLocal.getIdEmpleado(), empleadoLocal);
        }

        for (Empleado empleadoBD : empleadosBD) {
            Empleado empleadoLocal = mapaLocales.get(empleadoBD.getIdEmpleado());
            if (empleadoLocal != null) {
                if (!empleadoLocal.equals(empleadoBD)) {
                    empleadoDAO.actualizar(empleadoLocal);
                }
                mapaLocales.remove(empleadoLocal.getIdEmpleado());
            }
        }

        // Inserta empleados locales que no están en la base de datos
        for (Empleado empleadoLocal : mapaLocales.values()) {
            empleadoDAO.insertar(empleadoLocal);
        }
    }
}
