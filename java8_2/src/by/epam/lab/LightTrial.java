package by.epam.lab;

public class LightTrial extends Trial {
    private static final int MARK1_TO_BE_PASSED = 40;
    private static final int MARK2_TO_BE_PASSED = 50;

    public LightTrial(LightTrial lt){
       super(lt.getAccount(), lt.getMark1(), lt.getMark2());
    }

    public LightTrial(String account, int mark1, int mark2) {
        super(account, mark1, mark2);
    }

    @Override
    public boolean isPassed() {
        return (getMark1() >= MARK1_TO_BE_PASSED) && (getMark2() >= MARK2_TO_BE_PASSED);
    }

    @Override
    public LightTrial getClone(){
        return new LightTrial(this);
    }
}
