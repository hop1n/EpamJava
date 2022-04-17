import by.epam.lab.ResultDao;
import by.epam.lab.ResultImplCsv;
import by.epam.lab.ResultImplXml;
import by.epam.lab.exceptions.SourceException;

import static by.epam.lab.Constants.XML_PATH;

public class RunnerDecimal {
    public static void main(String[] args) throws SourceException {
        ResultDao reader = new ResultImplXml(XML_PATH);
        RunnerLogic.execute(reader);
    }
}
