package by.epam.lab.beans;

import java.sql.Date;

import static by.epam.lab.services.Constants.*;


public class ResultHalf extends Result {

    public ResultHalf(String login, String test, Date date, int mark) {
        super(login, test, date, mark);
    }

    @Override
    public String markToString() {
        return getMark() / CONVERT + (getMark() % 10 == 0 ? EMPTY_LINE : HALF);
    }
}