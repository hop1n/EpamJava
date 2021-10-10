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
