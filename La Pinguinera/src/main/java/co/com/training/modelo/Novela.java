package co.com.training.modelo;

import co.com.training.util.enums.TipoPublicacion;

public class Novela {
    private String tituloNovela;
    private String autor;
    private String genero;
    private int edadLecturaSugerida;
    private int cantidadEjemplares;
    private int cantidadPrestados;

    public Novela(String tituloNovela, String autorNovela, String genero, int edadLecturaSugerida, int cantidadEjemplares, int cantidadPrestados) {
        this.tituloNovela = tituloNovela;
        this.autor = autor;
        this.genero = genero;
        this.edadLecturaSugerida = edadLecturaSugerida;
        this.cantidadEjemplares = cantidadEjemplares;
        this.cantidadPrestados = cantidadPrestados;
    }

    public String getTituloNovela() {
        return tituloNovela;
    }

    public void setTituloNovela(String tituloNovela) {
        this.tituloNovela = tituloNovela;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEdadLecturaSugerida() {
        return edadLecturaSugerida;
    }

    public void setEdadLecturaSugerida(int edadLecturaSugerida) {
        this.edadLecturaSugerida = edadLecturaSugerida;
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