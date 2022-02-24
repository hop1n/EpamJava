package by.epam.lab;

import by.epam.lab.beans.Purchase;
import by.epam.lab.beans.WeekDay;

import java.util.Map;

public interface EntryChecker<K, V> {
    boolean check(Map.Entry<K, V> entry);

}
