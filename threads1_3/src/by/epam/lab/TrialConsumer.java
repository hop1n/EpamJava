package by.epam.lab;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;

import static by.epam.lab.Constants.*;

public class TrialConsumer implements Runnable {
    private final Queue<Trial> buffer;
    private final BlockingQueue<String> stringBuffer;

    public TrialConsumer(BlockingQueue<String> stringBuffer, Queue<Trial> buffer) {
        this.buffer = buffer;
        this.stringBuffer = stringBuffer;
    }

    @Override
    public void run() {
        while (true) {
            String s;
            try {
                s = stringBuffer.take();
                if (FALSE.equals(s)) {
                    break;
                }
                Trial trial = new Trial(s.split(DELIMITER));
                if (trial.isPassed()){
                    buffer.add(trial);
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());;
            }
        }
    }
}
