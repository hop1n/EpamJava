package by.gsu.epamlab;

import java.util.Scanner;

public class LowPricePurchase extends Purchase {
    private Byn discount;

    public Byn getDiscount() {
        return discount;
    }

    public void setDiscount(Byn discount) {
        this.discount = discount;
    }

    public LowPricePurchase(Scanner sc) {
        super(sc.next(), new Byn(sc), sc.nextInt());
        this.discount = new Byn(sc.nextInt());
    }

    @Override
    public Byn getCost() {
        return (new Byn(getPrice())).subtract(discount).multiply(getNumber());
    }

    protected String fieldsToString() {
        return super.fieldsToString() + ";" + discount;
    }
}
