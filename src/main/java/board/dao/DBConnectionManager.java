package board.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class DBConnectionManager {

    private Connection connection;
    private String DB_DRIVER;//= "org.postgresql.Driver";
    private String DB_URL;// = "jdbc:postgresql://localhost:5432/first_project";
    private String DB_USERNAME;// = "postgres";
    private String DB_PASSWORD;// = "postgres";

    private static Logger log = Logger.getLogger(DBConnectionManager.class.getName());


    public DBConnectionManager() {
        loadProperties();
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            //log.error("Driver not found\n", e);
        }
    }

    private void loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(DBConnectionManager.class.getResourceAsStream("/db.properties"));
            DB_USERNAME = properties.getProperty("user");
            DB_PASSWORD = properties.getProperty("password");
            DB_URL = properties.getProperty("url");
            DB_DRIVER = properties.getProperty("driver");
        } catch (IOException e) {
            //log.error("Can't read the file\n", e);
            throw new ExceptionDBProperties("Can't read the file ", e);
        }
    }
    public Connection getConnection()throws ClassNotFoundException, SQLException{
        Class.forName(DB_DRIVER);
         return connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

    }
}