package by.epam.lab;

import java.sql.Date;

import static by.epam.lab.Constants.CONVERT;
import static by.epam.lab.Constants.DOT;

public class ResultDecimal extends Result {

    ResultDecimal(String login, String test, Date date, String mark){
        super(login, test, date, mark);
    }

    @Override
    protected String markToString() {
        return (getMark() / CONVERT) + DOT + (getMark() % CONVERT);
    }
}
