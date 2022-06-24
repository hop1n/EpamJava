package by.epam.lab;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.concurrent.PriorityBlockingQueue;

import static by.epam.lab.Constants.*;

public class TrialProducer implements Runnable {
    private final String path;
    private final PriorityBlockingQueue<String> stringBuffer;

    public TrialProducer(String path, PriorityBlockingQueue<String> stringBuffer) {
        this.stringBuffer = stringBuffer;
        this.path = path;
    }

    @Override
    public void run() {
        try (Scanner sc = new Scanner(new FileReader(path))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                stringBuffer.put(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND);
        } finally {
            stringBuffer.put(FALSE);
        }
    }
}
