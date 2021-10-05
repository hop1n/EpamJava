package by.gsu.epamlab;

import java.util.Scanner;

public class LowPricePurchase extends Purchase {
    Byn discount;

    public LowPricePurchase(Scanner sc) {
        super(sc.next(), new Byn(sc), sc.nextInt());
        this.discount = new Byn(sc.nextInt());
    }
    public Byn getCost() {
        return (new Byn(getPrice())).subtract(discount).multiply(getNumber()) ;
    }
}
