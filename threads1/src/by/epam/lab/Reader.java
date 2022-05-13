package by.epam.lab;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static by.epam.lab.Constants.*;

public class Reader implements Runnable {
    private final Drop drop;
    private final String path;

    public Reader(Drop drop, String path) {
        this.drop = drop;
        this.path = path;
    }

    public void run() {
        try (Scanner sc = new Scanner(new FileReader(path))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                drop.put(line);
            }
        } catch (FileNotFoundException e) {
            drop.put(DONE);
        }
    }
}
