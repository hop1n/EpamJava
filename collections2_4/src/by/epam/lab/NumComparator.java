package by.epam.lab;

import java.util.Comparator;
import java.util.Map;

public class NumComparator implements Comparator<Map.Entry<Integer, Integer>> {
    public int compare(Map.Entry<Integer, Integer> e1, Map.Entry<Integer, Integer> e2) {
        return e2.getValue() - e1.getValue();
    }
}
