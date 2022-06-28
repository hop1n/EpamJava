import by.epam.lab.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

import static by.epam.lab.Constants.*;

public class Runner {
    public static void main(String[] args) {
        ResourceBundle rb = ResourceBundle.getBundle(PROPERTY_FILE_NAME);
        String folderName = rb.getString(FOLDER_NAME);
        int maxProducersNumber = Integer.parseInt(rb.getString(MAX_PRODUCERS_NUMBER));
        int maxConsumersNumber = Integer.parseInt(rb.getString(MAX_CONSUMERS_NUMBER));
        int bufferStrLength = Integer.parseInt(rb.getString(STRING_BUFFER_LENGTH));
        CountDownLatch countDownLatch = new CountDownLatch(3);
        Queue<Trial> buffer = new ConcurrentLinkedQueue<>();
        BlockingQueue<String> stringBuffer = new PriorityBlockingQueue<>(bufferStrLength);
        ExecutorService producersPool = Executors.newFixedThreadPool(maxProducersNumber);
        ExecutorService consumersPool = Executors.newFixedThreadPool(maxConsumersNumber);
        File[] listOfFiles = new File(folderName).listFiles();
        int fileCount = 0;
        for (File file : listOfFiles) {
            if (file.isFile()) {
                fileCount++;
                producersPool.execute(new TrialProducer(file.getPath(), stringBuffer, countDownLatch));
            }
        }
        for (int i = 0; i < maxConsumersNumber; i++) {
            consumersPool.execute(new TrialConsumer(stringBuffer, buffer));
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        for (int i = 0; i < maxConsumersNumber; i++) {
            stringBuffer.add(POISONED_PILL);
        }
        new Thread(new TrialsWriter(buffer, FINAL_RESULT_PATH)).start();
        producersPool.shutdown();
        consumersPool.shutdown();
    }
}