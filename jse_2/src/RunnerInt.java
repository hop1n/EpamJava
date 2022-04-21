import by.epam.lab.DAO.ResultDao;
import by.epam.lab.DAO.ResultImplCsv;

import java.io.IOException;

import static by.epam.lab.Constants.*;

public class RunnerInt {
    public static void main(String[] args) {
        try (ResultDao reader = new ResultImplCsv(CSV_PATH, ZERO_PARAMETER);) {
            RunnerLogic.execute(reader);
        } catch (IOException e) {
            System.out.println(READER_EXCEPTION);
        }
    }
}

