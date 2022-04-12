import java.sql.*;
import java.sql.Date;
import java.util.*;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.ResultsLoader;
import by.gsu.epamlab.exceptions.ConnectionException;
import by.gsu.epamlab.exceptions.SourceException;
import by.gsu.epamlab.MarkFactory;
import by.gsu.epamlab.Result;
import by.gsu.epamlab.singletones.DBConnector;

public class RunnerLogic {
	public static void execute(String inputFile, MarkFactory markFactory) {
		try {
			//2 Load data from a file results.csv into DB.
			try {
				ResultsLoader.loadResults(markFactory.getImpl(inputFile));
			} catch (SourceException e) {
				System.err.print(e);
			}
			//3 Print a mean value of marks (2 digits after a decimal point) on every student in descending order by a mean value.
			Statement st = null;
			ResultSet rs = null;
			try {
				System.out.println(Constants.TASK_3_TEXT);
				st = DBConnector.getConnection().createStatement();
				rs = st.executeQuery(Constants.TASK_3_QUERY);
				while (rs.next()) {
					System.out.printf(rs.getString(1) + ": " + markFactory.getStringMark(rs.getDouble(2)));
					System.out.println();
				}
			} catch(SQLException e) {
				System.err.print(Constants.TASK_3_SQL_ERROR);
			}
			
			
			//4 Create a LinkedList implementation of tests results for the current month sorting by a date ascending and print it.
			try {
				System.out.println(Constants.TASK_4_TEXT);
				List<Result> results = new LinkedList<Result>();
				rs = st.executeQuery(Constants.TASK_4_QUERY);
				while (rs.next()) {
					results.add(new Result(rs.getString(1), rs.getString(2), rs.getDate(3), markFactory.getMarkFromFactory(rs.getInt(4))));
				}
				if (results.size() == 0) {
					System.out.print(Constants.NOT_FOUND_MESAGE);
				} else {
					for (Result result : results) {
						System.out.println(result);
					}
					
					//5 Print tests results in the latest day of the current month (without SQL request).
					System.out.println(Constants.TASK_5_TEXT);
					ListIterator<Result> li = results.listIterator(results.size());
					if (li.hasPrevious()) {
						Result last = li.previous();
						Date latestDay = last.getDate();
						do {
							System.out.println(last);
						}
						while (li.hasPrevious() && (last = li.previous()).getDate().equals(latestDay));
					}
				}
			} catch(SQLException e) {
				System.err.print(Constants.TASK_4_SQL_ERROR);
			}
		} catch(ConnectionException e) {
			System.err.print(e);
		}
	}
}
