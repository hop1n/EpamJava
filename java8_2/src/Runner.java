import by.epam.lab.ExtraTrial;
import by.epam.lab.LightTrial;
import by.epam.lab.StrongTrial;
import by.epam.lab.Trial;

import static by.epam.lab.Constants.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class Runner {
    public static void main(String[] args) {
        ToIntFunction<Trial> getSum = t -> t.getMark1() + t.getMark2();
        //1
        List<Trial> trainee = Arrays.asList(new Trial("cool", 60, 70),
                new Trial("steve", 50, 40),
                new Trial("simon", 60, 80),
                new LightTrial("billie", 30, 60),
                new LightTrial("sam", 70, 70),
                new StrongTrial("milena", 90, 90),
                new StrongTrial("alex", 80, 50),
                new ExtraTrial("rachele", 80, 90, 50),
                new ExtraTrial("rachele", 50, 90, 90));
        //2
        trainee.forEach(System.out::println);
        //3
        System.out.println(TEST_PASSED + trainee.stream()
                .filter(Trial::isPassed)
                .count());
        //4
        System.out.println(SORTED);
        trainee.sort(Comparator.comparingInt(getSum));
        //5
        trainee.stream()
                .mapToInt(getSum)
                .forEach(System.out::println);
        //6
        List<Trial> failedTrials = trainee.stream()
                .filter(t -> !t.isPassed())
                .map(Trial::getClone)
                .peek(System.out::println)
                .peek(Trial::clearMarks)
                .collect(Collectors.toList());
        System.out.println(TEST_FAILED + failedTrials.stream()
                .noneMatch(Trial::isPassed));
        //7
        int[] sums = trainee.stream()
                .mapToInt(getSum)
                .toArray();
        System.out.println(Arrays.stream(sums)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(DELIMITER)));
    }
}
