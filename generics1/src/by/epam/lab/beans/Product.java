package by.epam.lab.beans;

import by.epam.lab.Constants;

public class Product implements Priceable {
    private final String name;
    private final Byn price;

    public Product(String name, Byn price) {
        this.name = name;
        this.price = price;
    }

    public Product() {
        this(Constants.EMPTY_LINE, new Byn());
    }

    public String getName() {
        return name;
    }

    public Byn getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return fieldsToString() + Constants.DELIMITER + getPrice();
    }

    protected String fieldsToString() {
        return name + Constants.DELIMITER + price;
    }
}
