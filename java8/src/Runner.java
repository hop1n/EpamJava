import by.epam.lab.ExtraTrial;
import by.epam.lab.LightTrial;
import by.epam.lab.StrongTrial;
import by.epam.lab.Trial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Runner {
    public static void main(String[] args){
        List<Trial> trainee = new ArrayList<>();
        trainee.add(new Trial("cool", 60, 70));
        trainee.add(new Trial("steve", 50, 40));
        trainee.add(new Trial("simon", 60, 80));
        trainee.add(new LightTrial("billie", 30, 60));
        trainee.add(new LightTrial("sam", 70, 70));
        trainee.add(new StrongTrial("milena", 90, 90));
        trainee.add(new StrongTrial("alex", 80, 50));
        trainee.add(new ExtraTrial("rachele", 80, 90, 50));
        trainee.add(new ExtraTrial("rachele", 50, 90, 90));
        trainee.forEach(System.out::println);
        System.out.println("======================================================");
        System.out.println("Tests passed: " + trainee.stream().filter(Trial::isPassed).count());
        System.out.println("sorted ->");
        //trainee.stream().sorted(Trial::compareTo).forEach(System.out::println); why is this sort not working ?
        trainee.sort(Comparator.comparingInt(Trial::getSum));
        trainee.stream().map(Trial::getSum).forEach(System.out::println);
        List<Trial> failedTrials = new ArrayList<>();
        List<Trial> clonedTrials = new ArrayList<>();
        trainee.stream().map(Trial::getClone).forEach(clonedTrials::add);
        clonedTrials.stream().filter(t -> !t.isPassed()).forEach(failedTrials::add);
        failedTrials.forEach(System.out::println);
        failedTrials.forEach(Trial::clearMarks);
        System.out.println("tests are failed: " + failedTrials.stream().noneMatch(Trial::isPassed));
        int[] sums = trainee.stream().mapToInt(Trial::getSum).toArray();
        Arrays.stream(sums).forEach(System.out::println);
    }
}
