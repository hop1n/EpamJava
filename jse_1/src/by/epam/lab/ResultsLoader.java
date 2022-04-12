package by.gsu.epamlab;

import java.sql.*;

import by.gsu.epamlab.exceptions.ConnectionException;
import by.gsu.epamlab.singletones.DBConnector;

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
			DBConnector.closeResultSet(rs);
		}
	}
	
	private static void insertResult(int idLogin, int idTest, Result result, PreparedStatement selectResult, 
			PreparedStatement insertResult) throws SQLException {
		final int ID_LOGIN = 1;
		final int ID_TEST = 2;
		final int ID_DATE = 3;
		final int ID_MARK = 4;
		Date date = result.getDate();
		int mark = result.getMark().getValue();
		selectResult.setInt(ID_LOGIN, idLogin);
		selectResult.setInt(ID_TEST, idTest);
		selectResult.setDate(ID_DATE, date);
		selectResult.setInt(ID_MARK, mark);
		if (!selectResult.executeQuery().next()) {
			insertResult.setInt(ID_LOGIN, idLogin);
			insertResult.setInt(ID_TEST, idTest);
			insertResult.setDate(ID_DATE, date);  
			insertResult.setInt(ID_MARK, mark);
			insertResult.executeUpdate();
		}
	}
	
	public static void loadResults(IResultDAO reader) {
		try {
			Connection cn = null;
			PreparedStatement selectLogin = null;
			PreparedStatement insertLogin = null;
			PreparedStatement selectTest = null;
			PreparedStatement insertTest = null;
			PreparedStatement selectResult = null;
			PreparedStatement insertResult = null;
			try {
				cn = DBConnector.getConnection();
				selectLogin = cn.prepareStatement(Constants.SELECT_LOGIN_QUERY);
				insertLogin = cn.prepareStatement(Constants.INSERT_LOGIN_QUERY);
				selectTest = cn.prepareStatement(Constants.SELECT_TEST_QUERY);
				insertTest = cn.prepareStatement(Constants.INSERT_TEST_QUERY);
				selectResult = cn.prepareStatement(Constants.SELECT_RESULT_QUERY);
				insertResult = cn.prepareStatement(Constants.INSERT_RESULT_QUERY);
				while (reader.hasResult()) {
					Result result = reader.nextResult();
					String login = result.getLogin();
					String test = result.getTest();
					int idLogin = getId(login, selectLogin, insertLogin);
					int idTest = getId(test, selectTest, insertTest);
					insertResult(idLogin, idTest, result, selectResult, insertResult);
				}
			} finally {
				DBConnector.closeAllPS(selectLogin, insertLogin, selectTest, insertTest, insertResult);
			}
		} catch (SQLException e) {
			throw new ConnectionException(Constants.INSERT_SQL_ERROR);
		}
	}
}
