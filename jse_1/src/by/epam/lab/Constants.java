package by.epam.lab;

public class Constants {
    public final static int NAME_INDEX = 0;
    public final static int DATE_INDEX = 1;
    public final static int MARK_INDEX = 2;
    public final static String DB_URL = "jdbc:mysql://localhost:3306/results";
    public final static String USER_NAME = "root";
    public final static String PASSWORD = "mysqlpass";
    public final static String CSV_PATH = "src/results.csv";
    public final static String ID_LOGIN = "idLogin";
    public final static String ID_TESTS = "idTest";
    public final static String DELIMITER = ";";
    public final static int ZERO_PART = 0;
    public final static int FIRST_PART = 1;
    public final static int SECOND_PART = 2;
    public final static int THIRD_PART = 3;
    public final static int FIRST_PARAMETER = 1;
    public final static int SECOND_PARAMETER = 2;
    public final static int ZERO_PARAMETER = 0;
    public final static int THIRD_PARAMETER = 3;
    public final static int  FOURTH_PARAMETER = 4;
    public final static String GET_ID_TEST = "SELECT idTest FROM tests where name = ?";
    public final static String ADD_LOGINS_QUERY = "{call addLogins(?)}";
    public final static String ADD_TESTS_QUERY = "{call addTests(?)}";
    public final static String ADD_TO_RESULTS = "INSERT INTO results (loginId, testId, dat, mark) VALUES (?, ?, ?, ?)";
    public final static String GET_ID_LOGIN = "SELECT idLogin FROM logins where name = ?";
    public final static String RESET_RESULTS_AUTO_INC = "ALTER TABLE results AUTO_INCREMENT = 1";
    public final static String RESET_TESTS_AUTO_INC = "ALTER TABLE tests AUTO_INCREMENT = 1";
    public final static String RESET_LOGINS_AUTO_INC = "ALTER TABLE logins AUTO_INCREMENT = 1";
    public final static String GET_AVG_MARK = "SELECT logins.name, AVG(results.mark) AS mark\n" +
            "      FROM results INNER JOIN logins\n" +
            "\t     ON results.loginId = logins.idLogin group by name order by mark desc";
    public final static String CLEAR_LOGINS = "delete from logins;";
    public final static String CLEAR_RESULTS = "delete from results;";
    public final static String CLEAR_TESTS = "delete from tests;";
    public final static String DOT = ".";
    public final static int CONVERT = 10;
    public final static String DATE_FORMAT = "dd.MM.yyyy";
    public final static String GET_RESULTS_CURRENT_MONTH = "SELECT logins.name as login," +
            " tests.name as test, results.dat as date , results.mark as mark\n" +
            "      FROM results INNER JOIN logins INNER JOIN tests\n" +
            "\t     ON results.loginId = logins.idLogin and results.testId = tests.idTest WHERE" +
            " MONTH(`dat`) = MONTH(NOW()) AND YEAR(`dat`) = YEAR(NOW()) order by date asc";
    public final static String XML_PATH = "src/results.xml";
    public final static String CSV2_PATH = "src/results2.csv";
}
