package by.epam.lab;

import by.epam.lab.exceptions.InvalidNameException;
import by.epam.lab.exceptions.InvalidNumberOfArgumentsException;

import java.lang.*;

public class Purchase {
    private final String name;
    private final Byn price;
    private final int number;

    public Purchase() {
        throw new IllegalStateException("Purchase is empty");
    }

    public Purchase(String name, Byn price, int number) {
        if (name.equals(Constants.EMPTY_LINE)) {
            throw new InvalidNameException(Causes.WRONG_NAME);
        }
        this.name = name;
        this.price = price;
        this.number = number;
    }

    public Purchase(String[] strings) {
        this(getValidPurchase(strings));
    }

    private static Purchase getValidPurchase(String[] fields) {
        if(fields.length != Constants.NUMBER_OF_PURCHASE_INDEXES) {
            throw new ArrayIndexOutOfBoundsException("wrong args number");
        }
        return new Purchase(fields[Constants.NAME_INDEX], new Byn(Integer.parseInt(fields[Constants.PRICE_INDEX])), Integer.parseInt(fields[Constants.NUMBER_INDEX]));
    }

    public Purchase(Purchase p) {
        this(p.name, p.price, p.number);
    }

    public Purchase getPurchaseClone() {
        return new Purchase(this.name, new Byn(price), number);
    }

    public String getName() {
        return name;
    }

    public Byn getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

    public Byn getCost() {
        return price.mul(number);
    }

    @Override
    public String toString() {
        return fieldsToString() + Constants.DELIMITER + getCost();
    }

    protected String fieldsToString() {
        return name + Constants.DELIMITER + price + Constants.DELIMITER + number;
    }
}

