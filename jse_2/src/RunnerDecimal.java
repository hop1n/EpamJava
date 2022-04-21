import by.epam.lab.DAO.ResultDao;
import by.epam.lab.DAO.ResultImplXml;

import java.io.IOException;

import static by.epam.lab.Constants.*;

public class RunnerDecimal {
    public static void main(String[] args) {
        try (ResultDao reader = new ResultImplXml(XML_PATH, FIRST_PARAMETER);) {
            RunnerLogic.execute(reader);
        } catch (IOException e) {
            System.out.println(READER_EXCEPTION);
        }
    }
}
