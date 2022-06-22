package by.epam.lab;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

public class TrialsWriter implements Runnable {
    private final PriorityBlockingQueue<String> queue;

    public TrialsWriter(PriorityBlockingQueue<String> queue){
        this.queue = queue;
    }
    @Override
    public void run() {
        try(FileWriter writer = new FileWriter("finalTrials.csv"))
        {
            writer.write(queue.take());
        } catch (IOException | InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
