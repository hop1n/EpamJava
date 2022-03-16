package by.epam.lab;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Result {
    private final String login;
    private final String test;
    private final Date date;
    private final int mark;
    private static final SimpleDateFormat SIMPLE_DATE = new SimpleDateFormat(Constants.DATE_FORMAT);

    public Result(String login, String test, Date date, int mark) {
        this.login = login;
        this.test = test;
        this.date = date;
        this.mark = mark;
    }

    public Result(String login, String test, String date, String mark) {
        this(login, test, Date.valueOf(date), (int) (Double.parseDouble(mark) * Constants.CONVERT));
    }

    public String getLogin() {
        return login;
    }

    public String getTest() {
        return test;
    }

    public int getMark() {
        return mark;
    }

    public String getStringMark() {
        return (mark / Constants.CONVERT) + Constants.DOT + (mark % Constants.CONVERT);
    }

    public String getSimpleDate() {
        return SIMPLE_DATE.format(date);
    }

    public String toString() {
        return login + Constants.DELIMITER + test + Constants.DELIMITER +
                getSimpleDate() + Constants.DELIMITER + getStringMark();
    }
}
