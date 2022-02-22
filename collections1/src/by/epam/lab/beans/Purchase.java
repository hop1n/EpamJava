package by.epam.lab.beans;


import by.epam.lab.Constants;

import java.util.Scanner;

public class Purchase {
    private String name;
    private Byn price;
    private int number;

    public Purchase() {
    }

    public Purchase(String name, Byn price, int number) {
        this.name = name;
        this.price = price;
        this.number = number;
    }

    public Purchase(String[] fields) {
        this(fields[Constants.NAME_INDEX], new Byn(fields[Constants.PRICE_INDEX]),
                Integer.parseInt(fields[Constants.NUMBER_INDEX]));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byn getPrice() {
        return price;
    }

    public void setPrice(Byn price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Byn getCost() {
        return new Byn(price).mul(number);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + Constants.DELIMITER  + fieldsToString() + Constants.DELIMITER + getCost();
    }

    protected String fieldsToString() {
        return name + Constants.DELIMITER  + price + Constants.DELIMITER  + number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase)) return false;
        Purchase purchase = (Purchase) o;
        return name.equals(purchase.name) && price.equals(purchase.price);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = Constants.HASH_CODE_CONST * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
