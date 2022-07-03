import by.epam.lab.beans.Trial;
import by.epam.lab.beans.TrialConsumer;
import by.epam.lab.beans.TrialProducer;
import by.epam.lab.service.TrialsWriter;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static by.epam.lab.service.Constants.*;

public class Runner {
    public static void main(String[] args) throws IOException {
        ResourceBundle rb = ResourceBundle.getBundle(PROPERTY_FILE_NAME);
        String folderName = rb.getString(FOLDER_NAME);
        int maxProducersNumber = Integer.parseInt(rb.getString(MAX_PRODUCERS_NUMBER));
        int maxConsumersNumber = Integer.parseInt(rb.getString(MAX_CONSUMERS_NUMBER));
        int bufferStrLength = Integer.parseInt(rb.getString(STRING_BUFFER_LENGTH));
        CountDownLatch countDownLatch = new CountDownLatch(COUNT_DOWN_INDEX);
        Queue<Trial> buffer = new ConcurrentLinkedQueue<>();
        BlockingQueue<String> stringBuffer = new PriorityBlockingQueue<>(bufferStrLength);
        ExecutorService producersPool = Executors.newFixedThreadPool(maxProducersNumber);
        ExecutorService consumersPool = Executors.newFixedThreadPool(maxConsumersNumber);
        ExecutorService writerThread = Executors.newSingleThreadExecutor();
        List<String> filesList = Arrays.stream(Objects.requireNonNull(new File(folderName).listFiles()))
                .filter(File::isFile)
                .map(File::getName)
                .filter(s -> s.contains(CSV_EXTENSION))
                .collect(Collectors.toList());
        for (String p : filesList) {
            producersPool.execute(new TrialProducer(folderName  + p, stringBuffer, countDownLatch));
        }
        IntStream.rangeClosed(0, maxConsumersNumber - 1)
                .forEach(i -> {
                    consumersPool.execute(new TrialConsumer(stringBuffer, buffer));
                });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(INTERRUPT_EXCEPTION);
        }
        TrialsWriter writer = new TrialsWriter(buffer, FINAL_RESULT_PATH);
        writerThread.execute(writer);
        IntStream.rangeClosed(0, maxConsumersNumber - 1)
                .forEach(i -> {
                    stringBuffer.add(POISONED_PILL);
                });
        for (int i = 0; i < maxConsumersNumber; i++) {
            stringBuffer.add(POISONED_PILL);
        }
        writer.requestStop();
        writerThread.shutdown();
        producersPool.shutdown();
        consumersPool.shutdown();
    }
}