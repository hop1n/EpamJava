package by.epam.lab;

import java.util.Scanner;

public class PriceDiscountPurchase extends Purchase {
    private Byn discount;

    public Byn getDiscount() {
        return discount;
    }

    public void setDiscount(Byn discount) {
        this.discount = discount;
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
        return new Byn(getPrice()).sub(discount).mul(getNumber());
    }

    protected String fieldsToString() {
        return String.format("%10s%10s", super.fieldsToString(), discount);
    }
}