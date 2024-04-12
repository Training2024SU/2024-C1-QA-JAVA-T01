package org.moreno.cristian.modelos;

import com.github.javafaker.Faker;

public class Publicacion {

    private String id;
    private String titulo;
    private int totalEjemplares;
    private int ejemplaresDisponibles;
    private int ejemplaresPrestados;
    private Autor autor;

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getTotalEjemplares() {
        return totalEjemplares;
    }

    public void setTotalEjemplares(int totalEjemplares) {
        this.totalEjemplares = totalEjemplares;
    }

    public int getEjemplaresDisponibles() {
        return ejemplaresDisponibles;
    }

    public void setEjemplaresDisponibles(int ejemplaresDisponibles) {
        this.ejemplaresDisponibles = ejemplaresDisponibles;
    }

    public int getEjemplaresPrestados() {
        return ejemplaresPrestados;
    }

    public void setEjemplaresPrestados(int ejemplaresPrestados) {
        this.ejemplaresPrestados = ejemplaresPrestados;
    }

    public Publicacion(String id, String titulo, int totalEjemplares, int ejemplaresDisponibles, int ejemplaresPrestados, Autor autor) {
        this.id = id;
        this.titulo = titulo;
        this.totalEjemplares = totalEjemplares;
        this.ejemplaresDisponibles = ejemplaresDisponibles;
        this.ejemplaresPrestados = ejemplaresPrestados;
        this.autor = autor;
    }

    public Publicacion(String titulo, int totalEjemplares, int ejemplaresDisponibles, int ejemplaresPrestados, Autor autor) {
        Faker faker = new Faker();
        this.id = faker.bothify("#####");
        this.titulo = titulo;
        this.totalEjemplares = totalEjemplares;
        this.ejemplaresDisponibles = ejemplaresDisponibles;
        this.ejemplaresPrestados = ejemplaresPrestados;
        this.autor = autor;
    }
}
