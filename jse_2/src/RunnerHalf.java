import by.epam.lab.DAO.ResultDao;
import by.epam.lab.DAO.ResultImplCsv;
import by.epam.lab.beans.Result;
import by.epam.lab.services.ResultKind;
import by.epam.lab.services.RunnerLogic;

import java.io.IOException;

import static by.epam.lab.services.Constants.*;

public class RunnerHalf {
    public static void main(String[] args) {
        ResultKind resultKind = ResultKind.RESULT_HALF;
        try (ResultDao reader = new ResultImplCsv(CSV2_PATH, resultKind);) {
            RunnerLogic.execute(reader, resultKind);
        } catch (IOException e) {
            System.out.println(READER_EXCEPTION);
        }
    }
}
