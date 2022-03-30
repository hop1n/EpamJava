package by.epam.lab;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static by.epam.lab.Constants.*;

public class RunnerLogic {

    public static void addResult(String[] parts) {
        ResultSet rs = null;
        String name = parts[ZERO_PARAMETER];
        String test = parts[FIRST_PARAMETER];
        Date date = Date.valueOf(parts[SECOND_PARAMETER]);
        int mark;
        if (parts[THIRD_PARAMETER].contains(".")){
            mark = (int) Double.parseDouble(parts[THIRD_PARAMETER]) * 10;
        } else{
            mark = Integer.parseInt(parts[THIRD_PARAMETER]);
        }
        int loginId;
        int testId;
        try (Connection cn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);) {
            CallableStatement addLogins = cn.prepareCall(ADD_LOGINS_QUERY);
            CallableStatement addTests = cn.prepareCall(ADD_TESTS_QUERY);
            CallableStatement addToResults = cn.prepareCall(ADD_TO_RESULTS);
            CallableStatement getLoginId = cn.prepareCall(GET_ID_LOGIN);
            CallableStatement getTestId = cn.prepareCall(GET_ID_TEST);
            Statement st = cn.createStatement();
            st.executeUpdate(RESET_RESULTS_AUTO_INC);
            st.executeUpdate(RESET_TESTS_AUTO_INC);
            st.executeUpdate(RESET_LOGINS_AUTO_INC);
            addLogins.setString(FIRST_PARAMETER, name);
            addLogins.executeUpdate();
            getLoginId.setString(FIRST_PARAMETER, name);
            rs = getLoginId.executeQuery();
            rs.next();
            loginId = rs.getInt(ID_LOGIN);
            addTests.setString(FIRST_PARAMETER, test);
            addTests.executeUpdate();
            getTestId.setString(FIRST_PARAMETER, test);
            rs = getTestId.executeQuery();
            rs.next();
            testId = rs.getInt(ID_TESTS);
            addToResults.setInt(FIRST_PARAMETER, loginId);
            addToResults.setInt(SECOND_PARAMETER, testId);
            addToResults.setDate(THIRD_PARAMETER, date);
            addToResults.setDouble(FOURTH_PARAMETER, mark);
            addToResults.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void otherTasks() {
        try (Connection cn = DBConnector.getConnection()) {
            Statement st = cn.createStatement();
            ResultSet rs;
            rs = st.executeQuery(GET_AVG_MARK);
            System.out.println("Avg result for each student");
            while (rs.next()) {
                System.out.println(rs.getString("name") + DELIMITER + rs.getFloat("mark"));
            }
            rs = st.executeQuery(GET_RESULTS_CURRENT_MONTH);
            List<Result> currentMonthResults = new LinkedList<>();
            System.out.println("\nTests of current month");
            while (rs.next()) {
                currentMonthResults.add(new Result(rs.getString("login"), rs.getString("test"),
                        rs.getDate("date"), rs.getInt("mark")));
            }
            for (Result result : currentMonthResults) {
                System.out.println(result);
            }
            System.out.println("\nlatest day");
            System.out.println(currentMonthResults.get(currentMonthResults.size() - 1));
            System.out.println("\ndata has been added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
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
