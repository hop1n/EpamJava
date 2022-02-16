package by.epam.lab.services;

import by.epam.lab.Constants;
import by.epam.lab.beans.*;

public class PurchaseUtils {
    private final Purchase purchase;

    public PurchaseUtils(Object item, Number number) {
        this.purchase = new Purchase(item, number);
    }

    public PurchaseUtils(Purchase purchase){
        this.purchase = purchase;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void printPurchase() {
        System.out.println(purchase.getItem() + Constants.DELIMITER + purchase.getNumber());
    }

    public void printCost() {
        System.out.println(Constants.COST_OTP + purchase.getCost() + Constants.BYN);
    }

    public void printCostDiff(Purchase p) {
        String otp;
         int diff = purchase.getCost().compareTo(p.getCost());
         if (diff == 0) {
            otp = Constants.EMPTY_LINE;
         } else {
             otp = String.valueOf(diff);
         }
        System.out.println(otp + Constants.DIFF + purchase.getCost() + Constants.BYN);
    }

    public boolean printIsSameCost(Purchase... purchases) {
        boolean isExsists = false;
        for (Purchase purchases1: purchases){
            if (this.purchase.getCost().compareTo(purchases1.getCost()) == 0){
                isExsists = true;
                break;
            }
        }
        return isExsists;
    }
}
