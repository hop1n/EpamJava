package by.epam.lab;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.PriorityBlockingQueue;
import static by.epam.lab.Constants.*;

public class TrialConsumer implements Runnable {
    private final Queue<Trial> trialQueue;
    private final BlockingQueue<String> stringBuffer;

    public TrialConsumer(BlockingQueue<String> stringBuffer, Queue<Trial> trialQueue) {
        this.trialQueue = trialQueue;
        this.stringBuffer = stringBuffer;
    }

    @Override
    public void run() {
        while (true) {
            String s;
            try {
                s = stringBuffer.take();
                if (s.equals(FALSE)) {
                    break;
                }
                String[] parts = s.split(DELIMITER);
                Trial trial = new Trial(parts);
                if (trial.isPassed()){
                    trialQueue.add(trial);
                }
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
