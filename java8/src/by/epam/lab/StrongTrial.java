package by.epam.lab;

public class StrongTrial extends Trial {

    public StrongTrial(String account, int mark1, int mark2) {
        super(account, mark1, mark2);
    }

    @Override
    public boolean isPassed() {
        return getMark1() / 2 + getMark2() >= getMarkToBePassed();
    }
}
