package by.epam.lab;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import static by.epam.lab.Constants.*;

public class TrialBuffer {
    private Trial trial;
    private boolean empty = true;

    private static final Logger LOGGER = LoggerFactory.getLogger(TrialBuffer.class);

    public synchronized Trial take() {
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
        return trial;
    }

    public synchronized void put(Trial trial) {
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
        this.trial = trial;
        notifyAll();
    }
}
