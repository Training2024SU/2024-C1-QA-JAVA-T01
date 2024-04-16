package com.sofkau.model;

public class AreaGenero {

    private String titulo;
    private String areaGenero;

    public AreaGenero(String titulo, String areaGenero) {
        this.titulo = titulo;
        this.areaGenero = areaGenero;
    }

    public AreaGenero() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAreaGenero() {
        return areaGenero;
    }

    public void setAreaGenero(String areaGenero) {
        this.areaGenero = areaGenero;
    }
}
