package by.epam.lab;

import static by.epam.lab.Constants.*;

import java.sql.Date;

public class ResultFactory {
    private enum ResultKind {
        RESULT {
            public Result getResult(String login, String test, Date date, int mark) {
                return new Result(login, test, date, mark);
            }
        },
        RESULT_DECIMAL {
            public ResultDecimal getResult(String login, String test, Date date, int mark) {
                return new ResultDecimal(login, test, date, mark);
            }
        },
        RESULT_HALF {
            public ResultHalf getResult(String login, String test, Date date, int mark) {
                return new ResultHalf(login, test, date, mark);
            }
        };

        public abstract Result getResult(String login, String test, Date date, int mark);

        public Result getResult(String login, String test, Date date, String mark) {
            return getResult(login, test, date, (int) (Double.parseDouble(mark) * CONVERT));
        }
    }

    public static Result getResultFromFactory(int resultType, String login, String test, Date date, int mark) {
        ResultKind kind = ResultKind.values()[resultType];
        return kind.getResult(login, test, date, mark);
    }

    public static Result getResultFromFactory(int resultType, String login, String test, Date date, String mark) {
        ResultKind kind = ResultKind.values()[resultType];
        return kind.getResult(login, test, date, mark);
    }
}
