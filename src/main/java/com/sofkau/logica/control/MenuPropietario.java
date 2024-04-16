package com.sofkau.logica.control;

import com.sofkau.dialogo.Menu;
import com.sofkau.model.Empleado;
import com.sofkau.util.enums.Roles;
import com.sofkau.dialogo.Menu;
import com.sofkau.util.enums.TipoPublicacion;
import static com.sofkau.logica.control.ControlIngreso.scannerGlobal;
import static com.sofkau.logica.control.ControlIngreso.usuarioOp;
import static com.sofkau.logica.control.ControlIngreso.prestamoOp;
import static com.sofkau.logica.control.ControlIngreso.bandera;
import static com.sofkau.logica.control.ControlIngreso.publicacionOp;
import static com.sofkau.logica.control.ControlIngreso.autorOp;
import static com.sofkau.logica.control.ControlIngreso.empleadoOp;


public class MenuPropietario {

    protected static void menuPropietario(int op) {

        switch (op) {
            case 1 -> {
                Menu.nombre();
                String nombre = scannerGlobal.nextLine();
                Menu.correo();
                String correo = scannerGlobal.nextLine();
                Menu.contrasena();
                String contrasena = scannerGlobal.nextLine();


                empleadoOp.registrarEmpleado(new Empleado(nombre,correo,contrasena), Roles.ASISTENTE.toString());

            }
            default -> {
                System.out.println("Ha ocurrido un error por favor verifique sus credenciales");
                bandera = false;
            }

        }
    }
}
