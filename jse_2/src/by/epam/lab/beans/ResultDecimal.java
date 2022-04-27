package by.epam.lab.beans;

import java.sql.Date;

import static by.epam.lab.services.Constants.CONVERT;
import static by.epam.lab.services.Constants.DOT;

public class ResultDecimal extends Result {

    public ResultDecimal(String login, String test, Date date, int mark) {
        super(login, test, date, mark);
    }

    @Override
    protected String markToString() {
        return (getMark() / CONVERT) + DOT + (getMark() % CONVERT);
    }
}
