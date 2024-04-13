package co.com.pinguinera.modelado;

import co.com.pinguinera.modelado.enums.RolUsuario;

public class Usuario {
    private String correo;
    private String nombre;
    private String contrasena;
    private RolUsuario rol; // Atributo para el rol del usuario

    public Usuario() {
        // Constructor vacío
    }

    // Constructor con todos los campos
    public Usuario(String correo, String nombre, String contrasena, RolUsuario rol) {
        this.correo = correo;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.rol = rol; // Inicializa el rol
    }

    // Getters y Setters
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public RolUsuario getRol() {
        return rol;
    }

    public void setRol(RolUsuario rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "correo='" + correo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", rol=" + rol + // Incluye el rol en la representación textual
                '}';
    }
}
