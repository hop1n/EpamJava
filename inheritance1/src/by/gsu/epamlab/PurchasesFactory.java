package by.gsu.epamlab;

import java.util.Scanner;

public class PurchasesFactory {
    private static enum PurchaseKind {
        LowCostPurchase, Purchase, PresentDiscountPurchase;
    }
    public static Purchase getPurchaseFromFactory(Scanner sc) {
        String id = sc.next();
        PurchaseKind kind = PurchaseKind.valueOf(id);
        switch(kind) {
            case PresentDiscountPurchase :
                return new PercentDiscountPurchase(sc);
            case LowCostPurchase :
                return new LowCostPurchase(sc);
            case Purchase :
			    return new Purchase(sc);
            default :
                throw new IllegalArgumentException();
        }
    }
}

