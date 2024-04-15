package com.sofkau.logica.empleado;

import com.sofkau.integration.database.ConexionDatabase;
import com.sofkau.integration.database.mysql.MySqlOperation;
import com.sofkau.integration.repositorio.EmpleadoRepositorio;
import com.sofkau.model.Empleado;
import com.sofkau.util.CommonOperacion.GenerateUniqueId;
import com.sofkau.util.enums.Roles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;

public class EmpleadoOperaciones {

    private Empleado empleadoActual;

    private static HashMap<String, Empleado> empleados = new HashMap<>();

    public void registrarEmpleado(Empleado empleado, String rol) {
        empleado.setId(GenerateUniqueId.generateID());
        empleado.setRol(rol);
        EmpleadoRepositorio.crearEmpleado(empleado);
        Empleado empleadoVal = consultarEmpleado(empleado.getId());
        if(empleadoVal != null){
            System.out.println("Empleado " +rol+ " creado correctamente");
            System.out.println(empleado);
            empleados.put(empleadoVal.getId(), empleadoVal);
        }else{
            System.out.println("Por favor verifique");
        }
    }

    public EmpleadoOperaciones() {
        getEmpleados();
    }

    public static void getEmpleados() {
        empleados = EmpleadoRepositorio.consultarEmpleados();
    }

    public boolean inicioSesion(String correo, String contrasena) {
        getEmpleados();
        Optional<Empleado> empleadoVal;
        empleadoVal = empleados.values().stream()
                    .filter(empleado -> empleado.getCorreo().equals(correo) && empleado.getContrasena().equals(contrasena))
                    .findFirst();

        if(!empleadoVal.isEmpty()){
            this.empleadoActual = empleadoVal.get();
            return true;
        }

       return false;
    }

    private Empleado consultarEmpleado(String Id){
       return EmpleadoRepositorio.consultarEmpleadoPorId(Id);
    }

    public Empleado getEmpleadoActual(){
        return empleadoActual;
    }

}
