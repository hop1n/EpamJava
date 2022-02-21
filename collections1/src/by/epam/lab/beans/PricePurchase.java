package by.epam.lab.beans;

import java.util.Scanner;

public class PricePurchase extends Purchase{
    private Byn discount;

    public Byn getDiscount() {
        return discount;
    }

    public void setDiscount(Byn discount) {
        this.discount = discount;
    }

    public PricePurchase(Scanner sc) {
        super(sc);
        this.discount = new Byn(sc);
    }

    @Override
    public Byn getCost() {
        return new Byn(getPrice()).sub(discount).mul(getNumber());
    }

    protected String fieldsToString() {
        return super.fieldsToString() + ";" + discount;
    }
}

