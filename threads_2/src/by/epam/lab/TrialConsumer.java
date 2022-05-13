package by.epam.lab;

import java.util.Random;

import static by.epam.lab.Constants.*;

public class TrialConsumer implements Runnable {
    private final TrialBuffer trialBuffer;

    public TrialConsumer(TrialBuffer trialBuffer) {
        this.trialBuffer = trialBuffer;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true){
            Trial trial = trialBuffer.take();
            if (trial.getAccount().equals(FAIL)){
                break;
            }
            System.out.println(PUT + trial);
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            if (!trial.getAccount().equals(FAIL))
                System.out.println(GOT + trial);
        }
    }
}
