package com.sofkau.util.generar;

import com.github.javafaker.Faker;
import com.sofkau.logica.Autor.AutorOperaciones;
import com.sofkau.model.Autor;
import com.sofkau.util.CommonOperacion.GenerateUniqueId;


public class GenerarAutores {

    private static AutorOperaciones autorOp = new AutorOperaciones();
        public static void generateAutores(int numAutores) {
            Faker faker = new Faker();
            for (int i = 0; i < numAutores; i++) {
                String nombre = faker.name().fullName();
                Autor autor = new Autor(GenerateUniqueId.generateID(), nombre);
                autorOp.registrarAutor(autor);
            }
        }
}
