import by.epam.lab.ResultDao;
import by.epam.lab.ResultImplCsv;

import java.io.FileNotFoundException;

import static by.epam.lab.Constants.*;

public class RunnerInt {
    public static void main(String[] args) throws FileNotFoundException {
        ResultDao reader = new ResultImplCsv(CSV_PATH, 0);
        RunnerLogic.execute(reader);
    }
}

