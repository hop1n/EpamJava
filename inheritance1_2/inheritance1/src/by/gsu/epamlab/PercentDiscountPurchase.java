package by.gsu.epamlab;

import java.util.Scanner;

public class PercentDiscountPurchase extends Purchase {
    private double percent;
    private final int LIMIT = 2;

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public int getLimit() {
        return LIMIT;
    }

    public PercentDiscountPurchase(Scanner sc) {
        super(sc.next(), new Byn(sc), sc.nextInt());
        this.percent = sc.nextDouble();
    }

    @Override
    public Byn getCost() {
        Byn purchasePrice = super.getCost();
        if (getNumber   () > LIMIT) {
            purchasePrice.multiply(1 - percent / 100);
        }
        return purchasePrice;
    }

    protected String fieldsToString() {
        return super.fieldsToString() + ";" + percent;
    }
}
