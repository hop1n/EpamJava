import by.epam.lab.DAO.ResultDao;
import by.epam.lab.DAO.ResultImplXml;
import by.epam.lab.services.ResultKind;
import by.epam.lab.services.RunnerLogic;

import java.io.IOException;

import static by.epam.lab.services.Constants.*;

public class RunnerDecimal {
    public static void main(String[] args) {
        RunnerLogic.execute(XML_PATH, ResultKind.RESULT_DECIMAL);
    }
}
