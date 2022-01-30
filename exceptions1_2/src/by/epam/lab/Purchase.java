package by.epam.lab;

import by.epam.lab.exceptions.InvalidNameException;
import by.epam.lab.exceptions.InvalidNumberOfArgumentsException;

import java.lang.*;

public class Purchase implements Comparable<Purchase> {
    private final String name;
    private final Byn price;
    private final int number;

    public Purchase(Purchase p) {
        this(p.name, p.price, p.number);
    }

    public Purchase() {
        throw new IllegalStateException("Purchase is empty");
    }

    public Purchase(String name, Byn price, int number) {
        if (name.equals(Constants.EMPTY_LINE)) {
            throw new InvalidNameException(Causes.WRONG_NAME);
        } else {
            this.name = name;
        }
        CheckIfPositive.check(price, Constants.PRICE);
        this.price = price;
        CheckIfPositive.check(number, Constants.NUMBER);
        this.number = number;
    }


    public Purchase(String[] strings) throws InvalidNameException, InvalidNumberOfArgumentsException {
        this(strings[Constants.NAME_INDEX], new Byn(Integer.parseInt(strings[Constants.PRICE_INDEX])),
                Integer.parseInt(strings[Constants.NUMBER_INDEX]));
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase)) return false;
        Purchase purchase = (Purchase) o;
        return name.equals(purchase.name) && price.equals(purchase.price);
    }

    @Override
    public int compareTo(Purchase purchase) {
        return purchase.getCost().compareTo(getCost());
    }
}

