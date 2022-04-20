import by.epam.lab.DAO.ResultDao;
import by.epam.lab.DAO.ResultImplXml;

import static by.epam.lab.Constants.FIRST_PARAMETER;
import static by.epam.lab.Constants.XML_PATH;

public class RunnerDecimal {
    public static void main(String[] args) {
        ResultDao reader = new ResultImplXml(XML_PATH, FIRST_PARAMETER);
        RunnerLogic.execute(reader);
    }
}
