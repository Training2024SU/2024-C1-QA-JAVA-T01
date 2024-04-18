package com.sofka.controllers;

import com.sofka.enums.Estado;
import com.sofka.modelo.Prestamo;
import net.datafaker.Faker;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static com.sofka.BibliotecaLaPinguinera.*;
import static com.sofka.constants.InsertConstants.INSERT_PRESTAMO;
import static com.sofka.constants.SelectConstants.SELECT_ALL_FROM_PRESTAMO;
import static com.sofka.constants.UpdateConstants.*;
import static com.sofka.controllers.ControlMenu.mostrarMensaje;
import static com.sofka.controllers.ControlStament.ejecutarMostrarSQL;
import static com.sofka.controllers.ControlStament.insertIntoBd;

public class ControlPrestamo {
    private static final List<Prestamo> prestamos = new ArrayList<>();

    public static void logicaCrearPrestamo(Prestamo prestamo) {
        registrarPrestamo(prestamo);
        prestamos.add(prestamo);
        mostrarMensaje("¡Prestamo registrado exitosamente: " + prestamo.getIdPrestamo() + "!");
    }


    public static void solicitarPrestamo() {
        Faker faker = new Faker();
        String idPrestamo = faker.passport().valid();
        String fechaPrestamo = LocalDate.now().toString();
        String fechaDevolucion = LocalDate.now().plusDays(15).toString();
        String estado = ("SOLICITADO");
        String correo = JOptionPane.showInputDialog(null, "Ingrese el correo del Lector: ");
        String titulo = JOptionPane.showInputDialog(null, "Ingrese el titulo del Libro/Novela: ");
        Prestamo presto = new Prestamo(idPrestamo, fechaPrestamo, fechaDevolucion, estado, correo, titulo);
        logicaCrearPrestamo(presto);
    }

    public static ArrayList<String> realizarPrestamo() throws SQLException {
        String correoUsuario = JOptionPane.showInputDialog(null, "Ingrese el correo: ");
        mySqlOperation.setSqlStatement(SELECT_ALL_FROM_PRESTAMO);
        mySqlOperation.executeSqlStatement();
        ResultSet prestamos = mySqlOperation.getResulset();
        ArrayList<String> miLista = new ArrayList<>();
        System.out.println("Resultado busqueda de los Prestamo del usuario " + correoUsuario);
        while (prestamos.next()) {
            String idPrestamo = prestamos.getString("idPrestamo");
            String correo = prestamos.getString("correoUsuario");
            String titulo = prestamos.getString("tituloPublicacion");
            if (correo.equals(correoUsuario)) {
                miLista.add(String.format(UPDATE_PRESTAMO, "REALIZADO", idPrestamo));
                miLista.add(String.format(UPDATE_PUBLICACION_REALIZADA, titulo));
                System.out.println("Correo: " + correo + ",\t" + "Titulo: " + titulo);
            }
        }
        return miLista;
    }

    public static ArrayList<String> finalizarPrestamo() throws SQLException {
        String correoUsuario = JOptionPane.showInputDialog(null, "Ingrese el correo: ");
        mySqlOperation.setSqlStatement(SELECT_ALL_FROM_PRESTAMO);
        mySqlOperation.executeSqlStatement();
        ResultSet prestamos = mySqlOperation.getResulset();
        ArrayList<String> miLista = new ArrayList<>();
        System.out.println("Resultado busqueda de los Prestamo del usuario " + correoUsuario);
        while (prestamos.next()) {
            String idPrestamo = prestamos.getString("idPrestamo");
            String correo = prestamos.getString("correoUsuario");
            String titulo = prestamos.getString("tituloPublicacion");
            if (correo.equals(correoUsuario)) {
                miLista.add(String.format(UPDATE_PRESTAMO, "FINALIZADO", idPrestamo));
                miLista.add(String.format(UPDATE_PUBLICACION_FINALIZADA,titulo));
                System.out.println("Correo: " + correo + ",\t" + "Titulo: " + titulo);
            }
        }
        return miLista;
    }

    public static void registrarPrestamo(Prestamo prestamo) {
        String cadena = String.format(INSERT_PRESTAMO, prestamo.getIdPrestamo(), prestamo.getFechaPrestamo(), prestamo.getFechaDevolucion(), prestamo.getEstado(), prestamo.getCorreoUsuario(), prestamo.getTituloPublicacion());
        insertIntoBd(cadena);
    }

    private static void crearPrestamo() {
        Faker faker = new Faker(new Locale("es"));
        String idPrestamo = faker.number().digits(10);
        String fechaPrestamo = LocalDate.now().toString();
        String fechaDevolucion = LocalDate.now().plusDays(15).toString();
        String correo = JOptionPane.showInputDialog(null, "Ingrese el correo del Lector: ");
        String titulo = JOptionPane.showInputDialog(null, "Ingrese el titulo del libro/novela: ");
        String estado = String.valueOf(Estado.values()[new Random().nextInt(Estado.values().length)]);
        Prestamo presto = new Prestamo(idPrestamo, fechaPrestamo, fechaDevolucion, estado, correo, titulo);
        logicaCrearPrestamo(presto);
    }

    public static void insertarPrestamoFaker(int cantidad) throws SQLException {
        for (int i = 0; i < cantidad; i++) {
            crearPrestamo();
        }
    }

    public static void selectAllFromPrestamo() throws SQLException {
        System.out.println("Lista de Prestamos Registrados");
        ejecutarMostrarSQL(SELECT_ALL_FROM_PRESTAMO);
    }

}
