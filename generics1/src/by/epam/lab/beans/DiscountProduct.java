package by.epam.lab.beans;

import by.epam.lab.Constants;

public class DiscountProduct extends Product implements Priceable {
    private final Byn discount;

    public DiscountProduct(String name, Byn price, Byn discount) {
        super(name, price);
        this.discount = discount;
    }

    public Byn getDiscount() {
        return discount;
    }

    public Byn getPrice() {
        return super.getPrice().sub(discount);
    }

    protected String fieldsToString() {
        return super.fieldsToString() + Constants.DELIMITER + discount;
    }

}
