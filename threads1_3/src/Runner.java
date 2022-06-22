import by.epam.lab.*;
import sun.nio.ch.ThreadPool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

import static by.epam.lab.Constants.*;

public class Runner {
    public static void main(String[] args) {
        TrialBuffer trialBuffer = new TrialBuffer();
        Queue<Trial> trialQueue  = new ConcurrentLinkedQueue<>();
        PriorityBlockingQueue<String> stringBuffer = new PriorityBlockingQueue<>(200);
        ExecutorService producersPool = Executors.newFixedThreadPool(2);
        ExecutorService consumersPool = Executors.newFixedThreadPool(2);
        File folder = new File("src\\trials");
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                String path = file.getPath();
                producersPool.execute(new TrialProducer(trialBuffer, path, stringBuffer));
            }
        }
        TrialConsumer consumer = new TrialConsumer(trialBuffer, trialQueue);
        for(int i = 0; i <2; i++){
            consumersPool.execute(consumer);
        }
        producersPool.shutdown();
        consumersPool.shutdown();
    }
}