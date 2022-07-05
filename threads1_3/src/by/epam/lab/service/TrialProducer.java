package by.epam.lab.service;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

import static by.epam.lab.service.Constants.*;

public class TrialProducer implements Runnable {
    private final String path;
    private final BlockingQueue<String> stringBuffer;
    private final CountDownLatch countdownlatch;

    public TrialProducer(String path, BlockingQueue<String> stringBuffer, CountDownLatch countDownLatch) {
        this.stringBuffer = stringBuffer;
        this.path = path;
        this.countdownlatch = countDownLatch;
    }

    @Override
    public void run() {
        try (Scanner sc = new Scanner(new FileReader(path))) {
            while (sc.hasNextLine()) {
                try {
                    String line = sc.nextLine();
                    stringBuffer.put(line);
                } catch (InterruptedException e) {
                    System.err.println(INTERRUPT_EXCEPTION + path);
                    Thread.currentThread().interrupt();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND);
        } finally {
            countdownlatch.countDown();
        }
    }
}
