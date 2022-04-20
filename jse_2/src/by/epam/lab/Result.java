package by.epam.lab;

import java.sql.Date;
import java.text.SimpleDateFormat;

import static by.epam.lab.Constants.*;

public class Result {
    private final String login;
    private final String test;
    private final Date date;
    private final int mark;
    private static final SimpleDateFormat SIMPLE_DATE = new SimpleDateFormat(Constants.DATE_FORMAT);

    public Result() {
        this(EMPTY_LINE, EMPTY_LINE, null, 0);
    }

    public Result(String login, String test, Date date, int mark) {
        this.login = login;
        this.test = test;
        this.date = date;
        this.mark = mark;
    }

    public Result(String login, String test, Date date, String mark) {
        this.login = login;
        this.test = test;
        this.date = date;
        if(mark.contains(DOT)){
            mark = mark.replace(".", "");
        }
        this.mark = (int)Double.parseDouble(mark);
    }

    public String getLogin() {
        return login;
    }

    public Date getDate() {
        return date;
    }

    public String getTest() {
        return test;
    }

    public int getMark() {
        return mark;
    }

    public String getSimpleDate() {
        return SIMPLE_DATE.format(date);
    }

    protected String markToString(){
        return String.valueOf(mark / CONVERT);
    }

    public String toString() {
        return login + Constants.DELIMITER + test + Constants.DELIMITER +
                getSimpleDate() + Constants.DELIMITER + markToString();
    }
}
