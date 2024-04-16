package com.sofkau.logica.control;

import com.sofkau.dialogo.Menu;
import com.sofkau.util.enums.TipoPublicacion;
import static com.sofkau.logica.control.ControlIngreso.scannerGlobal;
import static com.sofkau.logica.control.ControlIngreso.usuarioOp;
import static com.sofkau.logica.control.ControlIngreso.prestamoOp;
import static com.sofkau.logica.control.ControlIngreso.bandera;
import static com.sofkau.logica.control.ControlIngreso.publicacionOp;
import static com.sofkau.logica.control.ControlIngreso.autorOp;


public class MenuUsuario {
    protected static void menuUsuario() {

        Menu.menuUsuario();
        int op = scannerGlobal.nextInt();
        scannerGlobal.nextLine();

        switch (op) {
            case 1-> {
                Menu.ingresoTitulo();
                String titulo = scannerGlobal.nextLine();
                Menu.ingresoFechaDevolucion();
                String fechaDev = scannerGlobal.nextLine();

                prestamoOp.RegistrarPrestamo(titulo,fechaDev,usuarioOp.getUsuarioActual().getCorreo());
            }
            case 2-> {
                publicacionOp.imprimirPublicaciones(TipoPublicacion.Libro);
            }
            case 3-> {
                publicacionOp.imprimirPublicaciones(TipoPublicacion.Novela);
            }
            case 4-> {
                autorOp.listarAutores();
            }
            case 5-> {
                Menu.nombre();
                String nombre = scannerGlobal.nextLine();
                publicacionOp.listarPublicacionesPorAutor(nombre);
            }
            default -> {
                System.out.println("Ha ocurrido un error por favor verifique sus credenciales");
                bandera = false;
            }

        }
    }
}
