package by.gsu.epamlab;

import java.util.Scanner;

public class PurchasesFactory {
    private static enum PurchaseKind {
        PURCHASE {
            Purchase getPurchase(Scanner sc) {
                return new Purchase(sc);
            }
        },
        LOW_PRICE_PURCHASE {
            Purchase getPurchase(Scanner sc) {
                return new LowPricePurchase(sc);
            }
        },
        PERCENT_DISCOUNT_PURCHASE {
            Purchase getPurchase(Scanner sc) {
                return new PercentDiscountPurchase(sc);
            }
        };

        abstract Purchase getPurchase(Scanner sc);
    }

    public static Purchase getPurchaseFromFactory(Scanner sc) {
        String id = sc.next();
        PurchaseKind kind = PurchaseKind.valueOf(id);
        return kind.getPurchase(sc);
    }
}

