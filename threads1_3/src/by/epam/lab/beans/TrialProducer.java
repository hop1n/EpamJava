package by.epam.lab.beans;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

import static by.epam.lab.service.Constants.*;

public class TrialProducer implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrialProducer.class);

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
                String line = sc.nextLine();
                stringBuffer.put(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND);
        } catch (InterruptedException e) {
            LOGGER.error(INTERRUPT_EXC);
            Thread.currentThread().interrupt();
            System.out.println(INTERRUPT_EXCEPTION);
        } finally {
            countdownlatch.countDown();
        }
    }
}
