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
        this(login, test, Date.valueOf(date), Math.round(Float.parseFloat(mark)));
    }

    public String getDate() {
        return date.toString();
    }

    public String toString(){
        return login + Constants.DELIMITER + test + Constants.DELIMITER +
                new SimpleDateFormat(Constants.DATE_FORMAT).format(date) + Constants.DELIMITER + mark;
    }
}
