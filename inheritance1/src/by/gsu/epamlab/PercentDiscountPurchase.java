package by.gsu.epamlab;

import java.util.Scanner;

public class PercentDiscountPurchase extends Purchase {
    double percent;
    final int a = 2;

    public PercentDiscountPurchase(Scanner sc) {
        super(sc.next(), new Byn(sc), sc.nextInt());
        this.percent = sc.nextDouble();
    }

    public Byn getCost() {
        if (getNumber() < a) {
            return (new Byn(getPrice())).multiply(getNumber()).multiply(1 - percent / 100);
        } else return getPrice();
    }
}
