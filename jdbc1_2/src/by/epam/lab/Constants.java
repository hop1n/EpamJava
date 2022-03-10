package by.epam.lab;

public class Constants {
    public final static String CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    public final static String DB_URL = "jdbc:mysql://localhost:3306/segments";
    public final static String USER_NAME = "root";
    public final static String PASSWORD = "mysqlpass";
    public final static String GET_LENNUM_TABLE = "SELECT ROUND(ABS(x1 - x2)) AS len, Count(*) AS num FROM Coordinates GROUP BY len ORDER BY len ASC";
    public final static String LEN = "len";
    public final static String NUM = "num";
    public final static String CLEAR_FREQ_TABLE = "DELETE FROM Frequencies";
    public final static String INSERT_TO_FREQ_TABLE = "INSERT INTO Frequencies(len, num) VALUES (?, ?)";
    public final static String GET_FREQ_BY_EXPRESSION = "SELECT * FROM Frequencies WHERE len > num";
    public final static String SOUT_3_EX = "records where len>num: ";
    public final static String DELIMITER = ";";
}