package by.epam.lab.services;

import by.epam.lab.DAO.ResultDao;
import by.epam.lab.beans.Result;
import by.epam.lab.exceptions.DBException;
import by.epam.lab.singlerones.DBConnector;

import java.net.ConnectException;
import java.sql.*;

import static by.epam.lab.services.Constants.*;

public class ResultsLoader {

    private static int getId(String value, PreparedStatement select, PreparedStatement insert) throws SQLException {
        final int SELECT_LOGIN_INDEX = 1;
        final int ID_INDEX = 1;
        ResultSet rs = null;
        try {
            select.setString(SELECT_LOGIN_INDEX, value);
            rs = select.executeQuery();
            if (!rs.next()) {
                insert.setString(SELECT_LOGIN_INDEX, value);
                insert.executeUpdate();
                rs = select.executeQuery();
                rs.next();
            }
            return rs.getInt(ID_INDEX);
        } finally {
            rs.close();
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

    public static void loadResults(ResultDao reader) throws DBException {
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
            throw new DBException(INSERT_EXCEPTION);
        } catch (ConnectException e) {
            throw new DBException(CONNECTION_FAILED);
        }
    }


    public static void clearTables() {
        try {
            Connection cn = DBConnector.getConnection();
            Statement st = cn.createStatement();
            st.executeUpdate(CLEAR_LOGINS);
            st.executeUpdate(CLEAR_TESTS);
            st.executeUpdate(CLEAR_RESULTS);
        } catch (SQLException | ConnectException e) {
            throw new DBException(OPERATION_FAILED);
        }
    }
}
