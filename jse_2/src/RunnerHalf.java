import by.epam.lab.DAO.ResultDao;
import by.epam.lab.DAO.ResultImplCsv;
import by.epam.lab.beans.Result;
import by.epam.lab.exceptions.SourceException;
import by.epam.lab.services.ResultKind;
import by.epam.lab.services.RunnerLogic;

import java.io.FileNotFoundException;
import java.io.IOException;

import static by.epam.lab.services.Constants.*;

public class RunnerHalf {
    public static void main(String[] args) {
        RunnerLogic.execute(CSV2_PATH, ResultKind.RESULT_HALF);
    }
}
