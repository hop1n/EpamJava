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
                String[] parts = line.split(DELIMITER);
                trialBuffer.put(new Trial(parts));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input file is not found");
        } finally {
            trialBuffer.put(new Trial(FAIL, 0, 0));
        }
    }
}
