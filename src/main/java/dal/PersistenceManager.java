package dal;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PersistenceManager {

    private static final String PROPS_FILE = "src\\main\\resources\\application.properties";
    private static Connection connection;

    private PersistenceManager(){} //Prevents initialization

    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        if( connection == null || !connection.isValid(5)){

            Properties props = new Properties();
            try (FileInputStream fis = new FileInputStream(PROPS_FILE)){
                props.load(fis);
            }

            String driverClass = props.getProperty("spring.datasource.driver-class-name");
            String dbUrl = props.getProperty("spring.datasource.url");
            String dbLogin = props.getProperty("spring.datasource.username");
            String dbPwd = props.getProperty("spring.datasource.password");

            Class.forName( driverClass );

            connection = DriverManager.getConnection(dbUrl, dbLogin, dbPwd);
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if(connection != null && connection.isValid(2)){
            connection.close();
        }
    }
}
