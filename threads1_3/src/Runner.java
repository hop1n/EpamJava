import by.epam.lab.Exceptions.WriteException;
import by.epam.lab.beans.Trial;
import by.epam.lab.beans.TrialConsumer;
import by.epam.lab.beans.TrialProducer;
import by.epam.lab.service.TrialsWriter;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static by.epam.lab.service.Constants.*;

public class Runner {
    public static void main(String[] args) {
        ResourceBundle rb = ResourceBundle.getBundle(PROPERTY_FILE_NAME);
        int maxProducersNumber = Integer.parseInt(rb.getString(MAX_PRODUCERS_NUMBER));
        int maxConsumersNumber = Integer.parseInt(rb.getString(MAX_CONSUMERS_NUMBER));
        int bufferStrLength = Integer.parseInt(rb.getString(STRING_BUFFER_LENGTH));
        String folderName = rb.getString(FOLDER_NAME);
        Queue<Trial> buffer = new ConcurrentLinkedQueue<>();
        BlockingQueue<String> stringBuffer = new PriorityBlockingQueue<>(bufferStrLength);
        ExecutorService producersPool = Executors.newFixedThreadPool(maxProducersNumber);
        ExecutorService consumersPool = Executors.newFixedThreadPool(maxConsumersNumber);
        ExecutorService writerThread = Executors.newSingleThreadExecutor();
        List<File> filesList = Arrays.stream(new File(folderName).listFiles())
                .filter(file -> file.getName().contains(".csv"))
                .collect(Collectors.toList());
        CountDownLatch countDownLatch = new CountDownLatch(filesList.size());
        filesList.forEach(file -> producersPool.execute(new TrialProducer(file.getPath(), stringBuffer, countDownLatch)));
        IntStream.rangeClosed(0, maxConsumersNumber - 1)
                .forEach(i -> consumersPool.execute(new TrialConsumer(stringBuffer, buffer)));
        TrialsWriter writer = new TrialsWriter(buffer, FINAL_RESULT_PATH);
        writerThread.execute(writer);
        try {
            countDownLatch.await();
        IntStream.rangeClosed(0, maxConsumersNumber - 1)
                .forEach(i -> stringBuffer.add(POISONED_PILL));
        writer.requestStop();
        writerThread.shutdown();
        producersPool.shutdown();
        consumersPool.shutdown();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(INTERRUPT_EXCEPTION);
        }
    }
}