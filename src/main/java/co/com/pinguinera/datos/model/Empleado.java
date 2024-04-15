package co.com.pinguinera.datos.model;

public class Empleado {
    private int idEmpleado;
    private String nombre;
    private String contrasena;
    private String correo;
    private String rol; // 'ADMINISTRATIVO' o 'ASISTENTE' como ENUM
    private boolean esAdministrativo; // Representa el TINYINT EsAdministrativo

    public Empleado() {
        // Constructor vacío
    }

    // Constructor con todos los campos
    public Empleado(int idEmpleado, String nombre, String contrasena, String correo, String rol, boolean esAdministrativo) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.correo = correo;
        this.rol = rol;
        this.esAdministrativo = esAdministrativo;
    }

    // Getters y Setters
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public boolean isEsAdministrativo() {
        return esAdministrativo;
    }

    public void setEsAdministrativo(boolean esAdministrativo) {
        this.esAdministrativo = esAdministrativo;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "idEmpleado=" + idEmpleado +
                ", nombre='" + nombre + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", correo='" + correo + '\'' +
                ", rol='" + rol + '\'' +
                ", esAdministrativo=" + esAdministrativo +
                '}';
    }
}
