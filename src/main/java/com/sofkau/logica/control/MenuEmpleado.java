package com.sofkau.logica.control;

import com.sofkau.dialogo.Menu;

import static com.sofkau.logica.control.ControlIngreso.scannerGlobal;
import static com.sofkau.logica.control.ControlIngreso.usuarioOp;
import static com.sofkau.logica.control.ControlIngreso.prestamoOp;
import static com.sofkau.logica.control.ControlIngreso.bandera;
import static com.sofkau.logica.control.ControlIngreso.publicacionOp;
import static com.sofkau.logica.control.ControlIngreso.autorOp;import com.sofkau.dialogo.Menu;
import com.sofkau.util.enums.TipoPublicacion;
import static com.sofkau.logica.control.ControlIngreso.scannerGlobal;
import static com.sofkau.logica.control.ControlIngreso.usuarioOp;
import static com.sofkau.logica.control.ControlIngreso.prestamoOp;
import static com.sofkau.logica.control.ControlIngreso.bandera;
import static com.sofkau.logica.control.ControlIngreso.publicacionOp;
import static com.sofkau.logica.control.ControlIngreso.empleadoOp;


public class MenuEmpleado {
    protected static void menuEmpleado()  {
        switch (empleadoOp.getEmpleadoActual().getRol()) {
            case "ADMINISTRADOR" -> {
                Menu.menuAdministrador();
                int op = scannerGlobal.nextInt();
                scannerGlobal.nextLine();
                MenuAdministrador.menuAdministrador(op);
            }
            case "ASISTENTE" -> {
                Menu.ingresoAsistente();
                int op = scannerGlobal.nextInt();
                scannerGlobal.nextLine();
                MenuAsistente.menuAsistente(op);
            }
            case "PROPIETARIO" -> {
                Menu.ingresoPropietario();
                int op = scannerGlobal.nextInt();
                scannerGlobal.nextLine();
                MenuPropietario.menuPropietario(op);
            }
            default -> {
                System.out.println("Ha ocurrido un error por favor verifique sus credenciales");
                bandera = false;
            }

        }
    }

}
