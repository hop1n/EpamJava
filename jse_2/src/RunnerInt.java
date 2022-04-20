import by.epam.lab.DAO.ResultDao;
import by.epam.lab.DAO.ResultImplCsv;

import static by.epam.lab.Constants.*;

public class RunnerInt {
    public static void main(String[] args) {
        ResultDao reader = new ResultImplCsv(CSV_PATH, 0);
        RunnerLogic.execute(reader);
    }
}

