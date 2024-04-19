package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class AreaGenero {
    private String titulo;
    private String areaGenero;
    public static final List<AreaGenero> generos=new ArrayList<>();

    public AreaGenero() {
    }

    public AreaGenero(String titulo, String areaGenero) {
        this.titulo = titulo;
        this.areaGenero = areaGenero;
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
