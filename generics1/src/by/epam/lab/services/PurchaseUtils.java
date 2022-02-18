package by.epam.lab.services;

import by.epam.lab.Constants;
import by.epam.lab.beans.*;

import java.nio.channels.Pipe;

public class PurchaseUtils<T extends Priceable> {
    private final Purchase<T> purchase;

    public PurchaseUtils(T item, Number number) {
        this.purchase = new Purchase<T>(item, number);
    }

    public PurchaseUtils(Purchase<T> purchase) {
        this.purchase = purchase;
    }

    public Purchase<T> getPurchase() {
        return purchase;
    }

    public void printPurchase() {
        System.out.println(purchase);
    }

    public void printCost() {
        System.out.println(Constants.COST_OTP + purchase.getCost() + Constants.BYN);
    }

    public void printCostDiff(Purchase<?> p) {
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

    public void printIsSameCost(Purchase<? extends Priceable>... purchases) {
        boolean isExists = false;
        for (Purchase<?> purchases1 : purchases) {
            if (purchase.getCost().compareTo(purchases1.getCost()) == 0) {
                isExists = true;
                break;
            }
        }
        System.out.println(isExists);
    }
}
