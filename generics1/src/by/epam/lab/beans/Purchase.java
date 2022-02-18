package by.epam.lab.beans;

import by.epam.lab.Constants;
import by.epam.lab.services.RoundMethod;

public class Purchase<T extends Priceable> {
    private final T item;
    private final Number number;

    public Purchase() {
        this(null, 0);
    }

    public Purchase(T item, Number number) {
        this.item = item;
        this.number = number;
    }

    public T getItem() {
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
