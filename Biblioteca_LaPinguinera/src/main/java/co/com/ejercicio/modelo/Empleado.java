package co.com.ejercicio.modelo;

import co.com.ejercicio.util.enums.Roles;

public class Empleado {
    private int idEmpleado;
    private String nombre;

    private String correo;
    private String contrasenia;
    private String rol;

    public Empleado(int idEmpleado, String nombre, String correo, String contrasenia, String rol) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
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

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
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
                "idEmpleado=" + idEmpleado +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", rol=" + rol +
                '}';
    }
}
