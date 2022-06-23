package by.epam.lab;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Queue;

public class TrialsWriter implements Runnable {
    private final Queue<Trial> queue;

    public TrialsWriter(Queue<Trial> queue){
        this.queue = queue;
    }
    @Override
    public void run() {
        try(FileWriter writer = new FileWriter("finalTrials.csv"))
        {
            while (true) {
                Trial trial = queue.remove();
                if (trial == null) {
                    break;
                }
                writer.write(trial+"\n");
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
