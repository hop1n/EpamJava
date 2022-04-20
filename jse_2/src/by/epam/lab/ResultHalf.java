package by.epam.lab;

import java.sql.Date;

import static by.epam.lab.Constants.*;


public class ResultHalf extends Result{

    public ResultHalf(String login, String test, Date date, String mark){
        super(login, test, date, mark);
    }
    @Override
    public String markToString() {
        String markStr;
        if (getMark() % CONVERT !=0){
            markStr = getMark() / CONVERT + DOT + getMark() % CONVERT;
        } else {
            markStr = super.markToString();
        }
        return markStr;
    }
}
