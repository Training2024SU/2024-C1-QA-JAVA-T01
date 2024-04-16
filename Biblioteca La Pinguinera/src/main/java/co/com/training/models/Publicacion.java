package co.com.training.models;

public class Publicacion {
    private String tituloPublicacion;
    private String autorPublicacion;
    private int cantidadEjemplares;
    private int cantidadPrestados;

    public Publicacion(String tituloPublicacion, String autorPublicacion, int cantidadEjemplares, int cantidadPrestados) {
        this.tituloPublicacion = tituloPublicacion;
        this.autorPublicacion = autorPublicacion;
        this.cantidadEjemplares = cantidadEjemplares;
        this.cantidadPrestados = cantidadPrestados;
    }

    public String getTituloPublicacion() {
        return tituloPublicacion;
    }

    public void setTituloPublicacion(String tituloPublicacion) {
        this.tituloPublicacion = tituloPublicacion;
    }

    public String getAutorPublicacion() {
        return autorPublicacion;
    }

    public void setAutorPublicacion(String autorPublicacion) {
        this.autorPublicacion = autorPublicacion;
    }

    public int getCantidadEjemplares() {
        return cantidadEjemplares;
    }

    public void setCantidadEjemplares(int cantidadEjemplares) {
        this.cantidadEjemplares = cantidadEjemplares;
    }

    public int getCantidadPrestados() {
        return cantidadPrestados;
    }

    public void setCantidadPrestados(int cantidadPrestados) {
        this.cantidadPrestados = cantidadPrestados;
    }

}