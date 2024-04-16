package com.sofkau.logica.publicacion;

import com.sofkau.integration.repositorio.EdadSugeridaRepositorio;
import com.sofkau.model.EdadSugerida;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class EdadSugeridaOperaciones {

    private ArrayList<EdadSugerida> listaEdadesSugeridas = new ArrayList<>();

    public EdadSugeridaOperaciones() {
        getEdadesSugeridas();
    }

    public void crearEdadSugerida(EdadSugerida edadSugerida) {
        EdadSugeridaRepositorio.crearEdadSugerida(edadSugerida);
        listaEdadesSugeridas.add(edadSugerida);
    }

    public void getEdadesSugeridas() {
        listaEdadesSugeridas = EdadSugeridaRepositorio.consultarEdadesSugeridas();
    }

    // Retorna todos los registros que posean el título de la publicación
    public ArrayList<EdadSugerida> getEdadesSugeridasPorIdTitulo(String titulo) {
        return listaEdadesSugeridas.stream()
                .filter(edad -> edad.getTitulo().equals(titulo))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
