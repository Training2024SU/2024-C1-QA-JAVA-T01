package co.com.training.modelo;

public class Autor {
    private final String id_autor;
    private String nombre_autor;
    private String apellido_autor;

    public Autor(String id_autor, String nombre_autor, String apellido_autor) {
        this.id_autor = id_autor;
        this.nombre_autor = nombre_autor;
        this.apellido_autor = apellido_autor;
    }

    public String getId_autor() {
        return id_autor;
    }

    public String getNombre_autor() {
        return nombre_autor;
    }

    public void setNombre_autor(String nombre_autor) {
        this.nombre_autor = nombre_autor;
    }

    public String getApellido_autor() {
        return apellido_autor;
    }

    public void setApellido_autor(String apellido_autor) {
        this.apellido_autor = apellido_autor;
    }
}
