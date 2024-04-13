package co.com.pinguinera.modelado;

import co.com.pinguinera.modelado.enums.TipoPublicacion;
import java.util.List;

public class Publicacion {
    private String titulo;
    private TipoPublicacion tipoPublicacion; // Usa el enum TipoPublicacion
    private String autor;
    private int numPaginas;
    private int cantEjemplares;
    private int cantPrestados;
    private int cantDisponible;
    private List<AreaGenero> areas;
    private List<EdadSugerida> edades;

    public Publicacion() {
        // Constructor vacío
    }

    // Constructor con todos los campos
    public Publicacion(String titulo, TipoPublicacion tipoPublicacion, String autor, int numPaginas, int cantEjemplares, int cantPrestados, int cantDisponible, List<AreaGenero> areas, List<EdadSugerida> edades) {
        this.titulo = titulo;
        this.tipoPublicacion = tipoPublicacion;
        this.autor = autor;
        this.numPaginas = numPaginas;
        this.cantEjemplares = cantEjemplares;
        this.cantPrestados = cantPrestados;
        this.cantDisponible = cantDisponible;
        this.areas = areas;
        this.edades = edades;
    }

    // Getters y Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public TipoPublicacion getTipoPublicacion() {
        return tipoPublicacion;
    }

    public void setTipoPublicacion(TipoPublicacion tipoPublicacion) {
        this.tipoPublicacion = tipoPublicacion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }

    public int getCantEjemplares() {
        return cantEjemplares;
    }

    public void setCantEjemplares(int cantEjemplares) {
        this.cantEjemplares = cantEjemplares;
    }

    public int getCantPrestados() {
        return cantPrestados;
    }

    public void setCantPrestados(int cantPrestados) {
        this.cantPrestados = cantPrestados;
    }

    public int getCantDisponible() {
        return cantDisponible;
    }

    public void setCantDisponible(int cantDisponible) {
        this.cantDisponible = cantDisponible;
    }

    public List<AreaGenero> getAreas() {
        return areas;
    }

    public void setAreas(List<AreaGenero> areas) {
        this.areas = areas;
    }

    public List<EdadSugerida> getEdades() {
        return edades;
    }

    public void setEdades(List<EdadSugerida> edades) {
        this.edades = edades;
    }

    @Override
    public String toString() {
        return "Publicacion{" +
                "titulo='" + titulo + '\'' +
                ", tipoPublicacion=" + tipoPublicacion +
                ", autor='" + autor + '\'' +
                ", numPaginas=" + numPaginas +
                ", cantEjemplares=" + cantEjemplares +
                ", cantPrestados=" + cantPrestados +
                ", cantDisponible=" + cantDisponible +
                ", areas=" + areas +
                ", edades=" + edades +
                '}';
    }
}
