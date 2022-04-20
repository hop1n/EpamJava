package by.epam.lab.singlerones;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static by.epam.lab.Constants.*;

public class DBConnector {
    private static Connection connection;

    static {
        try {
            connection = buildConnection();
        } catch (ConnectException e) {
            System.out.println(e);
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
            connection = buildConnection();
        }
        return connection;
    }

    public void closeConnection() throws ConnectException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new ConnectException(CLOSE_CONNECTION_FAILED);
            }
        }
    }

    public static void closeAllPS(PreparedStatement... preparedStatements) throws SQLException {
        for (PreparedStatement preparedStatement : preparedStatements) {
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
        }
    }

}
