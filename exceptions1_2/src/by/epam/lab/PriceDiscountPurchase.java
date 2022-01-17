package by.epam.lab;

import java.util.Scanner;

public final class PriceDiscountPurchase extends Purchase {
    private final Byn discount;

    public Byn getDiscount() {
        return discount;
    }

    public PriceDiscountPurchase(PriceDiscountPurchase p) {
        super(p.getName(), p.getPrice(), p.getNumber());
        discount = p.discount;
    }
    public PriceDiscountPurchase(String name, String price, String number, String discount) {
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