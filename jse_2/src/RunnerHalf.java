import by.epam.lab.services.ResultKind;
import by.epam.lab.services.RunnerLogic;

import static by.epam.lab.services.Constants.*;

public class RunnerHalf {
    public static void main(String[] args) {
        RunnerLogic.execute(CSV2_PATH, ResultKind.RESULT_HALF);
    }
}
