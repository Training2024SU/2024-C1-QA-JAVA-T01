package com.sofkau.logica.control;

import com.sofkau.dialogo.Menu;
import com.sofkau.model.Empleado;
import com.sofkau.util.enums.Roles;

import static com.sofkau.logica.control.ControlIngreso.scannerGlobal;

import static com.sofkau.logica.control.ControlIngreso.bandera;

import static com.sofkau.logica.control.ControlIngreso.empleadoOp;

public class MenuAdministrador {
    protected static void menuAdministrador(int op) {

        switch (op) {
            case 1 -> {
                Menu.nombre();
                String nombre = scannerGlobal.nextLine();
                Menu.correo();
                String correo = scannerGlobal.nextLine();
                Menu.contrasena();
                String contrasena = scannerGlobal.nextLine();


                empleadoOp.registrarEmpleado(new Empleado(nombre,correo,contrasena), Roles.PROPIETARIO.toString());

            }
            case 2 -> {
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
