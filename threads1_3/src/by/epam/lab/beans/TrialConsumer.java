package by.epam.lab.beans;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import jdk.nashorn.internal.runtime.Context;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;

import static by.epam.lab.service.Constants.*;

public class TrialConsumer implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrialConsumer.class);
    private final Queue<Trial> buffer;
    private final BlockingQueue<String> stringBuffer;

    public TrialConsumer(BlockingQueue<String> stringBuffer, Queue<Trial> buffer) {
        this.buffer = buffer;
        this.stringBuffer = stringBuffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String line;
                line = stringBuffer.take();
                if (POISONED_PILL.equals(line)) {
                    break;
                }
                Trial trial = new Trial(line.split(SEMICOLON));
                if (trial.isPassed()) {
                    buffer.add(trial);
                }
            } catch (InterruptedException e) {
                LOGGER.error("current thread is interrupted");
                Thread.currentThread().interrupt();
                System.out.println(INTERRUPT_EXCEPTION);
            }
        }
    }
}
