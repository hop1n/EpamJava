package by.epam.lab;

import static by.epam.lab.Constants.*;

public class Trial {
    private String account;
    private int mark1;
    private int mark2;

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

    public String getAccount() {
        return account;
    }

    public int getMark1() {
        return mark1;
    }

    public int getMark2() {
        return mark2;
    }

    @Override
    public String toString() {
        return account + DELIMITER + mark1 + DELIMITER + mark2;
    }
}
