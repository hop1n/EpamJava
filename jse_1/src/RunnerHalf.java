import by.epam.lab.ResultDao;
import by.epam.lab.ResultImplCsv;

import static by.epam.lab.Constants.CSV2_PATH;
import static by.epam.lab.Constants.CSV_PATH;

public class RunnerHalf {
    public static void main(String[] args) {
        ResultDao reader = new ResultImplCsv(CSV2_PATH);
        RunnerLogic.execute(reader);
    }
}
