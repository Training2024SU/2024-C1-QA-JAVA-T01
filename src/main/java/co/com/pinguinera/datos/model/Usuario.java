package co.com.pinguinera.datos.model;

public class Usuario {
    private int idUsuario; // Nuevo campo `idUsuario`
    private String correo;
    private String nombre;
    private String contrasena;

    public Usuario() {
        // Constructor vacío
    }

    // Constructor con todos los campos
    public Usuario(int idUsuario, String correo, String nombre, String contrasena) {
        this.idUsuario = idUsuario;
        this.correo = correo;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    // Getters y Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

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

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", correo='" + correo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", contrasena='" + contrasena + '\'' +
                '}';
    }
}
