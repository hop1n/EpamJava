package by.gsu.epamlab;

import java.util.Scanner;

public class PurchasesFactory {
    private static enum PurchaseKind {
        LOW_СOST_PURCHASE, PURCHASE, PERCENT_DISCOUNT_PURCHASE;
    }

    public static Purchase getPurchaseFromFactory(Scanner sc) {
        String id = sc.next();
        PurchaseKind kind = PurchaseKind.valueOf(id);
        switch (kind) {
            case PERCENT_DISCOUNT_PURCHASE:
                return new PercentDiscountPurchase(sc);
            case LOW_СOST_PURCHASE:
                return new LowCostPurchase(sc);
            case PURCHASE:
                return new Purchase(sc);
            default:
                throw new IllegalArgumentException();
        }
    }
}

