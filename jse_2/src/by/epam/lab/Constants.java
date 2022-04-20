package by.epam.lab;

public class Constants {
    public final static int NAME_INDEX = 0;
    public final static int DATE_INDEX = 1;
    public final static int MARK_INDEX = 2;
    public final static int FIRST_PARAMETER = 1;
    public final static int SECOND_PARAMETER = 2;
    public final static int ZERO_PARAMETER = 0;
    public final static int THIRD_PARAMETER = 3;
    public final static int CONVERT = 10;
    public final static String DB_URL = "jdbc:mysql://localhost:3306/results";
    public final static String USER_NAME = "jse";
    public final static String PASSWORD = "jse";
    public final static String DELIMITER = ";";
    public final static String GET_ID_TEST = "SELECT idTest FROM tests where name = ?";
    public final static String ADD_LOGINS_QUERY = "{call addLogins(?)}";
    public final static String ADD_TESTS_QUERY = "{call addTests(?)}";
//    public final static String ADD_LOGINS_QUERY = "insert ignore into logins (name) values (?)";
//    public final static String ADD_TESTS_QUERY = "insert ignore into tests (name) values (?)";
    public final static String ADD_TO_RESULTS = "INSERT INTO results (loginId, testId, dat, mark) VALUES (?, ?, ?, ?)";
    public final static String SELECT_RESULT_QUERY = "SELECT * FROM results WHERE loginId = ? AND testId = ? AND date = ? AND mark = ?";
    public final static String GET_ID_LOGIN = "SELECT idLogin FROM logins where name = ?";
    public final static String GET_AVG_MARK = "SELECT logins.name, AVG(results.mark)/10 AS mark\n" +
            "      FROM results INNER JOIN logins\n" +
            "\t     ON results.loginId = logins.idLogin group by name order by mark desc";
    public final static String CLEAR_LOGINS = "delete from logins;";
    public final static String CLEAR_RESULTS = "delete from results;";
    public final static String CLEAR_TESTS = "delete from tests;";
    public final static String DOT = ".";
    public final static String DATE_FORMAT = "dd.MM.yyyy";
    public final static String GET_RESULTS_CURRENT_MONTH = "SELECT logins.name as login," +
            " tests.name as test, results.dat as date , results.mark as mark\n" +
            "      FROM results INNER JOIN logins INNER JOIN tests\n" +
            "\t     ON results.loginId = logins.idLogin and results.testId = tests.idTest WHERE" +
            " MONTH(`dat`) = MONTH(NOW()) AND YEAR(`dat`) = YEAR(NOW()) order by date desc";
    public final static String CSV_PATH = "src/results.csv";
    public final static String XML_PATH = "src/results.xml";
    public final static String CSV2_PATH = "src/results2.csv";
    public final static String AVG_RESULT = "Avg result for each student";
    public final static String TESTS_CURRENT_MONTH = "\nTests of current month";
    public final static String NAME = "name";
    public final static String LOGIN = "login";
    public final static String TEST = "test";
    public final static String DATE = "date";
    public final static String MARK = "mark";
    public final static String LATEST_DAY = "\nlatest day";
    public final static String AVG_OUTPUT = "%s%s%.2f\n";
    public final static String FILE_NOT_FOUND = "Input file is not found";
    public final static String CLOSE_CONNECTION_FAILED = "field close connection";
    public final static String CONNECTION_FAILED = "field connection";
    public final static String EMPTY_LINE = "";
    public final static String SAX_EXCEPTION = "SAX Parser exc";
    public final static String IO_EXCEPTION = "IOException";
    public final static String INSERT_EXCEPTION = "Insert sql error";
    public final static String NO_DATA = "data not found";
    public final static String SQL_EXCEPTION = "sql exception";
    public final static String OPERATION_FAILED = "sql operation failed";

}
