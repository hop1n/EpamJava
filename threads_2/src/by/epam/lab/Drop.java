package by.epam.lab;

import static by.epam.lab.Constants.*;

public class Drop {
    private String message;
    private boolean empty = true;

    public synchronized String take() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        empty = true;
        notifyAll();
        return message;
    }

    public synchronized void put(Trial trial) {
        // Wait until message has
        // been retrieved.
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        empty = false;
        this.message = trial.toString();
        if (!trial.getAccount().equals(FAIL))
            System.out.println(GOT + message);
        notifyAll();
    }
}
