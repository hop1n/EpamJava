package by.epam.lab.singlerones;

import by.epam.lab.exceptions.DBException;

import java.io.Closeable;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static by.epam.lab.services.Constants.*;

public class DBConnector implements Closeable {
    private static final Connection connection;

    static {
        try {
            connection = buildConnection();
        } catch (ConnectException e) {
            System.out.println(e.getMessage());
            throw new DBException(CONNECTION_FAILED);
        }
    }


    public static Connection buildConnection() throws ConnectException {
        Connection cn;
        try {
            cn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            throw new ConnectException(CONNECTION_FAILED);
        }
        return cn;
    }

    public static Connection getConnection() throws ConnectException {
        if (connection == null){
            throw new ConnectException(CONNECTION_IS_CLOSED);
        } else {
            return connection;
        }
    }

    public void close() throws ConnectException {
        try{
            if (connection != null){
                connection.close();
            }
        }catch (SQLException e){
            throw new ConnectException(CONNECTION_CLOSE_FAILED + e.getMessage());
        }
    }
}
