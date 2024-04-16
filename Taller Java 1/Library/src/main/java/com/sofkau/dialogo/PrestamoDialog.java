package com.sofkau.dialogo;

import com.github.javafaker.Faker;
import com.sofkau.logica.prestamo.CrudPrestamo;
import com.sofkau.logica.publicacion.CrudLibro;
import com.sofkau.model.Prestamo;
import com.sofkau.model.Publicacion;
import com.sofkau.util.OperadorFechas;
import com.sofkau.util.PedirPorConsola;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PrestamoDialog {

    public static void logicaParaHacerPrestamosEmpleados () throws SQLException {
        System.out.println("Ha ingresado a la seccion de pretamos, donde podra modificar el estado del prestamo");
        System.out.println("Ingrese el id del prestamo a modificar");
        String idPrestamo = PedirPorConsola.pedirString();
        com.sofkau.model.Prestamo prestamo = CrudPrestamo.findPrestamoById(idPrestamo);
        System.out.println("Con que estado desea que quede el prestamo");
        System.out.println("1. Finalizado");
        System.out.println("2. Realizado");
        int opcionSeleccionada = PedirPorConsola.pedirOpcion();
        switch (opcionSeleccionada) {
            case 1:
                CrudPrestamo.changeStatusPrestamo("FINALIZADA", prestamo.getIdPrestamo());
                Publicacion publicacionToSetChangeStatus = CrudLibro.findPublicacionByTitulo(prestamo.getTituloPublicacion());
                publicacionToSetChangeStatus.setCantidadPrestado(publicacionToSetChangeStatus.getCantidadPrestado() - 1);
                CrudLibro.updatePublicacionByTitulo(publicacionToSetChangeStatus.getCantidadEjemplares(),publicacionToSetChangeStatus.getCantidadPrestado(), publicacionToSetChangeStatus.getTitulo());
                break;
            case 2:
                CrudPrestamo.changeStatusPrestamo("REALIZADA", prestamo.getIdPrestamo());
                break;
            default:
                System.out.println("valor no valido");
        }
    }

    public static void logicaParaHacerPrestamosUsuario () throws SQLException {
        System.out.println("Eligio solicitar prestamo");
        System.out.println("Escriba la publicacion que desea obtener: ");
        String tituloToGive = PedirPorConsola.pedirString();
        System.out.println("Escriba su correo: ");
        Faker faker = new Faker(new Locale("es"));
        String id = faker.bothify("##########");
        String emailToGive = PedirPorConsola.pedirString();
        Prestamo prestamoGive = new Prestamo();
        prestamoGive.setIdPrestamo(id);
        prestamoGive.setFechaPrestamo(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        prestamoGive.setFechaDevolucion(new SimpleDateFormat("yyy-MM-dd").format(OperadorFechas.sumarRestarDiasFecha(new Date(), 15)));
        prestamoGive.setEstadoPrestamo("SOLICITADO");
        prestamoGive.setCorreoUsuario(emailToGive);
        prestamoGive.setTituloPublicacion(tituloToGive);
        CrudPrestamo.createPrestamo(prestamoGive);
        Publicacion publicacionToSet = CrudLibro.findPublicacionByTitulo(prestamoGive.getTituloPublicacion());
        publicacionToSet.setCantidadPrestado(publicacionToSet.getCantidadPrestado() + 1);
        CrudLibro.updatePublicacionByTitulo(publicacionToSet.getCantidadEjemplares(),publicacionToSet.getCantidadPrestado(), publicacionToSet.getTitulo());
    }
}
