package by.epam.lab;

import by.epam.lab.exceptions.ConnectionException;
import by.epam.lab.singlerones.DBConnector;

import java.sql.*;

import static by.epam.lab.Constants.*;

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

    public static void loadResults(ResultDao reader) throws ConnectionException {
        try {
            Connection cn;
            PreparedStatement addLogins = null;
            PreparedStatement addTests = null;
            PreparedStatement addToResults = null;
            PreparedStatement getLoginId = null;
            PreparedStatement getTestId = null;
            PreparedStatement getResult = null;
            try {
                cn = DBConnector.getConnection();
                addLogins = cn.prepareStatement(ADD_LOGINS_QUERY);
                addTests = cn.prepareStatement(ADD_TESTS_QUERY);
                addToResults = cn.prepareStatement(ADD_TO_RESULTS);
                getLoginId = cn.prepareStatement(GET_ID_LOGIN);
                getTestId = cn.prepareStatement(GET_ID_TEST);
                getResult = cn.prepareStatement(SELECT_RESULT_QUERY);
                while (reader.hasResult()) {
                    Result result = reader.nextResult();
                    String login = result.getLogin();
                    String test = result.getTest();
                    int idLogin = getId(login, getLoginId, addLogins);
                    int idTest = getId(test, getTestId, addTests);
                    insertResult(idLogin, idTest, result, addToResults);
                }
            } finally {
                DBConnector.closeAllPS(addLogins, addTests, getTestId,
                        addToResults, getLoginId, getResult);
            }
        } catch (SQLException e) {
            throw new ConnectionException(INSERT_EXCEPTION);
        }
    }


    public static void clearTables() {
        try {
            Connection cn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            Statement st = cn.createStatement();
            st.executeUpdate(CLEAR_LOGINS);
            st.executeUpdate(CLEAR_TESTS);
            st.executeUpdate(CLEAR_RESULTS);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
