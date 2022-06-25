package by.epam.lab;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

import static by.epam.lab.Constants.*;

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
                String line = sc.nextLine();
                stringBuffer.put(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND);
        } catch (InterruptedException e){
            System.out.println(e.getMessage());
        } finally {
            countdownlatch.countDown();
        }
    }
}
