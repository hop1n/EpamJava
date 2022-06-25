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
        Queue<Trial> buffer  = new ConcurrentLinkedQueue<>();
        PriorityBlockingQueue<String> stringBuffer = new PriorityBlockingQueue<>(bufferStrLength);
        ExecutorService producersPool = Executors.newFixedThreadPool(maxProducersNumber);
        ExecutorService consumersPool = Executors.newFixedThreadPool(maxConsumersNumber);
        File folder = new File(folderName);
        File[] listOfFiles = folder.listFiles();
        int fileCount = 0;
        for (File file : listOfFiles) {
            if (file.isFile()) {
                fileCount++;
                String path = file.getPath();
                producersPool.execute(new TrialProducer(path, stringBuffer, countDownLatch));
            }
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        for(int i = 0; i <maxConsumersNumber; i++){
            consumersPool.execute(new TrialConsumer(stringBuffer, buffer));
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        for (int i = 0; i < fileCount; i++){
            stringBuffer.put(FALSE);
        }
        new TrialsWriter(buffer, FINAL_RESULT_PATH).run();
        producersPool.shutdown();
        consumersPool.shutdown();
    }
}