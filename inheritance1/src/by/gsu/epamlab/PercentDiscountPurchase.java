package by.gsu.epamlab;

import java.util.Scanner;

public class PercentDiscountPurchase extends Purchase {
    private double percent;
    private final int limit = 2;

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public int getLimit() {
        return limit;
    }

    public PercentDiscountPurchase(Scanner sc) {
        super(sc.next(), new Byn(sc), sc.nextInt());
        this.percent = sc.nextDouble();
    }

    public Byn getCost() {
        if (getNumber() > limit) {
            return (new Byn(getPrice())).multiply(getNumber()).multiply(1 - percent / 100);
        } else return (new Byn(getPrice())).multiply(getNumber());
    }

    protected String fieldsToString() {
        return super.fieldsToString() + ";" + percent;
    }
}
