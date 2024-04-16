package com.sofkau.model;

import java.util.List;

public class Publicacion {

    private String titulo;
    private String tipo;
    private Autor autor;
    private int numeroPaginas;
    private int cantidadEjemplares;
    private int cantidadPrestado;
    private int cantidadDisponible;
    private List<AreaGenero> areas;
    private List<EdadSugerida> edades;


    public Publicacion(String titulo, Autor autor, int numeroPaginas, int cantidadEjemplares, int cantidadPrestado) {
        this.titulo = titulo;
        this.autor = autor;
        this.numeroPaginas = numeroPaginas;
        this.cantidadEjemplares = cantidadEjemplares;
        this.cantidadPrestado = cantidadPrestado;
    }

    public Publicacion(String titulo, Autor autor, int cantidadEjemplares, int cantidadPrestado) {
        this.titulo = titulo;
        this.autor = autor;
        this.numeroPaginas = numeroPaginas;
        this.cantidadEjemplares = cantidadEjemplares;
        this.cantidadPrestado = cantidadPrestado;
    }

    public Publicacion() {

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
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

    public int getCantidadPrestado() {
        return cantidadPrestado;
    }

    public void setCantidadPrestado(int cantidadPrestado) {
        this.cantidadPrestado = cantidadPrestado;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
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
                ", tipo='" + tipo + '\'' +
                ", autor='" + autor + '\'' +
                ", numeroPaginas=" + numeroPaginas +
                ", cantidadEjemplares=" + cantidadEjemplares +
                ", cantidadPrestado=" + cantidadPrestado +
                ", cantidadDisponible=" + cantidadDisponible +
                '}';
    }
}
