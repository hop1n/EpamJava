package by.epam.lab.services;

import by.epam.lab.DAO.ResultDao;
import by.epam.lab.beans.Result;
import by.epam.lab.services.ResultsLoader;
import by.epam.lab.singlerones.DBConnector;
import by.epam.lab.exceptions.DBException;

import java.net.ConnectException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import static by.epam.lab.services.Constants.*;

public class RunnerLogic {
    public static void execute(ResultDao daoImplementation, ResultKind resultKind) {
        try {

            //2 Load data from a file results.csv into DB.
            try {
                ResultsLoader.clearTables();
                ResultsLoader.loadResults(daoImplementation);
            } catch (DBException e) {
                System.out.println(e.getMessage());
                System.err.print(e);
            }

            //3 Print a mean value of marks (2 digits after a decimal point) on every student in descending order by a mean value.
            try (Statement st = DBConnector.getConnection().createStatement(); ResultSet rs = st.executeQuery(GET_AVG_MARK)){
                System.out.println(AVG_RESULT);
                while (rs.next()) {
                    System.out.printf(Locale.ENGLISH, AVG_OUTPUT, rs.getString(NAME), DELIMITER, rs.getFloat(MARK));
                }
            } catch (SQLException e) {
                System.err.print(CONNECTION_FAILED + e.getMessage());
            }

            //4 Create a LinkedList implementation of tests results for the current month sorting by a date ascending and print it.
            try(Statement st = DBConnector.getConnection().createStatement(); ResultSet rs = st.executeQuery(GET_RESULTS_CURRENT_MONTH)) {
                List<Result> currentMonthResults = new LinkedList<>();
                System.out.println(TESTS_CURRENT_MONTH);
                while (rs.next()) {
                    Result result = resultKind.getResult(rs.getString(LOGIN), rs.getString(TEST),
                            rs.getDate(DATE), rs.getInt(MARK));
                    currentMonthResults.add(result);
                }
                if (currentMonthResults.size() == 0) {
                    System.out.print(NO_DATA);
                } else {
                    for (Result result : currentMonthResults) {
                        System.out.println(result);
                    }

                    //5 Print tests results in the latest day of the current month (without SQL request).
                    System.out.println(LATEST_DAY);
                    for (Result result : currentMonthResults){
                        if (currentMonthResults.get(0).getDate().equals(result.getDate())){
                            System.out.println(result);
                        }
                    }
                }
            } catch (SQLException e) {
                System.err.print(GET_DATA_FAIL);
            }
        } catch (ConnectException e) {
            System.err.print(CONNECTION_FAILED + e.getMessage());
        }
        finally {
            try {
                DBConnector.close();
            } catch (ConnectException e){
                System.out.println(CONNECTION_CLOSE_FAILED + e.getMessage());
            }
        }
    }
}
