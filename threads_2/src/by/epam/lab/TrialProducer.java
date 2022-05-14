package by.epam.lab;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static by.epam.lab.Constants.*;

public class TrialProducer implements Runnable {
    private final TrialBuffer trialBuffer;
    private final String path;

    public TrialProducer(TrialBuffer trialBuffer, String path) {
        this.trialBuffer = trialBuffer;
        this.path = path;
    }

    @Override
    public void run() {
        try (Scanner sc = new Scanner(new FileReader(path))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Trial trial = new Trial(line.split(DELIMITER));
                trialBuffer.put(trial);
                System.out.println(PUT + trial);
            }
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND);
        } finally {
            trialBuffer.put(FAILED_TRIAL);
        }
    }
}
