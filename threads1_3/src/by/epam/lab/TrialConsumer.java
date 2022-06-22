package by.epam.lab;

import java.io.Writer;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

import static by.epam.lab.Constants.*;

public class TrialConsumer implements Runnable {
    private final TrialBuffer trialBuffer;
    private  Queue<Trial> trialQueue = null;

    public TrialConsumer(TrialBuffer trialBuffer, Queue<Trial> trialQueue) {
        this.trialQueue = trialQueue;
        this.trialBuffer = trialBuffer;
    }

    @Override
    public void run() {
        while (true) {
            PriorityBlockingQueue<String> stringBuffer = trialBuffer.take();
            if (stringBuffer == null) {
                break;
            }
            for (String s : stringBuffer){
                String[] parts = s.split("\\W");
                trialQueue.add(new Trial(parts));
            }
            System.out.println(GOT + stringBuffer);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
