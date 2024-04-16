package com.sofkau;

import com.sofkau.integration.database.ConexionDatabase;
import com.sofkau.logica.control.ControlIngreso;

import java.sql.SQLException;
import java.util.Scanner;
import static com.sofkau.integration.database.mysql.Constantes.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        //Se abre la conexion
        ConexionDatabase.openConnection();
        ControlIngreso.implementarLogica();
        ConexionDatabase.closeConnection();
    }
}