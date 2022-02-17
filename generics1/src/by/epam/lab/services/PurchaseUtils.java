package by.epam.lab.services;

import by.epam.lab.Constants;
import by.epam.lab.beans.*;

public class PurchaseUtils {
    private final Purchase purchase;

    public PurchaseUtils(Priceable item, Number number) {
        this.purchase = new Purchase(item, number);
    }

    public PurchaseUtils(Purchase purchase) {
        this.purchase = purchase;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void printPurchase() {
        System.out.println(purchase);
    }

    public void printCost() {
        System.out.println(Constants.COST_OTP + purchase.getCost() + Constants.BYN);
    }

    public void printCostDiff(Purchase p) {
        String otp = Constants.EMPTY_LINE;
        Byn diff = new Byn(0);
        int result = purchase.getCost().compareTo(p.getCost());
        if (result > 0) {
            diff = purchase.getCost().sub(p.getCost());
            otp = Constants.POSITIVE;
        }
        if (result < 0) {
            diff = p.getCost().sub(purchase.getCost());
            otp = Constants.NEGATIVE;
        }
        System.out.println(otp + Constants.DIFF + diff + Constants.BYN);
    }

    public void printIsSameCost(Purchase... purchases) {
        boolean isExists = false;
        for (Purchase purchases1 : purchases) {
            if (purchase.getCost().compareTo(purchases1.getCost()) == 0) {
                isExists = true;
                break;
            }
        }
        System.out.println(isExists);
    }
}
