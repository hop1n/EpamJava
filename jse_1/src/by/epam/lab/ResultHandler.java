package by.epam.lab;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import static by.epam.lab.Constants.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ResultHandler extends DefaultHandler {
    private enum ResultEnum {
        RESULTS, STUDENT, LOGIN, TESTS, TEST;
    }
    private final List<Result2Task> results = new ArrayList<>();
    private ResultEnum currentEnum;
    private String login;

    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        currentEnum = ResultEnum.valueOf(localName.toUpperCase());
        if (currentEnum == ResultEnum.TEST) {
            results.add(new Result2Task(login, attributes.getValue(NAME_INDEX),
                    attributes.getValue(DATE_INDEX), attributes.getValue(MARK_INDEX)));
        }
    }

    public void printResults() {
        for (Result2Task res : results) {
            System.out.println(res);
        }
    }

    public void task2(){
        ResultSet rs;
        try (Connection cn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD)) {
            CallableStatement addLogins = cn.prepareCall(ADD_LOGINS_QUERY);
            CallableStatement addTests = cn.prepareCall(ADD_TESTS_QUERY);
            CallableStatement addToResults = cn.prepareCall(ADD_TO_RESULTS);
            CallableStatement getLoginId = cn.prepareCall(GET_ID_LOGIN);
            CallableStatement getTestId = cn.prepareCall(GET_ID_TEST);
            Statement st = cn.createStatement();
            st.executeUpdate(CLEAR_LOGINS);
            st.executeUpdate(CLEAR_TESTS);
            st.executeUpdate(CLEAR_RESULTS);
            String[] parts;
            String csvLine;
            String name;
            String test;
            Date date;
            int mark;
            int loginId;
            int testId;
            for(Result2Task result : results) {
                st.executeUpdate(RESET_RESULTS_AUTO_INC);
                st.executeUpdate(RESET_TESTS_AUTO_INC);
                st.executeUpdate(RESET_LOGINS_AUTO_INC);
                name = result.getLogin();
                test = result.getTest();
                date = result.getDate();
                mark = result.getMark();
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
                addToResults.setInt(FOURTH_PARAMETER, mark);
                addToResults.executeUpdate();
            }
            rs = st.executeQuery(GET_AVG_MARK);
            while (rs.next()) {
                System.out.println(rs.getString("name") + DELIMITER + rs.getFloat("mark"));
            }
            rs = st.executeQuery(GET_RESULTS_CURRENT_MONTH);
            List<Result> currentMonthResults = new LinkedList<>();
            while (rs.next()) {
                currentMonthResults.add(new Result(rs.getString("login"), rs.getString("test"),
                        rs.getDate("date"), rs.getInt("mark")));
            }
            for (Result result: currentMonthResults){
                System.out.println(result);
            }
            System.out.println("latest day");
            System.out.println(currentMonthResults.get(currentMonthResults.size()-1));
            System.out.println("data has been added successfully");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String str = new String(ch, start, length).trim();
        if (currentEnum == ResultEnum.LOGIN) {
            if (!str.isEmpty()) {
                login = str;
            }
        }
    }
}
