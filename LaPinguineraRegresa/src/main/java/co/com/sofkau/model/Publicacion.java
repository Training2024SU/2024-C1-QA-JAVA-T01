package co.com.sofkau.model;

import co.com.sofkau.integration.database.mysql.MySqlOperation;

import java.util.ArrayList;
import java.util.List;

public class Publicacion {

    private String titulo;
    private String tipo;
    private String autor;
    private int numeroPaginas;
    private int cantidadEjemplares;
    private int cantidadPrestado;
    private int cantidadDisponible;

    private String areas;
    private String edades;

    public static ArrayList<Publicacion> publicaciones = new ArrayList<>();

    private static MySqlOperation mySqlOperation ;



    public Publicacion(){}

    public Publicacion(String titulo, String tipo, String autor, int numeroPaginas, int cantidadEjemplares, int cantidadPrestado, int cantidadDisponible) {
        this.titulo = titulo;
        this.tipo = tipo;
        this.autor = autor;
        this.numeroPaginas = numeroPaginas;
        this.cantidadEjemplares = cantidadEjemplares;
        this.cantidadPrestado = cantidadPrestado;
        this.cantidadDisponible = cantidadDisponible;
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
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

    public String getAreas() {
        return areas;
    }

    public void setAreas(String areas) {
        this.areas = areas;
    }

    public String getEdades() {
        return edades;
    }

    public void setEdades(String edades) {
        this.edades = edades;
    }

    public static ArrayList<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public static MySqlOperation getMySqlOperation() {
        return mySqlOperation;
    }

    public static void setMySqlOperation(MySqlOperation mySqlOperation) {
        Publicacion.mySqlOperation = mySqlOperation;
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
                ", areas=" + areas +
                ", edades=" + edades +
                '}';
    }
}
