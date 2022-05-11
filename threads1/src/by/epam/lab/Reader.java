package by.epam.lab;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
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
        Random random = new Random();
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            drop.put(line);
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        drop.put(DONE);
    }
}
