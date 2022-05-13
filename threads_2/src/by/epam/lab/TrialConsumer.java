package by.epam.lab;

import java.util.Random;

import static by.epam.lab.Constants.*;

public class TrialConsumer implements Runnable {
    private final Drop drop;

    public TrialConsumer(Drop drop) {
        this.drop = drop;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (String message = drop.take();
             !message.contains(FAIL);
             message = drop.take()) {
            System.out.println(PUT + message);
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
