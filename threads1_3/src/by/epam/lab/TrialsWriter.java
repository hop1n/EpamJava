package by.epam.lab;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Queue;

public class TrialsWriter implements Runnable {
    private final Queue<Trial> QUEUE;
    private final String FILE_NAME;

    public TrialsWriter(Queue<Trial> queue, String path){
        this.QUEUE = queue;
        FILE_NAME = path;

    }
    @Override
    public void run() {
        try(FileWriter writer = new FileWriter(FILE_NAME))
        {
            while (true) {
                if(QUEUE.isEmpty()){
                    break;
                }
                Trial trial = QUEUE.remove();
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
