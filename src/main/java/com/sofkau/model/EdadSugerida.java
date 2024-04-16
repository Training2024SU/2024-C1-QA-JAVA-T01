package com.sofkau.model;

public class EdadSugerida {

    private String titulo;
    private String edadSugeridad;

    public EdadSugerida(String titulo, String edadSugeridad) {
        this.titulo = titulo;
        this.edadSugeridad = edadSugeridad;
    }

    public EdadSugerida() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEdadSugeridad() {
        return edadSugeridad;
    }

    public void setEdadSugeridad(String edadSugeridad) {
        this.edadSugeridad = edadSugeridad;
    }
}
