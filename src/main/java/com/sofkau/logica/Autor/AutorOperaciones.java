package com.sofkau.logica.Autor;

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
    public void registrarAutor(Autor autor) throws SQLException {
        autor.setId(GenerateUniqueId.generateID());
        AutorRepositorio.crearAutor(autor);
        getAutores();

        if(consultarAutorId(autor.getId()) != null){
            MensajeOperacionBd.crearAutor();
        }else{
            MensajeOperacionBd.crearAutor();
        }
    }



    public static void getAutores() throws SQLException {
        autores = AutorRepositorio.consultarAutores();
    }


    private static Autor consultarAutorId(String id) {
        Optional<Autor> autorVal;
        autorVal = autores.values().stream()
                .filter(autor -> autor.getId().equals(id))
                .findFirst();

       return autorVal.orElse(null);


    }





}