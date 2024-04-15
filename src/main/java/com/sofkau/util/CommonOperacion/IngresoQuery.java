package com.sofkau.util.CommonOperacion;

import com.sofkau.integration.database.ConexionDatabase;
import com.sofkau.integration.database.mysql.MySqlOperation;

public class IngresoQuery {
    private static MySqlOperation mySqlOperation = ConexionDatabase.getMySqlOperation();

    // Metodo para ejecutar querys de ingreso de datos
    public static void ejecutarIngresoQuery(String query){
        try {
            mySqlOperation.setSqlStatement(query);
            mySqlOperation.executeSqlStatementVoid();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Metodo para ejecutar consultas query
    public static void ejecutarConsultaQuery(String query){
        try {
            mySqlOperation.setSqlStatement(query);
            mySqlOperation.executeSqlStatement();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
