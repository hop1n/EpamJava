import by.epam.lab.Result;

import javax.sound.sampled.Line;

import static by.epam.lab.Constants.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        ResultSet rs = null;
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
            Scanner sc = new Scanner(new FileReader(CSV_PATH));
            while (sc.hasNextLine()) {
                st.executeUpdate(RESET_RESULTS_AUTO_INC);
                st.executeUpdate(RESET_TESTS_AUTO_INC);
                st.executeUpdate(RESET_LOGINS_AUTO_INC);
                csvLine = sc.nextLine();
                parts = csvLine.split(DELIMITER);
                name = parts[ZERO_PART];
                test = parts[FIRST_PART];
                date = Date.valueOf(parts[SECOND_PART]);
                mark = Integer.parseInt(parts[THIRD_PART]);
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
        } catch (SQLException | FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
