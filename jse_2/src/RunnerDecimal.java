import by.epam.lab.services.ResultKind;
import by.epam.lab.services.RunnerLogic;

import static by.epam.lab.services.Constants.*;

public class RunnerDecimal {
    public static void main(String[] args) {
        RunnerLogic.execute(XML_PATH, ResultKind.RESULT_DECIMAL);
    }
}
