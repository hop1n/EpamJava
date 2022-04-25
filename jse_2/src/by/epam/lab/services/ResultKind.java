package by.epam.lab.services;

import by.epam.lab.DAO.ResultDao;
import by.epam.lab.DAO.ResultImplCsv;
import by.epam.lab.DAO.ResultImplXml;
import by.epam.lab.beans.Result;
import by.epam.lab.beans.ResultDecimal;
import by.epam.lab.beans.ResultHalf;

import java.sql.Date;

import static by.epam.lab.services.Constants.CONVERT;

public enum ResultKind {
    RESULT {
        public Result getResult(String login, String test, Date date, int mark) {
            return new Result(login, test, date, mark);
        }

        public ResultDao getDao(String path){
            return new ResultImplCsv(path ,RESULT);
        }
    },
    RESULT_DECIMAL {
        public ResultDecimal getResult(String login, String test, Date date, int mark) {
            return new ResultDecimal(login, test, date, mark);
        }

        public ResultDao getDao(String path){
            return new ResultImplXml(path ,RESULT_DECIMAL);
        }
    },
    RESULT_HALF {
        public ResultHalf getResult(String login, String test, Date date, int mark) {
            return new ResultHalf(login, test, date, mark);
        }

        public ResultDao getDao(String path){
            return new ResultImplCsv(path ,RESULT_HALF);
        }
    };

    public abstract Result getResult(String login, String test, Date date, int mark);

    public abstract ResultDao getDao(String path);

    public Result getResult(String login, String test, Date date, String mark) {
        return getResult(login, test, date, (int) (Double.parseDouble(mark) * CONVERT));
    }
}
