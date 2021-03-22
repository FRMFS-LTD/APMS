package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String Host = "remotemysql.com";
    private static final int port = 3306;
    private static final String DB_NAME = "nZXG3ajglr";
    private static final String USERNAME = "nZXG3ajglr";
    private static final String PASSWORD = "8qowjF0omo";


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
