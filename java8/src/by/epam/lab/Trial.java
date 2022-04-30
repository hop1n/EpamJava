package by.epam.lab;

import java.util.Objects;

import static by.epam.lab.Constants.*;

public class Trial {
    private String account;
    private int mark1;
    private int mark2;

    private static final int MARK_TO_BE_PASSED = 100;

    public Trial() {
    }

    public Trial(String account, int mark1, int mark2) {
        this.account = account;
        this.mark1 = mark1;
        this.mark2 = mark2;
    }

    public static int getMarkToBePassed() {
        return MARK_TO_BE_PASSED;
    }

    public String getAccount() {
        return account;
    }

    public int getMark1() {
        return mark1;
    }

    public int getMark2() {
        return mark2;
    }

    public int getSum(){
        return mark1 + mark2;
    }

    public void clearMarks(){
        mark1 = 0;
        mark2 = 0;
    }

    public boolean isPassed() {
        return mark1 + mark2 >= MARK_TO_BE_PASSED;
    }

    public Trial getClone(){
        return new Trial(account, mark1, mark2);
    }

    @Override
    public String toString() {
        return account + DELIMITER + mark1 + DELIMITER + mark2;
    }
}
