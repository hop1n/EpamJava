package by.epam.lab;

import static by.epam.lab.Constants.*;

public class StrongTrial extends Trial {

    public StrongTrial(String account, int mark1, int mark2) {
        super(account, mark1, mark2);
    }

    public StrongTrial(StrongTrial st){
        super(st.getAccount(), st.getMark1(), st.getMark2());
    }

    @Override
    public boolean isPassed() {
        return getMark1() / TO_HALF + getMark2() >= getMarkToBePassed();
    }

    @Override
    public StrongTrial getClone(){
        return new StrongTrial(this);
    }
}
