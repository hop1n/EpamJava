package by.epam.lab;

import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import static by.epam.lab.Constants.*;
import static by.epam.lab.Constants.THIRD_PARAMETER;

public class ResultDecimal extends Result {

    public ResultDecimal(String[] parts){
        this(parts[ZERO_PARAMETER], parts[FIRST_PARAMETER], parts[SECOND_PARAMETER], parts[THIRD_PARAMETER]);
    }

    public ResultDecimal(String login, String test, String date, String mark) {
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

