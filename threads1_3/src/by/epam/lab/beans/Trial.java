package by.epam.lab.beans;

import static by.epam.lab.service.Constants.*;

public class Trial{
    private String account;
    private int mark1;
    private int mark2;

    public static final int MARK_TO_BE_PASSED = 100;

    public Trial() {
    }

    public Trial(String account, int mark1, int mark2) {
        this.account = account;
        this.mark1 = mark1;
        this.mark2 = mark2;
    }

    public Trial(String[] strings){
        this(strings[ACCOUNT_INDEX], Integer.parseInt(strings[MARK1_INDEX]), Integer.parseInt(strings[MARK2_INDEX]));
    }

    public boolean isPassed(){
        return mark1 + mark2 > MARK_TO_BE_PASSED;
    }

    @Override
    public String toString() {
        return account + DELIMITER + mark1 + DELIMITER + mark2 + DELIMITER + isPassed();
    }
}
