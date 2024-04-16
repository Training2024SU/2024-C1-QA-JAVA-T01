package co.com.sofka.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DataBase {

    public void configureDataBaseConnection();

    public void executeSqlStatement();

    public void executeSqlStatementVoid();

    public ResultSet getResultSet();

    public void close();

    public void printResultSet()throws SQLException;
}