package co.com.training.modelo;

public class Material {
    private String id_material;
    private String titulo;
    private Autor autor;
    private int cantidad_ejemplares;
    private int cantidad_prestados;

    public Material(String id_material, String titulo, Autor autor, int cantidad_ejemplares, int cantidad_prestados) {
        this.id_material = id_material;
        this.titulo = titulo;
        this.autor = autor;
        this.cantidad_ejemplares = cantidad_ejemplares;
        this.cantidad_prestados = cantidad_prestados;
    }

    public String getId_material() {
        return id_material;
    }

    public void setId_material(String id_material) {
        this.id_material = id_material;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public int getCantidad_ejemplares() {
        return cantidad_ejemplares;
    }

    public void setCantidad_ejemplares(int cantidad_ejemplares) {
        this.cantidad_ejemplares = cantidad_ejemplares;
    }

    public int getCantidad_prestados() {
        return cantidad_prestados;
    }

    public void setCantidad_prestados(int cantidad_prestados) {
        this.cantidad_prestados = cantidad_prestados;
    }



}