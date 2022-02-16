package by.epam.lab.services;

import by.epam.lab.Constants;
import by.epam.lab.beans.*;

public class PurchaseUtils {
    Purchase purchase;

    public Purchase getPurchase() {
        return purchase;
    }

    public void printPurchase() {
        System.out.println(purchase.getItem() + Constants.DELIMITER + purchase.getNumber());
    }

    public void printCost() {
        System.out.println("cost = " + purchase.getCost());
    }

    public void printCostDiff(Purchase p) {
        String otp;
         int diff = purchase.getCost().compareTo(p.getCost());
         if (diff == 0) {
            otp = "";
         } else {
             otp = String.valueOf(diff);
         }
        System.out.println(otp);
    }

    public boolean printIsSameCost(Purchase... purchases) {
        boolean bool = false;
        for (Purchase purchases1: purchases){
            if (this.purchase.getCost() == purchases1.getCost()){
                bool = true;
                break;
            }
        }
        return bool;
    }
}
