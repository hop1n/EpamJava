import by.epam.lab.*;
import sun.nio.ch.ThreadPool;

import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.lab.Constants.*;

public class Runner {
    public static void main(String[] args) {
        ResourceBundle rb = ResourceBundle.getBundle(PROPERTY_FILE_NAME);
        Enumeration<String> keys = rb.getKeys();
        String folderName = rb.getString(FOLDER_NAME);
        int maxProducersNumber = Integer.parseInt(rb.getString(MAX_PRODUCERS_NUMBER));
        int maxConsumersNumber = Integer.parseInt(rb.getString(MAX_CONSUMERS_NUMBER));
        int bufferStrLength = Integer.parseInt(rb.getString(STRING_BUFFER_LENGTH));
        Queue<Trial> trialQueue  = new ConcurrentLinkedQueue<>();
        PriorityBlockingQueue<String> stringBuffer = new PriorityBlockingQueue<>(bufferStrLength);
        ExecutorService producersPool = Executors.newFixedThreadPool(maxProducersNumber);
        ExecutorService consumersPool = Executors.newFixedThreadPool(maxConsumersNumber);
        File folder = new File(folderName);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                String path = file.getPath();
                producersPool.execute(new TrialProducer(path, stringBuffer));
            }
        }
        for(int i = 0; i <2; i++){
            consumersPool.execute(new TrialConsumer(stringBuffer, trialQueue));
        }
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        TrialsWriter writer = new TrialsWriter(trialQueue);
        writer.run();
        producersPool.shutdown();
        consumersPool.shutdown();
    }
}