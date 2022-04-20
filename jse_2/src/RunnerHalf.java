import by.epam.lab.DAO.ResultDao;
import by.epam.lab.DAO.ResultImplCsv;

import static by.epam.lab.Constants.CSV2_PATH;

public class RunnerHalf {
    public static void main(String[] args) {
        ResultDao reader = new ResultImplCsv(CSV2_PATH, 2);
        RunnerLogic.execute(reader);
    }
}
