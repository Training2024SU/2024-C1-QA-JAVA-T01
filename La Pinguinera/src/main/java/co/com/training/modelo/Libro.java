package co.com.training.modelo;

import co.com.training.util.enums.TipoPublicacion;

public class Libro {
    private String tituloLibro;
    private String autor;
    private String areaConocimiento;
    private int numeroPaginas;
    private int cantidadEjemplares;
    private int cantidadPrestados;

    public Libro(String tituloLibro, String autorLibro, String areaConocimiento, int numeroPaginas, int cantidadEjemplares, int cantidadPrestados) {
        this.tituloLibro = tituloLibro;
        this.autor = autor;
        this.areaConocimiento = areaConocimiento;
        this.numeroPaginas = numeroPaginas;
        this.cantidadEjemplares = cantidadEjemplares;
        this.cantidadPrestados = cantidadPrestados;
    }

    public String getTituloLibro() {
        return tituloLibro;
    }

    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAreaConocimiento() {
        return areaConocimiento;
    }

    public void setAreaConocimiento(String areaConocimiento) {
        this.areaConocimiento = areaConocimiento;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
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