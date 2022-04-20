import by.epam.lab.DAO.ResultDao;
import by.epam.lab.DAO.ResultImplCsv;

import static by.epam.lab.Constants.CSV2_PATH;
import static by.epam.lab.Constants.SECOND_PARAMETER;

public class RunnerHalf {
    public static void main(String[] args) {
        ResultDao reader = new ResultImplCsv(CSV2_PATH, SECOND_PARAMETER);
        RunnerLogic.execute(reader);
    }
}
