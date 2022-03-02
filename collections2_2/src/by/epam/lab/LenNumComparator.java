package by.epam.lab;

import java.util.Comparator;

public class LenNumComparator implements Comparator<LenNum> {
    @Override
    public int compare(LenNum a, LenNum b) {
        return a.getNum() - b.getNum();
    }
}
