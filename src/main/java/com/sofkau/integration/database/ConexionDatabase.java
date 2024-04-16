package com.sofkau.integration.database;
import com.sofkau.integration.database.mysql.MySqlOperation;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ConexionDatabase {


    //Instanciar objeto que me permite leer desde archivo .env
    static Dotenv dotenv = Dotenv.configure().load();

    private static final String server =  dotenv.get("database.server");
    private static final String databaseName = dotenv.get("database.databaseName");
    private static final String user = dotenv.get("database.user");
    private static final String password = dotenv.get("database.password");

    private static final MySqlOperation mySqlOperation = new MySqlOperation();

    public ConexionDatabase() {

    }

    public static void openConnection(){
        mySqlOperation.setServer(server);
        mySqlOperation.setDataBaseName(databaseName);
        mySqlOperation.setUser(user);
        mySqlOperation.setPassword(password);
        System.out.println("Se conecta a la BD"+databaseName);
    }

    public static Connection getConnection (){
        return mySqlOperation.getConnection();
    }

    public static MySqlOperation getMySqlOperation(){
        return mySqlOperation;
    }
    public void closeConnection() throws SQLException {
        mySqlOperation.close();
    }

}

