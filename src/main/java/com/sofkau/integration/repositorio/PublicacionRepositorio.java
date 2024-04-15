package com.sofkau.integration.repositorio;

import com.sofkau.integration.database.ConexionDatabase;
import com.sofkau.integration.database.mysql.MySqlOperation;
import com.sofkau.model.Publicacion;
import com.sofkau.util.CommonOperacion.IngresoQuery;

public class PublicacionRepositorio {

    private static MySqlOperation mySqlOperation = ConexionDatabase.getMySqlOperation();

    public static void crearPublicacion(Publicacion publicacion) {
        String query = String.format("INSERT INTO Publicacion (titulo, tipo_publicacion, id_autor, num_paginas, " +
                        "cant_ejemplares, cant_prestados, cant_disponible) VALUES ('%s', '%s', '%s', %d, %d, %d, %d)",
                publicacion.getTitulo(),
                publicacion.getTipo(),
                publicacion.getAutor().getId(),
                publicacion.getNumeroPaginas(),
                publicacion.getCantidadEjemplares(),
                publicacion.getCantidadPrestado(),
                publicacion.getCantidadDisponible());
        IngresoQuery.ejecutarIngresoQuery(query);


    }

}
