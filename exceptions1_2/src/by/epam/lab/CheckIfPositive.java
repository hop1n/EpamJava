package by.epam.lab;

import by.epam.lab.Causes;
import by.epam.lab.exceptions.NonPositiveArgumentException;

public class CheckIfPositive {
    public static void check(String element, String fieldName) {
        if (Integer.parseInt(element) <= 0) {
            throw new NonPositiveArgumentException(Causes.WRONG + fieldName);
        }
    }

    public static void check(int value, String fieldName){
        if (value <= 0) {
            throw new NonPositiveArgumentException(Causes.WRONG + fieldName);
        }
    }

    public static void check(Byn value, String fieldName){
        if (value.compareTo(new Byn(Constants.ZERO)) <= 0) {
            throw new NonPositiveArgumentException(Causes.WRONG + fieldName);
        }
    }
}
