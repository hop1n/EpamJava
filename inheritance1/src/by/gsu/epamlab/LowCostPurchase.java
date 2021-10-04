package by.gsu.epamlab;

import java.util.Scanner;

public class LowCostPurchase extends Purchase {
    Byn discount;

    public LowCostPurchase (Scanner sc){
        super(sc.next(), new Byn(sc), sc.nextInt());
        this.discount = new Byn(sc.nextInt());
    }
}
