package by.epam.lab;

import java.io.Writer;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

import static by.epam.lab.Constants.*;

public class TrialConsumer implements Runnable {
    private final Queue<Trial> trialQueue;
    private final PriorityBlockingQueue<String> stringBuffer;

    public TrialConsumer(PriorityBlockingQueue<String> stringBuffer, Queue<Trial> trialQueue) {
        this.trialQueue = trialQueue;
        this.stringBuffer = stringBuffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String s = stringBuffer.take();
                System.out.println(s);
                if (s.contains("false")) {
                    break;
                }
                String[] parts = s.split(";");
                trialQueue.add(new Trial(parts));
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                break;
            }

        }
    }
}
