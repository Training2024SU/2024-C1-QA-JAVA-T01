package com.sofka.controllers;

import com.sofka.modelo.Usuario;
import net.datafaker.Faker;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.sofka.constants.InsertConstants.INSERT_USUARIO;
import static com.sofka.constants.SelectConstants.SELECT_ALL_FROM_USUARIO;
import static com.sofka.controllers.ControlMenu.mostrarMensaje;
import static com.sofka.controllers.ControlStament.ejecutarMostrarSQL;
import static com.sofka.controllers.ControlStament.insertIntoBd;

public class ControlUsuario {
    private ControlUsuario() {
    }

    private static final List<Usuario> usuarios = new ArrayList<>();

    public static void logicaCrearUsuario(Usuario usuario) {
        registrarUsuario(usuario);
        usuarios.add(usuario);
        mostrarMensaje("¡Usuario registrado exitosamente: " + usuario.getNombre() + "!");
    }

    public static void registrarNuevoUsuario() {
        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre de usuario: ");
        String correo = JOptionPane.showInputDialog(null, "Ingrese un nuevo correo: ");
        String contrasena = JOptionPane.showInputDialog(null, "Ingrese una nueva contraseña: ");
        Usuario user = new Usuario(correo, nombre, contrasena);
        logicaCrearUsuario(user);
    }

    public static void registrarUsuario(Usuario usuario) {
        String cadena = String.format(INSERT_USUARIO, usuario.getCorreo(), usuario.getNombre(), usuario.getContrasena());
        insertIntoBd(cadena);
    }

    public static void crearUsuario() {
        Faker faker = new Faker(new Locale("es"));
        String nombre = faker.name().name().replace("'", "");
        String correo = faker.internet().emailAddress();
        String contrasena = faker.internet().password();
        Usuario user = new Usuario(nombre, correo, contrasena);
        logicaCrearUsuario(user);
    }

    public static void insertarUsuarioFaker(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            crearUsuario();
        }
    }

    public static void selectAllFromUsuario() throws SQLException {
        System.out.println("Lista de Usuarios Registrados");
        ejecutarMostrarSQL(SELECT_ALL_FROM_USUARIO);
    }

}
