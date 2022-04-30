package by.epam.lab;

import static by.epam.lab.Constants.*;

public class ExtraTrial extends Trial {

    private int mark3;
    private static final int MARK3_TO_BE_PASSED = 55;

    public ExtraTrial(String account, int mark1, int mark2, int mark3) {
        super(account, mark1, mark2);
        this.mark3 = mark3;
    }

    public int getMark3() {
        return mark3;
    }

    @Override
    public Trial getClone() {
        return new ExtraTrial(this.getAccount(), this.getMark1(), this.getMark2(), mark3);
    }

    @Override
    public void clearMarks() {
        super.clearMarks();
        mark3 = 0;
    }

    @Override
    public boolean isPassed() {
        return super.isPassed() && mark3 >= MARK3_TO_BE_PASSED;
    }

    @Override
    public String toString() {
        return super.toString() + DELIMITER + mark3;
    }
}
