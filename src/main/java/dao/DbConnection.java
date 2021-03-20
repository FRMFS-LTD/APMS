package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String Host = "127.0.0.1";
    private static final int port = 3306;
    private static final String DB_NAME = "apms_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";


    private static Connection connection ;

    public static Connection getConnection() {
        try{

        connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s",Host,port,DB_NAME),USERNAME,PASSWORD);

        }
        catch(SQLException E){
            E.printStackTrace();
        }

        return connection;
    }



}
