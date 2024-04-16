package com.sofkau.logica.control;

import com.github.javafaker.Faker;
import com.sofkau.dialogo.PrestamoDialog;
import com.sofkau.dialogo.PublicacionDialog;
import com.sofkau.logica.prestamo.CrudPrestamo;
import com.sofkau.logica.publicacion.CrudLibro;
import com.sofkau.model.Prestamo;
import com.sofkau.model.Publicacion;
import com.sofkau.util.OperadorFechas;
import com.sofkau.util.PedirPorConsola;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.sofkau.dialogo.Menu.*;
import static com.sofkau.logica.Empleado.CrudEmpleado.logearseEmpleado;

public class MetodosMain {
    public static void implementarLogica() throws SQLException {
        int option;
        boolean bandera = true;
        while (bandera) {
            menuPrincipal();
            option = PedirPorConsola.pedirOpcion();
            bandera = seleccionarOpciones(option, bandera);

        }
    }


    public static void implementarLogicaUsuario() throws SQLException {
        int optionUsuario;
        menuUsuario();
        optionUsuario = PedirPorConsola.pedirOpcion();
        seleccionarOpcionesUsuario(optionUsuario);
    }

    public static void implementarLogicaEmpleado() throws SQLException {
        //menuLoguearseEmpleado();
       logearseEmpleado();
       int optionEmpleado;
        menuEmpleado();
        optionEmpleado = PedirPorConsola.pedirOpcion();
        seleccionarOpcionesEmpleado(optionEmpleado);
    }

    public static boolean seleccionarOpciones(int option, boolean bandera) throws SQLException {
        switch (option) {
            case 1:
                implementarLogicaEmpleado();
                break;
            case 2:
                implementarLogicaUsuario();
                break;
            case 3:
                System.out.println("Opcion 3");
                bandera = false;
                break;
            default:
                System.out.println("ingrese una opcion válida");
        }
        bandera = false;
        return bandera;
    }

    public static void seleccionarOpcionesUsuario(int optionUsuario) throws SQLException {
        switch (optionUsuario) {
            case 1:
                PublicacionDialog.logicaObtenerPublicacionPorTitulo();
                break;
            case 2:
                PublicacionDialog.logicaObtenerPublicacionesPorAutor();
                break;
            case 3:
                PrestamoDialog.logicaParaHacerPrestamosUsuario();
                break;
            default:
                System.out.println("ingrese una opcion válida");
        }
    }

    public static void seleccionarOpcionesEmpleado(int optionEmpleado) throws SQLException {
        switch (optionEmpleado) {
            case 1:
                PrestamoDialog.logicaParaHacerPrestamosEmpleados();
                break;
            case 2:
                PublicacionDialog.logicaCrudPublicacion();
                break;
            case 3:
                System.out.println("3. Salir empleado");
                break;
            default:
                System.out.println("ingrese una opcion válida");
        }
    }
}

