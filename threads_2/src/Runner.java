import by.epam.lab.TrialBuffer;
import by.epam.lab.TrialProducer;
import by.epam.lab.TrialConsumer;

import static by.epam.lab.Constants.*;

public class Runner {
    public static void main(String[] args) {
        TrialBuffer trialBuffer = new TrialBuffer();
        (new Thread(new TrialProducer(trialBuffer, PATH))).start();
        (new Thread(new TrialConsumer(trialBuffer))).start();
    }
}
