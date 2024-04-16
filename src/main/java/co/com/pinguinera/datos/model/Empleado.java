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
        final String RESET = "\033[0m";
        final String GREEN = "\033[32m";

        String columnas = String.format(
                "%-10s %-20s %-15s %-30s %-15s %-15s",
                "ID", "Nombre", "Contraseña", "Correo", "Rol", "Administrativo"
        );
        String datos = String.format(
                "%s%-10d%s %s%-20s%s %s%-15s%s %s%-30s%s %s%-15s%s %s%-15b%s",
                GREEN, idEmpleado, RESET,
                GREEN, nombre, RESET,
                GREEN, contrasena, RESET,
                GREEN, correo, RESET,
                GREEN, rol, RESET,
                GREEN, esAdministrativo, RESET
        );
        return "\n" + columnas + "\n" + datos;
    }
}
