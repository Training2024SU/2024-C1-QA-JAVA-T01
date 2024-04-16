package co.com.ejercicio.modelo;

public class Usuario {

    private String correo;
    private String nombre;
    private String contrasenia;


    public Usuario(String correo,String nombre,String contrasenia) {

        this.correo = correo;
        this.nombre = nombre;
        this.contrasenia = contrasenia;

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



    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "correo='" + correo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                '}';
    }
}
