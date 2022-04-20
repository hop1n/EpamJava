package by.epam.lab;

import static by.epam.lab.Constants.*;
import java.sql.Date;

public class ResultFaсtory {
    private enum ResultKind{
        RESULT{
            Result getResult(String login, String test, Date date, String mark){
                return new Result(login, test, date, mark);
            }
        },
        RESULT_DECIMAL {
            ResultDecimal getResult(String login, String test, Date date, String mark){
                return new ResultDecimal(login, test, date, mark);
            }
        },
        RESULT_HALF{
            ResultHalf getResult(String login, String test, Date date, String mark){
                return new ResultHalf(login, test, date, mark);
            }
        };
        abstract Result getResult(String login, String test, Date date, String mark);
    }

    public static Result getResultFromFactory(int resultType, String login, String test, Date date, String mark) {
        ResultKind kind = ResultKind.values()[resultType];
        return kind.getResult(login, test, date, mark);
    }
}
