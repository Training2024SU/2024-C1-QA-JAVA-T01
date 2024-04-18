package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class EdadSugerida {
    public static final List<EdadSugerida> edades=new ArrayList<>();
    private String tituloPublicacion;
    private String edadSugeridad;

    public EdadSugerida() {
    }

    public EdadSugerida(String tituloPublicacion, String edadSugeridad) {
        this.tituloPublicacion = tituloPublicacion;
        this.edadSugeridad = edadSugeridad;
    }

    public String getTituloPublicacion() {
        return tituloPublicacion;
    }

    public void setTituloPublicacion(String tituloPublicacion) {
        this.tituloPublicacion = tituloPublicacion;
    }

    public String getEdadSugeridad() {
        return edadSugeridad;
    }

    public void setEdadSugeridad(String edadSugeridad) {
        this.edadSugeridad = edadSugeridad;
    }
}
