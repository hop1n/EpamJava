package by.epam.lab;

import java.io.Writer;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

import static by.epam.lab.Constants.*;

public class TrialConsumer implements Runnable {
    private final TrialBuffer trialBuffer;
    private Queue<Trial> trialQueue = null;
    private PriorityBlockingQueue<String> stringBuffer;

    public TrialConsumer(PriorityBlockingQueue<String> stringBuffer, Queue<Trial> trialQueue, TrialBuffer trialBuffer) {
        this.trialQueue = trialQueue;
        this.stringBuffer = stringBuffer;
        this.trialBuffer = trialBuffer;
    }

    @Override
    public void run() {
        while (true){
            stringBuffer = trialBuffer.take();
            if (stringBuffer == null) {
                break;
            }
            for (String s : stringBuffer) {
                String[] parts = s.split("\\W");
                trialQueue.add(new Trial(parts));
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(GOT + stringBuffer);
        }
    }
}
