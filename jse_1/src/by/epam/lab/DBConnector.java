package by.epam.lab;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static by.epam.lab.Constants.*;

public class DBConnector {
    private Connection connection;

    public void buildConnection() {
        String url = DB_URL;
        String name = USER_NAME;
        String password = PASSWORD;
        try {
            connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Input file is not found");
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public void closeConnection(){
        try{
            connection.close();
        } catch (SQLException e) {
            System.out.println("field close connection");
        }
    }

}
