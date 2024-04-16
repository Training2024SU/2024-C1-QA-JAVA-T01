package Garcia.Juan.database;

import Garcia.Juan.database.mysql.MySqlOperation;
import java.sql.SQLException;

public class ConexionBD {

    private static final String SERVER = System.getenv("GRADLE_SERVER");
    private static final String DATA_BASE_NAME = System.getenv("GRADLE_DB");
    private static final String USER = System.getenv("GRADLE_USER");
    private static final String PASSWORD = System.getenv("GRADLE_PASS");

    private static MySqlOperation mySqlOperation;

    public ConexionBD(MySqlOperation mySqlOperation) {
        this.mySqlOperation = mySqlOperation;
    }

    public static void openConnection(MySqlOperation mySqlOperation) {
        mySqlOperation.setServer(SERVER);
        mySqlOperation.setDataBaseName(DATA_BASE_NAME);
        mySqlOperation.setUser(USER);
        mySqlOperation.setPassword(PASSWORD);
    }

    public static void closeConnection(MySqlOperation mySqlOperation) {
        mySqlOperation.close();
    }




}
