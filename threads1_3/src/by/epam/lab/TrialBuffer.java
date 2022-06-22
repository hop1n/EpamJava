package by.epam.lab;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.util.concurrent.PriorityBlockingQueue;

import static by.epam.lab.Constants.*;

public class TrialBuffer {
    private PriorityBlockingQueue<String> stringBuffer;
    private boolean empty = true;

    private static final Logger LOGGER = LoggerFactory.getLogger(TrialBuffer.class);

    public synchronized PriorityBlockingQueue<String> take() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                //the thread will never be interrupted
                LOGGER.error("current thread is interrupted", e);
            }
        }
        empty = true;
        notifyAll();
        return stringBuffer;
    }

    public synchronized void put(PriorityBlockingQueue<String> stringBuffer) {
        // Wait until message has
        // been retrieved.
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                //the thread will never be interrupted
                LOGGER.error("current thread is interrupted", e);
            }
        }
        empty = false;
        this.stringBuffer = stringBuffer;
        notifyAll();
    }
}
