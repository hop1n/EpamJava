package by.gsu.epamlab.singletones;

import java.sql.*;
import by.gsu.epamlab.exceptions.*;
import by.gsu.epamlab.*;

public class DBConnector {
	private final static Connection CONNECTION = buildConnection();
	
	private DBConnector() {
		
	}
	
	private static Connection buildConnection() { 
		final String CLASS_NAME = "com.mysql.cj.jdbc.Driver"; 
		final String DB_URL = "jdbc:mysql://localhost/results?serverTimezone=Europe/Moscow"; 
		final String USER = "user"; 
		final String PASSWORD = "user"; 
		try { 
			Class.forName(CLASS_NAME); 
			return DriverManager.getConnection(DB_URL, USER, PASSWORD); 
		} catch (ClassNotFoundException | SQLException e) { 
			throw new ConnectionException(Constants.INPUT_FILE_NOT_FOUND_ERROR);
		} 
	}
	
	public static void closeResultSet(ResultSet resultSet) { 
		if(resultSet != null) { 
			try { 
				resultSet.close(); 
			} catch (SQLException e) { 
				System.err.println(Constants.RESULT_SET_CLOSE_ERROR + e); 
			} 
		} 
	}
	
	public static void closeStatement(Statement statement) { 
		if(statement != null) { 
			try { 
				statement.close(); 
			} catch (SQLException e) { 
				//System.err.println(Constants.STATEMENT_CLOSE_ERROR + e); 
				e.printStackTrace();
			} 
		} 
	}
	
	public static void closeConnection(Connection cn) { 
		if(cn != null) { 
			try { 
				cn.close(); 
			} catch (SQLException e) { 
				//System.err.println(Constants.CONNECTION_CLOSE_ERROR + e); 
				e.printStackTrace();
			} 
		} 
	}
	
	public static void closeAllPS(PreparedStatement ... preparedStatements) throws SQLException {
		for (PreparedStatement preparedStatement : preparedStatements) {
			if (preparedStatement != null && !preparedStatement.isClosed()) {
				preparedStatement.close();
			}
		}
	}
	
	public static Statement createStatement() throws SQLException {
		return CONNECTION.createStatement();
	}

	public static Connection getConnection() {
		return CONNECTION;
	}
}