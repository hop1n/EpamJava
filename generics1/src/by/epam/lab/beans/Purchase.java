package by.epam.lab.beans;

import by.epam.lab.Constants;
import by.epam.lab.services.RoundMethod;

public class Purchase {
    private final Priceable item;
    private final Number number;

    public Purchase() {
        this(null, 0);
    }

    public Purchase(Priceable item, Number number) {
        this.item = item;
        this.number = number;
    }

    public Object getItem() {
        return item;
    }

    public Number getNumber() {
        return number;
    }

    public Byn getCost() {
        return item.getPrice().mul(number.doubleValue(), RoundMethod.ROUND, 0);
    }

    @Override
    public String toString() {
        return item + Constants.DELIMITER + number + Constants.DELIMITER + getCost();
    }
}
