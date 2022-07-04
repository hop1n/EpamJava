package by.epam.lab.service;

import by.epam.lab.Exceptions.WriteException;
import by.epam.lab.beans.Trial;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Queue;

import static by.epam.lab.service.Constants.*;

public class TrialsWriter implements Runnable {
    private final Queue<Trial> buffer;
    private final String fileName;
    private volatile boolean isStopRequested = false;

    public TrialsWriter(Queue<Trial> buffer, String path) {
        this.buffer = buffer;
        fileName = path;
    }

    @Override
    public void run() throws WriteException{
        try (FileWriter writer = new FileWriter(FINAL_RESULTS_FOLDER + fileName)) {
            while (!isStopRequested || !buffer.isEmpty()) {
                Trial trial = buffer.poll();
                if (trial != null) {
                    writer.write(trial + "\n");
                } else {
                    Thread.sleep(10000);
                }
            }
        } catch (IOException e) {
            throw new WriteException(WRITER_FILE);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(INTERRUPT_EXCEPTION);
        }
    }

    public void requestStop() {
        isStopRequested = true;
    }
}

