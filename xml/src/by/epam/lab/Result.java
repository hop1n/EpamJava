package by.epam.lab;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Result {
    private final String login;
    private final String test;
    private final Date date;
    private final int mark;

    public Result(String login, String test, Date date, int mark){
        this.login = login;
        this.test = test;
        this.date = date;
        this.mark = mark;
    }

    public Result(String login, String test, String date, String mark) {
        this(login, test, Date.valueOf(date), (int)(Float.parseFloat(mark)*Constants.CONVERT_TO_INT));
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

    public String getDate() {
        return new SimpleDateFormat(Constants.DATE_FORMAT).format(date);
    }

    public String toString(){
        return login + Constants.DELIMITER + test + Constants.DELIMITER +
                getDate() + Constants.DELIMITER + mark/10 + Constants.DOT + mark%10;
    }
}
