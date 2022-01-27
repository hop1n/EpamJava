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
        this(Constants.EMPTY_LINE, new Byn(0), 0);
    }

    public Purchase(String name, Byn price, int number) {
        this.name = name;
        this.price = price;
        this.number = number;
    }


    public Purchase(String[] strings) throws InvalidNameException, InvalidNumberOfArgumentsException {
        if (!(strings.length >= Constants.NUMBER_OF_PURCHASE_INDEXES &&
                strings.length <= Constants.NUMBER_OF_PURCHASE_DISCOUNT_INDEXES)) {
            throw new InvalidNumberOfArgumentsException(Causes.ARGUMENTS_EXCEPTION);
        }
        if (strings[Constants.NAME_INDEX] == null || strings[Constants.NAME_INDEX].equals(Constants.EMPTY_LINE)) {
            throw new InvalidNameException(Causes.WRONG_NAME);
        } else {
            this.name = strings[Constants.NAME_INDEX];
            CheckIfPositive.check(strings[Constants.PRICE_INDEX], Constants.PRICE);
            this.price = new Byn(Integer.parseInt(strings[Constants.PRICE_INDEX]));
            CheckIfPositive.check(strings[Constants.NUMBER_INDEX], Constants.NUMBER);
            this.number = Integer.parseInt(strings[Constants.NUMBER_INDEX]);
        }
        CheckIfPositive.check(getCost(), Constants.TOTAL_COST);
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
    public String getTableOfPurchases(){
        return name + Constants.DELIMITER + price + Constants.DELIMITER + number + Constants.DASH + getCost();
    }

    @Override
    public int compareTo(Purchase purchase) {
        return purchase.getCost().compareTo(getCost());
    }
}

