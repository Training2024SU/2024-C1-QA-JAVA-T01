package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Empleado {
    public static final List<Empleado> empleados =new ArrayList<>();
    private int id;
    private String nombre;
    private String correo;
    private String contrasenna;
    private String rol;

    public Empleado() {
    }

    public Empleado(String nombre, String correo, String contrasenna, String rol) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenna = contrasenna;
        this.rol = rol;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", contrasenna='" + contrasenna + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
