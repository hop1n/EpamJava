package by.epam.lab;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Writer;
import java.util.Scanner;
import java.util.concurrent.PriorityBlockingQueue;

import static by.epam.lab.Constants.*;

public class TrialProducer implements Runnable {
    private final TrialBuffer trialBuffer;
    private final String path;
    private static final PriorityBlockingQueue<String> stringBuffer = new PriorityBlockingQueue<>(200);

    public TrialProducer(TrialBuffer trialBuffer, String path, PriorityBlockingQueue<String> stringBuffer) {
        this.trialBuffer = trialBuffer;
        this.path = path;
    }

    @Override
    public void run() {
        try (Scanner sc = new Scanner(new FileReader(path))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                stringBuffer.put(line);
                System.out.println(PUT + line);
            }
            trialBuffer.put(stringBuffer);
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND);
        } finally {
            trialBuffer.put(null);
        }
    }
}
