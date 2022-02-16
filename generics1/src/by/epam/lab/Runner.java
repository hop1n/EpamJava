package by.epam.lab;

import by.epam.lab.beans.*;
import by.epam.lab.services.PurchaseUtils;

public class Runner {
    public static void main(String[] args){
        Product milk = new Product("milk", new Byn(170));
        Purchase p1 = new Purchase(milk, 20);
        PurchaseUtils pu1 = new PurchaseUtils(p1);
        pu1.printCost();
        Product sugar = new Product("sugar", new Byn(300));
        Purchase p2 = new Purchase(sugar, 12.5);
        PurchaseUtils pu2 = new PurchaseUtils(p2);
        pu1.printCostDiff(p2);
        DiscountProduct sugar2 = new DiscountProduct("sugar", new Byn(280), new Byn(10));
        Purchase p3 = new Purchase(sugar2, 60);
        PurchaseUtils pu3 = new PurchaseUtils(p3);
        Service gym = new Service("gym", new Byn(7560), 5);
        PurchaseUtils pu4 = new PurchaseUtils(gym, 2.25);
        System.out.println(pu4.getPurchase());
        pu4.printCost();
        pu2.printIsSameCost(p1, p3, pu4.getPurchase());
    }
}
