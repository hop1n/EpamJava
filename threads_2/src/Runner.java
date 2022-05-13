import by.epam.lab.Drop;
import by.epam.lab.TrialBuffer;
import by.epam.lab.TrialConsumer;

import static by.epam.lab.Constants.*;

public class Runner {
    public static void main(String[] args) {
        Drop drop = new Drop();
        (new Thread(new TrialBuffer(drop, PATH))).start();
        (new Thread(new TrialConsumer(drop))).start();
    }
}
