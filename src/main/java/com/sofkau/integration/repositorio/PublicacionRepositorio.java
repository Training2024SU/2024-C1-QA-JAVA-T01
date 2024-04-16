package com.sofkau.integration.repositorio;

import com.sofkau.integration.database.ConexionDatabase;
import com.sofkau.integration.database.mysql.MySqlOperation;
import com.sofkau.logica.Autor.AutorOperaciones;
import com.sofkau.model.Autor;
import com.sofkau.model.Publicacion;
import com.sofkau.util.CommonOperacion.IngresoQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

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

    public static HashMap<String, Publicacion> consultarPublicaciones() {
        HashMap<String, Publicacion> publicaciones = new HashMap<>();
        String query = "SELECT * FROM Publicacion";

        IngresoQuery.ejecutarConsultaQuery(query);
        ResultSet resultSet = mySqlOperation.getResulset();

        try {
            while (resultSet.next()) {
                String titulo = resultSet.getString("titulo");
                String tipo = resultSet.getString("tipo_publicacion");
                String idAutor = resultSet.getString("id_autor");
                int numPaginas = resultSet.getInt("num_paginas");
                int cantEjemplares = resultSet.getInt("cant_ejemplares");
                int cantPrestados = resultSet.getInt("cant_prestados");
                int cantDisponible = resultSet.getInt("cant_disponible");

                AutorOperaciones autorOp = new AutorOperaciones();

                Publicacion publicacion = new Publicacion();
                publicacion.setTitulo(titulo);
                publicacion.setTipo(tipo);
                publicacion.setAutor(autorOp.buscarAutorId(idAutor));
                publicacion.setNumeroPaginas(numPaginas);
                publicacion.setCantidadEjemplares(cantEjemplares);
                publicacion.setCantidadPrestado(cantPrestados);
                publicacion.setCantidadDisponible(cantDisponible);

                publicaciones.put(titulo, publicacion); // Utilizamos el título como clave del mapa
            }
        } catch ( SQLException e) {
            throw new RuntimeException(e);
        }

        return publicaciones;
    }

}
