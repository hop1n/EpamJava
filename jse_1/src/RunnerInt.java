import by.epam.lab.ResultDao;
import by.epam.lab.ResultImplCsv;

import static by.epam.lab.Constants.*;

public class RunnerInt {
    public static void main(String[] args) {
        ResultDao reader = new ResultImplCsv(CSV_PATH);
        RunnerLogic.execute(reader);
    }
}

