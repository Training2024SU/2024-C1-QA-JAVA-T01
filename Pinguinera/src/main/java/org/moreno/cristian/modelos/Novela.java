package org.moreno.cristian.modelos;

import com.github.javafaker.Faker;
import org.moreno.cristian.modelos.enums.Genero;

public class Novela extends Publicacion{
    private String id;
    private int edadLectura;
    private Genero genero;

    public Novela(String id, String titulo, int totalEjemplares, int ejemplaresPrestados, int ejemplaresDisponibles, Autor autor, int edadLectura, Genero genero) {
        super(id, titulo, totalEjemplares, ejemplaresDisponibles, ejemplaresPrestados, autor);
        this.id = id;
        this.edadLectura = edadLectura;
        this.genero = genero;
    }

    public Novela(String titulo, int totalEjemplares, int ejemplaresPrestados, int ejemplaresDisponibles, Autor autor, int edadLectura, Genero genero) {
        super(new Faker().bothify("#####"), titulo, totalEjemplares, ejemplaresDisponibles, ejemplaresPrestados, autor);
        this.id = super.getId();
        this.edadLectura = edadLectura;
        this.genero = genero;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public int getEdadLectura() {
        return edadLectura;
    }

    public void setEdadLectura(int edadLectura) {
        this.edadLectura = edadLectura;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
}
