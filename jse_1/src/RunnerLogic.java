import by.epam.lab.DBConnector;
import by.epam.lab.Result;
import by.epam.lab.ResultDao;
import by.epam.lab.ResultsLoader;
import by.epam.lab.exceptions.ConnectionException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import static by.epam.lab.Constants.*;
import static by.epam.lab.Constants.MARK;

public class RunnerLogic {
    public static void execute(ResultDao daoImplementation) {
        try {

            //2 Load data from a file results.csv into DB.
            try {
                ResultsLoader.clearTables();
                ResultsLoader.loadResults(daoImplementation);
            } catch (ConnectionException e) {
                System.err.print(e);
            }

            //3 Print a mean value of marks (2 digits after a decimal point) on every student in descending order by a mean value.
            ResultSet rs = null;
            Statement st = null;
            try {
                st = DBConnector.getConnection().createStatement();
                rs = st.executeQuery(GET_AVG_MARK);
                System.out.println(AVG_RESULT);
                while (rs.next()) {
                    System.out.printf(Locale.ENGLISH, AVG_OUTPUT, rs.getString(NAME), DELIMITER, rs.getFloat(MARK));
                }
            } catch (SQLException e) {
                System.err.print(CONNECTION_FAILED);
            }

            //4 Create a LinkedList implementation of tests results for the current month sorting by a date ascending and print it.
            try {
                rs = st.executeQuery(GET_RESULTS_CURRENT_MONTH);
                List<Result> currentMonthResults = new LinkedList<>();
                System.out.println(TESTS_CURRENT_MONTH);
                while (rs.next()) {
                    currentMonthResults.add(new Result(rs.getString(LOGIN), rs.getString(TEST),
                            rs.getDate(DATE), rs.getInt(MARK)));
                }
                if (currentMonthResults.size() == 0) {
                    System.out.print("data not found");
                } else {
                    for (Result result : currentMonthResults) {
                        System.out.println(result);
                    }

                    //5 Print tests results in the latest day of the current month (without SQL request).
                    System.out.println(LATEST_DAY);
                    for (Result results : currentMonthResults){
                        if (currentMonthResults.get(currentMonthResults.size()-1).getDate().equals(results.getDate())){
                            System.out.println(results);
                        }
                    }
                }
            } catch (SQLException e) {
                System.err.print("sql exception");
            }
        } catch (Exception e) {
            System.err.print(e);
        }
    }
}
