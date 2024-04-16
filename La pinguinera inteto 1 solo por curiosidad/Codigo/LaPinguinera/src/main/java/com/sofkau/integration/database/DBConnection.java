package com.sofkau.integration.database;

import com.github.javafaker.Faker;
import com.sofkau.integration.database.ClasesDB.Libros;
import com.sofkau.integration.database.ClasesDB.Prestamos;
import com.sofkau.integration.database.ClasesDB.Publicaciones;
import com.sofkau.integration.database.ClasesDB.Usuarios;
import com.sofkau.integration.database.mysql.MySqlConstants;
import com.sofkau.integration.database.mysql.MySqlOperation;

import java.sql.SQLException;

public class DBConnection {

    private static final MySqlOperation mySqlOperation = new MySqlOperation();
    private static final MySqlConstants mySqlConstant = new MySqlConstants();
    public static Faker faker = new Faker();




    public static void ConnectDB() throws SQLException {

        openConnection();
        Publicaciones.obtenerPublicacionesDisponibles();
        closeConnection();
        System.out.println(faker.numerify("###"));
    }

    public static void openConnection() {
        mySqlOperation.setServer(MySqlConstants.SERVER);
        mySqlOperation.setDataBaseName(MySqlConstants.DATA_BASE_NAME);
        mySqlOperation.setUser(MySqlConstants.USER);
        mySqlOperation.setPassword(MySqlConstants.PASSWORD);
    }

    public static void closeConnection() {
        mySqlOperation.close();
    }

}