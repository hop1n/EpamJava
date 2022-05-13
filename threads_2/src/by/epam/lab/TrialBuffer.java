package by.epam.lab;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static by.epam.lab.Constants.*;

public class TrialBuffer implements Runnable {
    private final Drop drop;
    private final String path;

    public TrialBuffer(Drop drop, String path) {
        this.drop = drop;
        this.path = path;
    }

    @Override
    public void run() {
        try (Scanner sc = new Scanner(new FileReader(path))) {
            Trial trial;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(DELIMITER);
                drop.put(new Trial(parts));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input file is not found");
        } finally {
            drop.put(new Trial(FAIL, 0, 0));
        }
    }
}
