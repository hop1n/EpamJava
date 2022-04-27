import by.epam.lab.services.ResultKind;
import by.epam.lab.services.RunnerLogic;

import static by.epam.lab.services.Constants.*;

public class RunnerInt {
    public static void main(String[] args) {
        RunnerLogic.execute(CSV_PATH, ResultKind.RESULT);
    }
}

