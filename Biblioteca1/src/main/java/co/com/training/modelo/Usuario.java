package co.com.training.modelo;


public class Usuario {
    private int id_usuario;
    private String nombre_usuario;
    private String correo_usuario;
    private String contrasena_usuario;
    private Rol rol;

    public Usuario() {
    }

    public Usuario(int id_usuario, String nombre_usuario, String correo_usuario, String contrasena_usuario, Rol rol) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.correo_usuario = correo_usuario;
        this.contrasena_usuario = contrasena_usuario;
        this.rol = rol;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getCorreo_usuario() {
        return correo_usuario;
    }

    public void setCorreo_usuario(String correo_usuario) {
        this.correo_usuario = correo_usuario;
    }

    public String getContrasena_usuario() {
        return contrasena_usuario;
    }

    public void setContrasena_usuario(String contrasena_usuario) {
        this.contrasena_usuario = contrasena_usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id_usuario=" + id_usuario +
                ", nombre_usuario='" + nombre_usuario + '\'' +
                ", correo_usuario='" + correo_usuario + '\'' +
                ", contrasena_usuario='" + contrasena_usuario + '\'' +
                ", rol=" + rol +
                '}';
    }
}

