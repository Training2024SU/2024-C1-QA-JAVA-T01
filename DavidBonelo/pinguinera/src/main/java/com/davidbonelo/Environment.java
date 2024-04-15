package com.davidbonelo;

import io.github.cdimascio.dotenv.Dotenv;

public class Environment {
    private static final Dotenv dotenv = Dotenv.configure().load();

    private Environment(){
        throw new IllegalStateException("Utility class");
    }

    /**
     * Reads the .env file in the working directory and sets all its variables to System properties.
     */
    protected static void setup() {
        dotenv.entries().forEach(e -> System.setProperty(e.getKey(), e.getValue()));
    }
}
