package org.example.integration.database.persistencia;

import org.example.integration.database.mysql.MySqlOperation;
import org.example.model.AreaGenero;
import org.example.model.EdadSugerida;
import org.example.model.Publicacion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.example.integration.database.mysql.MySqlConstants.*;
import static org.example.integration.database.persistencia.CrudAreaGenero.obtenerGeneroPublicacion;
import static org.example.integration.database.persistencia.CrudEdades.obtenerEdadSugerida;
import static org.example.logica.control.ConstantesLogica.TIPO_PUBLICACION;

public class CrudPublicacion  {
    MySqlOperation mySqlOperation;

    public CrudPublicacion() {
        mySqlOperation = new MySqlOperation();
    }


    public void seleccionarDatos() throws SQLException {
        mySqlOperation.setSqlStatement(String.format(SELECT, TIPO_PUBLICACION));
        mySqlOperation.executeSqlStatement();
        ResultSet resultSet = mySqlOperation.getResulset();
        llenarPublicacion(resultSet);
    }
    public void crearEntidad(Publicacion publicacion) {
        mySqlOperation.executeSqlStatementVoid(String.format(CREATE_PUBLICACION, publicacion.getTitulo(), publicacion.getTipo(), publicacion.getAutor(), publicacion.getNumeroPaginas(),
        publicacion.getCantidadEjemplares(), publicacion.getCantidadPrestado(), publicacion.getCantidadDisponible()));
    }
    public void actualizarPublicacion(Publicacion publicacion){
        mySqlOperation.executeSqlStatementVoid(String.format(UPDATE_PULICACION, publicacion.getTipo(), publicacion.getAutor(), publicacion.getNumeroPaginas(),
        publicacion.getCantidadEjemplares(), publicacion.getCantidadPrestado(), publicacion.getCantidadDisponible(), publicacion.getTitulo()));
    }
    public void eliminarPublicacion(Publicacion publicacion){
        mySqlOperation.executeSqlStatementVoid(String.format(DELETE_PUBLICACION, publicacion.getTitulo()));
    }

    private void llenarPublicacion(ResultSet resultSet) throws SQLException {

        while (resultSet.next()) {
            String titulo = resultSet.getString("titulo");
            String tipo = resultSet.getString("tipo_publicacion");
            String autor = resultSet.getString("autor");
            int numPaginas = resultSet.getInt("num_paginas");
            int cantidadEjemplares = resultSet.getInt("cant_ejemplares");
            int cantidadPrestados = resultSet.getInt("cant_prestados");
            int cantidadDisponibles = resultSet.getInt("cant_disponible");
            List<AreaGenero> generos=obtenerGeneroPublicacion(titulo);
            List<EdadSugerida> edades=obtenerEdadSugerida(titulo);


            Publicacion publicacion = new Publicacion(titulo, tipo, autor, numPaginas, cantidadEjemplares, cantidadPrestados, cantidadDisponibles, generos, edades);
            Publicacion.publicaciones.add(publicacion);
        }
    }
}
