package by.epam.lab;

import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class Result2Task extends Result {

    public Result2Task(String login, String test, String date, String mark) {
        super(login, test, Date.valueOf(date), (int) (Double.parseDouble(mark) * Constants.CONVERT));
    }

    public String getStringMark() {
        return (getMark() / Constants.CONVERT) + Constants.DOT + (getMark() % Constants.CONVERT);
    }

    @Override
    public String toString() {
        return getLogin() + Constants.DELIMITER + getTest() + Constants.DELIMITER +
                getSimpleDate() + Constants.DELIMITER + getStringMark();
    }
}

