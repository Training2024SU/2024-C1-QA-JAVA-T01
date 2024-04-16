package com.sofkau.logica.publicacion;

import com.sofkau.integration.repositorio.AreaGeneroRepositorio;
import com.sofkau.model.AreaGenero;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class AreaGeneroOperaciones {

    ArrayList<AreaGenero> listaAreaGeneros = new ArrayList<>();

    public AreaGeneroOperaciones() {
        getAreaGenero();
    }

    public void crearAreaGenero (AreaGenero ag){
        AreaGeneroRepositorio.crearAreaGenero(ag);
        listaAreaGeneros.add(ag);
    }

    public void getAreaGenero(){
        listaAreaGeneros = AreaGeneroRepositorio.consultarAreasGenero();
    }

    // Retorna todos lo registros que posean el titulo de la publicacion
    public  ArrayList<AreaGenero> getAreaGeneroPorIdTitulo(String titulo){
        return listaAreaGeneros.stream()
                    .filter(area -> area.getTitulo().equals(titulo))
                    .collect(Collectors.toCollection(ArrayList::new));
    }

}
