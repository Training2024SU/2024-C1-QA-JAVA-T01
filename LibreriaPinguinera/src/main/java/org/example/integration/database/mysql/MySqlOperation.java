package org.example.integration.database.mysql;

import org.example.integration.database.DataBase;

import java.sql.*;

import static org.example.integration.database.mysql.MySqlConstants.*;

public class MySqlOperation implements DataBase {
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    private String sqlStatement;
    private String server;
    private String dataBaseName;
    private String user;
    private String password;

    public String getSqlStatement() {
        return sqlStatement;
    }

    public void setSqlStatement(String sqlStatement) {
        this.sqlStatement = sqlStatement;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void configureDataBaseConnection() {
        try {
            Class.forName(MY_SQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(
                    String.format(CONNECTION_STRING,
                            this.server,
                            this.dataBaseName,
                            this.user,
                            this.password)
            );
            statement = connection.createStatement();

        } catch (Exception e) {
            close();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void executeSqlStatement() {
        try {
            openConnection();
            configureDataBaseConnection();
            resultSet = statement.executeQuery(sqlStatement);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            close();
        }
    }
    @Override
    public void executeSqlStatementVoid(String sentencia) {
        openConnection();
        configureDataBaseConnection();
        this.setSqlStatement(sentencia);
        this.executeSqlStatementVoid();
        close();
    }
    @Override
    public int executeSqlStatementVoidForKeys(String sentencia) {

        openConnection();
        configureDataBaseConnection();
        this.setSqlStatement(sentencia);
        int autoId=this.executeSqlStatementVoidForKeys();
        close();
        return autoId;
    }
    @Override
    public void executeSqlStatementVoid() {
        configureDataBaseConnection();
        try {
            statement.execute(sqlStatement);
        } catch (SQLException e) {
            close();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int executeSqlStatementVoidForKeys() {
        int autoId=0;
        configureDataBaseConnection();
        try {
            statement.execute(sqlStatement, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            autoId = rs.getInt(1);
        } catch (SQLException e) {
            close();
            throw new RuntimeException(e);
        }
        return autoId;
    }

    @Override
    public ResultSet getResulset() {
        return resultSet;
    }

    @Override
    public void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void printResulset() throws SQLException {

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int totalColumnNumber = resultSetMetaData.getColumnCount();
        while (resultSet.next()) {
            for (int columnNumber = 1; columnNumber <= totalColumnNumber; columnNumber++) {
                if (columnNumber > 1) {
                    System.out.print(",\t");
                }
                String columnValue = resultSet.getString(columnNumber);
                System.out.print(resultSetMetaData.getColumnName(columnNumber) + ": " + columnValue);
            }
            System.out.println("");
        }

    }

    public void openConnection() {
        this.setServer(System.getenv("SERVER"));
        this.setDataBaseName(System.getenv("DATA_BASE_NAME"));
        this.setUser(System.getenv("USER"));
        this.setPassword(System.getenv("PASSWORD"));
    }

    public String printColumnValue() throws SQLException {
        int totalColumnNumber = 3;
        String columnValue = "";
        while (resultSet.next()) {
            columnValue = resultSet.getString(totalColumnNumber);
            System.out.print(columnValue);
            System.out.println("");
        }
        return columnValue;
    }
}
