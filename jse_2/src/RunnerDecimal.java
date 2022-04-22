import by.epam.lab.DAO.ResultDao;
import by.epam.lab.DAO.ResultImplXml;
import by.epam.lab.services.ResultKind;
import by.epam.lab.services.RunnerLogic;

import java.io.IOException;

import static by.epam.lab.services.Constants.*;

public class RunnerDecimal {
    public static void main(String[] args) {
        ResultKind resultKind = ResultKind.RESULT_DECIMAL;
        try (ResultDao reader = new ResultImplXml(XML_PATH, resultKind);) {
            RunnerLogic.execute(reader, resultKind);
        } catch (IOException e) {
            System.out.println(READER_EXCEPTION);
        }
    }
}
