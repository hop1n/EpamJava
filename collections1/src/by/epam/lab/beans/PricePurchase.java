package by.epam.lab.beans;

import by.epam.lab.Constants;

import java.util.Scanner;

public class PricePurchase extends Purchase{
    private Byn discount;

    public PricePurchase(){
        this.discount = new Byn(0);
    }

    public PricePurchase(String[] fields) {
        super(fields);
        this.discount = new Byn(fields[Constants.DISCOUNT_INDEX]);
    }

    public Byn getDiscount() {
        return discount;
    }

    public void setDiscount(Byn discount) {
        this.discount = discount;
    }

    @Override
    public Byn getCost() {
        return new Byn(getPrice()).sub(discount).mul(getNumber());
    }

    protected String fieldsToString() {
        return super.fieldsToString() + Constants.DELIMITER  + discount;
    }
}

