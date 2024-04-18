package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    public static final List<Usuario> usuarios=new ArrayList<>();
    private String nombre;
    private String correo;
    private String contrasenna;

    public Usuario() {
    }

    public Usuario(String correo , String nombre, String contrasenna) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenna = contrasenna;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", contrasenna='" + contrasenna + '\'' +
                '}';
    }
}
