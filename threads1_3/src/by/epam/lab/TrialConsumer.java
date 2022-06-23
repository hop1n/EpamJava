package by.epam.lab;

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
            String s;
            try {
                s = stringBuffer.take();
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
        System.out.println(stringBuffer);
        TrialsWriter trialsWriter = new TrialsWriter(trialQueue);
        trialsWriter.run();
//        System.out.println(trialQueue.size());
    }
}
