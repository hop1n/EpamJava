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
        Queue<Trial> trialQueue  = new ConcurrentLinkedQueue<>();
        PriorityBlockingQueue<String> stringBuffer = new PriorityBlockingQueue<>(200);
        ExecutorService producersPool = Executors.newFixedThreadPool(2);
        ExecutorService consumersPool = Executors.newFixedThreadPool(2);
        File folder = new File("src\\trials");
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
        System.out.println(trialQueue.size());
        producersPool.shutdown();
        consumersPool.shutdown();
    }
}