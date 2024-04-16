package com.sofkau.logica.Autor;

import com.github.javafaker.Faker;
import com.sofkau.dialogo.MensajeOperacionBd;
import com.sofkau.integration.repositorio.AutorRepositorio;
import com.sofkau.integration.repositorio.EmpleadoRepositorio;
import com.sofkau.model.Autor;
import com.sofkau.model.Empleado;
import com.sofkau.util.CommonOperacion.GenerateUniqueId;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;

public class AutorOperaciones {

    private static HashMap<String, Autor> autores = new HashMap<>();

    public AutorOperaciones() {
            getAutores();
    }

    public void registrarAutor(Autor autor) {
        autor.setId(GenerateUniqueId.generateID());
        AutorRepositorio.crearAutor(autor);
        getAutores();

        if(consultarAutorId(autor.getId()) != null){
            MensajeOperacionBd.crearAutor();
            System.out.println(autor);
        }else{
            MensajeOperacionBd.crearAutor();
        }
    }



    public static void getAutores() {
        autores = AutorRepositorio.consultarAutores();
    }


    private static Autor consultarAutorId(String id) {
        Optional<Autor> autorVal;
        autorVal = autores.values().stream()
                .filter(autor -> autor.getId().equals(id))
                .findFirst();

       return autorVal.orElse(null);
    }

    public Autor buscarAutorNombre(String nombre) {
        for (Autor autor : autores.values()) {
            if (autor.getNombre().equalsIgnoreCase(nombre)) {
                return autor;
            }
        }
        return null; // Retorna null si no se encuentra el autor con el nombre especificado
    }

    public Autor buscarAutorId(String id) {
        for (Autor autor : autores.values()) {
            if (autor.getId().equalsIgnoreCase(id)) {
                return autor;
            }
        }
        return null; // Retorna null si no se encuentra el autor con el nombre especificado
    }

    public void listarAutores() {
        System.out.println("Listado de autores:");
        for (Autor autor : autores.values()) {
            System.out.println(autor);
        }
    }








}