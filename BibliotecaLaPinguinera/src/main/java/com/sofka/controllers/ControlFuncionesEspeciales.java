package com.sofka.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.sofka.BibliotecaLaPinguinera.mySqlOperation;
import static com.sofka.constants.SelectConstants.SELECT_ALL_FROM_PRESTAMO;
import static com.sofka.constants.SelectConstants.SELECT_ALL_FROM_PUBLICACION;
import static com.sofka.controllers.ControlMenu.pedirEntrada;

public class ControlFuncionesEspeciales {


    public static void buscarPrestamoPorCorreo() throws SQLException {
        String correoUsuario = pedirEntrada( "Ingrese el correo: ");
        mySqlOperation.setSqlStatement(SELECT_ALL_FROM_PRESTAMO);
        mySqlOperation.executeSqlStatement();
        ResultSet prestamos = mySqlOperation.getResulset();
        System.out.println("Resultado busqueda de los Prestamo del usuario ");
        while (prestamos.next()) {
            String correo = prestamos.getString("correoUsuario");
            String titulo = prestamos.getString("tituloPublicacion");
            if (correo.equals(correoUsuario)) {
                System.out.println("Correo: " + correo + ",\t" + "Titulo: " + titulo);
            }
        }
    }

    public static void buscarPublicacionPorAutor() throws SQLException {
        String autorIngresado = pedirEntrada( "Ingrese el autor: ");
        mySqlOperation.setSqlStatement(SELECT_ALL_FROM_PUBLICACION);
        mySqlOperation.executeSqlStatement();
        ResultSet prestamos = mySqlOperation.getResulset();
        System.out.println("Resultado busqueda de las Publicaciones del autor ");
        while (prestamos.next()) {
            String autor = prestamos.getString("autor");
            String titulo = prestamos.getString("titulo");
            if (autorIngresado.equals(autor)) {
                System.out.println("Titulo: " + titulo + ",\t" + "Autor: " + autor);
            }
        }
    }

}
