package com.sofka.controller;

import com.sofka.Enums.Rol;
import com.sofka.modelo.Empleado;
import net.datafaker.Faker;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static com.sofka.BibliotecaLaPinguinera.*;
import static com.sofka.Constants.InsertConstants.INSERT_EMPLEADO;
import static com.sofka.Constants.SelectConstants.SELECT_ALL_FROM_EMPLEADO;

public class ControlEmpleado {

    private static final List<Empleado> empleados = new ArrayList<>();

    public static void logicaCrearEmpleado(Empleado empleado){
        registrarEmpleado(empleado);
        empleados.add(empleado);
        mostrarMensaje("¡Empleado registrado exitosamente: " +empleado.getNombre()+ "!");
    }

    public static void registrarNuevoEmpleado(String rol) {
        String idEmpleado = JOptionPane.showInputDialog(null, "Ingrese la cedula del empleado: ");
        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre de empleado: ");
        String correo = JOptionPane.showInputDialog(null, "Ingrese una nueva direccion de correo: ");
        String contrasena = JOptionPane.showInputDialog(null, "Ingrese una nueva contraseña: ");
        Empleado worker = new Empleado(idEmpleado, nombre, contrasena, correo, rol);
        logicaCrearEmpleado(worker);
        mostrarMensaje("¡Empleado registrado exitosamente: " + nombre + "!");
    }

    public static void registrarEmpleado(Empleado empleado){
        String cadena = String.format(INSERT_EMPLEADO, empleado.getIdEmpleado(), empleado.getNombre(), empleado.getContrasena(), empleado.getCorreo(), empleado.getRol());
        insertIntoBd(cadena);
    }

    public static void crearEmpleado() {
        Faker faker = new Faker(new Locale("es"));
        String idEmpleado = faker.passport().valid();
        String nombre = faker.name().name().replace("'", "");
        String correo = faker.internet().emailAddress();
        String contrasena = faker.passport().valid();
        String rol = String.valueOf(Rol.values()[new Random().nextInt(Rol.values().length)]);
        Empleado worker = new Empleado(idEmpleado, nombre, contrasena, correo, rol);
        logicaCrearEmpleado(worker);
    }

    public static void insertarEmpleadoFaker(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            crearEmpleado();
        }
    }

    public static void selectAllFromEmpleado() throws SQLException {
        System.out.println("Lista de Empleados Registrados");
        insertIntoBd(SELECT_ALL_FROM_EMPLEADO);
        ejecutarMostrarSQL();
    }





}
