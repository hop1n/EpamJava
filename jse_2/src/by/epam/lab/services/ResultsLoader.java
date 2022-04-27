package by.epam.lab.services;

import by.epam.lab.DAO.ResultDao;
import by.epam.lab.beans.Result;
import by.epam.lab.exceptions.ConnectionException;
import by.epam.lab.singlerones.DBConnector;

import java.net.ConnectException;
import java.sql.*;

import static by.epam.lab.services.Constants.*;

public class ResultsLoader {

    private static int getId(String value, PreparedStatement select,
                             PreparedStatement insert) throws SQLException {
        final int SELECT_LOGIN_INDEX = 1;
        final int ID_INDEX = 1;
        select.setString(SELECT_LOGIN_INDEX, value);
        try (ResultSet rs = select.executeQuery()) {
            if (!rs.next()) {
                insert.setString(SELECT_LOGIN_INDEX, value);
                insert.executeUpdate();
                try (ResultSet rs1 = select.executeQuery()) {
                    rs1.next();
                    return rs1.getInt(ID_INDEX);
                }
            } else {
                return rs.getInt(ID_INDEX);
            }
        }
    }

    private static void insertResult(int idLogin, int idTest, Result result,
                                     PreparedStatement insertResult) throws SQLException {
        final int ID_LOGIN = 1;
        final int ID_TEST = 2;
        final int ID_DATE = 3;
        final int ID_MARK = 4;
        Date date = result.getDate();
        int mark = result.getMark();
        insertResult.setInt(ID_LOGIN, idLogin);
        insertResult.setInt(ID_TEST, idTest);
        insertResult.setDate(ID_DATE, date);
        insertResult.setInt(ID_MARK, mark);
        insertResult.executeUpdate();
    }

    public static void loadResults(ResultDao reader) throws ConnectionException {
        try {
            Connection cn = DBConnector.getConnection();
            try (PreparedStatement addLogins = cn.prepareStatement(ADD_LOGINS_QUERY);
                 PreparedStatement addTests = cn.prepareStatement(ADD_TESTS_QUERY);
                 PreparedStatement addToResults = cn.prepareStatement(ADD_TO_RESULTS);
                 PreparedStatement getLoginId = cn.prepareStatement(GET_ID_LOGIN);
                 PreparedStatement getTestId = cn.prepareStatement(GET_ID_TEST);) {
                while (reader.hasResult()) {
                    Result result = reader.nextResult();
                    String login = result.getLogin();
                    String test = result.getTest();
                    int idLogin = getId(login, getLoginId, addLogins);
                    int idTest = getId(test, getTestId, addTests);
                    insertResult(idLogin, idTest, result, addToResults);
                }
            }
        } catch (SQLException e) {
            throw new ConnectionException(LOAD_ERROR);
        }
    }


    public static void clearTables() throws ConnectException {
        try {
            Connection cn = DBConnector.getConnection();
            Statement st = cn.createStatement();
            st.executeUpdate(CLEAR_LOGINS);
            st.executeUpdate(CLEAR_TESTS);
            st.executeUpdate(CLEAR_RESULTS);
        } catch (SQLException e) {
            throw new ConnectException(OPERATION_FAILED);
        }
    }
}
