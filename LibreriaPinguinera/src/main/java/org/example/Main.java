package org.example;

import org.example.integration.database.mysql.MySqlOperation;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.example.integration.database.mysql.MySqlConstants.SERVER;
import static org.example.logica.control.MetodosMain.implementarLogica;

public class Main {

    public static void main(String[] args) {

        try{
            implementarLogica();
        }catch (Exception e){
            System.out.println("Error general: "+e.getMessage());
        }

    }
}