package com.sofkau.integration.database;

import com.sofkau.integration.database.ClasesDB.Publicaciones;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DataBase {

    public void configureDataBaseConnection();

    public void executeSqlStatement();

    public void executeSqlStatementVoid();

    public ResultSet getResulset();

    ResultSet executeSqlStatementReturnResultSet() throws SQLException;

    public void close();

    public void printResulset()throws SQLException;
}
