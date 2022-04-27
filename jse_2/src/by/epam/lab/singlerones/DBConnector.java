package by.epam.lab.singlerones;

import by.epam.lab.exceptions.ConnectionException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static by.epam.lab.services.Constants.*;

public class DBConnector {
    private static final Connection CONNECTION;

    static {
        CONNECTION = buildConnection();
    }


    public static Connection buildConnection() throws ConnectionException {
        Connection cn;
        try {
            cn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            throw new ConnectionException(CONNECTION_FAILED);
        }
        return cn;
    }

    public static Connection getConnection() throws ConnectionException {
        if (CONNECTION == null) {
            throw new ConnectionException(CONNECTION_IS_CLOSED);
        } else {
            return CONNECTION;
        }
    }


    public static void close() throws ConnectionException {
        try {
            if (CONNECTION != null) {
                CONNECTION.close();
            }
        } catch (SQLException e) {
            throw new ConnectionException(CONNECTION_CLOSE_FAILED + e.getMessage());
        }
    }
}
