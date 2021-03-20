package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String Host = "sql11.freesqldatabase.com";
    private static final int port = 3306;
    private static final String DB_NAME = "sql11400255";
    private static final String USERNAME = "sql11400255";
    private static final String PASSWORD = "3ELHQYcQC6";


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
