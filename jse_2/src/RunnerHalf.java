import by.epam.lab.DAO.ResultDao;
import by.epam.lab.DAO.ResultImplCsv;
import by.epam.lab.ResultKind;

import java.io.IOException;

import static by.epam.lab.Constants.*;

public class RunnerHalf {
    public static void main(String[] args) {
        try (ResultDao reader = new ResultImplCsv(CSV2_PATH, ResultKind.RESULT_HALF);) {
            RunnerLogic.execute(reader);
        } catch (IOException e) {
            System.out.println(READER_EXCEPTION);
        }
    }
}
