package by.epam.lab.beans;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;

import static by.epam.lab.service.Constants.*;

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
            String line = EMPTY_LINE;
            try {
                line = stringBuffer.take();
            } catch (InterruptedException e) {
                System.out.println(INTERRUPT_EXCEPTION);
                Thread.currentThread().interrupt();
            }
            if (POISONED_PILL.equals(line)) {
                break;
            }
            Trial trial = new Trial(line.split(SEMICOLON));
            if (trial.isPassed()) {
                buffer.add(trial);
            }
        }
    }
}
