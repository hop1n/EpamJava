package by.gsu.epamlab;

import java.util.Scanner;

public class PresentDiscountPurchase extends Purchase {
    double percent;
    final int a =1;

    public PresentDiscountPurchase (Scanner sc){
        super(sc.next(), new Byn(sc), sc.nextInt());
        this.percent = sc.nextDouble();
    }
}
