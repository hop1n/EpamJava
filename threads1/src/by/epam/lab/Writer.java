package by.epam.lab;

import java.util.Random;

public class Writer implements Runnable {
    private final Drop drop;

    public Writer(Drop drop) {
        this.drop = drop;
    }

    public void run() {
        Random random = new Random();
        for (String message = drop.take();
             !message.equals("DONE");
             message = drop.take()) {
            System.out.println(message);
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
