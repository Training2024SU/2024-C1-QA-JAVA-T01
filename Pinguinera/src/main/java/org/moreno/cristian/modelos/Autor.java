package org.moreno.cristian.modelos;

import com.github.javafaker.Faker;

public class Autor {
    private String id;
    private String nombre;

    public Autor() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Autor(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    public Autor(String nombre) {
        Faker faker = new Faker();
        this.id = faker.bothify("#####");
        this.nombre = nombre;
    }
}
