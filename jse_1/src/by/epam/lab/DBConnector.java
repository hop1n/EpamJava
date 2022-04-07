package by.epam.lab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static by.epam.lab.Constants.*;

public class DBConnector {
    private final static Connection connection = buildConnection();

    public static Connection buildConnection() {
        String url = DB_URL;
        String name = USER_NAME;
        String password = PASSWORD;
        Connection cn = null;
        try {
            cn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println(FILE_NOT_FOUND);
        }
        return cn;
    }

    public static Connection getConnection(){
        return connection;
    }

    public void closeConnection(){
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(CLOSE_CONNECTION_FAILED);
            }
        }
    }

}
