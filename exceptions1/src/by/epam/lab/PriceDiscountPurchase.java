package by.epam.lab;

import java.util.Scanner;

public final class PriceDiscountPurchase extends Purchase {
    private Byn discount;

    public Byn getDiscount() {
        return discount;
    }

    public PriceDiscountPurchase(Scanner sc) {
        super(sc);
        this.discount = new Byn(sc);
    }

    public PriceDiscountPurchase(String name, String price, String number,String discount) {
        super(name, price, number);
        this.discount = new Byn(Integer.parseInt(discount));
    }

    @Override
    public Byn getCost() {
        return getPrice().sub(discount).mul(getNumber());
    }

    protected String fieldsToString() {
        return super.fieldsToString() + discount;
    }
}