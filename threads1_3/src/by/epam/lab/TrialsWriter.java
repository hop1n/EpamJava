package by.epam.lab;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Queue;
import static by.epam.lab.Constants.*;

public class TrialsWriter implements Runnable {
    private final Queue<Trial> buffer;
    private final String fileName;

    public TrialsWriter(Queue<Trial> queue, String path) {
        this.buffer = queue;
        fileName = path;
    }

    @Override
    public void run() {
        try (FileWriter writer = new FileWriter(fileName)) {
            while (true) {
                if (buffer.isEmpty()) {
                    break;
                }
                Trial trial = buffer.remove();
                writer.write(trial + "\n");
            }
        } catch (IOException e) {
            System.out.println(FILE_NOT_FOUND);
        }
    }
}
