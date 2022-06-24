package by.epam.lab;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Queue;
import static by.epam.lab.Constants.*;

public class TrialsWriter implements Runnable {
    private final Queue<Trial> queue;

    public TrialsWriter(Queue<Trial> queue){
        this.queue = queue;
    }
    @Override
    public void run() {
        try(FileWriter writer = new FileWriter(FINAL_RESULT_PATH))
        {
            while (true) {
                if(queue.isEmpty()){
                    break;
                }
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
