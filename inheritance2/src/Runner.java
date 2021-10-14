import by.gsu.epamlab.*;

import java.io.FileNotFoundException;
import java.util.Arrays;


public class Runner {
    private static void printPurchases(AbstractPurchase[] purchases) {
        for (AbstractPurchase purchase : purchases) {
            System.out.println(purchase);
        }
    }

    public static void main(String[] args) {
        final Product TOWEL = new Product("towel", new Byn(500));
        AbstractPurchase[] purchases = {
                new LowPricePurchase(TOWEL, 20, new Byn(500)),
                new LowPricePurchase(TOWEL, 10, new Byn(300)),
                new PercentDiscountPurchase(TOWEL, 5, 15.5),
                new PercentDiscountPurchase(TOWEL, 11, 10.0),
                new TransportPurchase(TOWEL, 12, new Byn(50)),
                new TransportPurchase(TOWEL, 12, new Byn(100))
        };

        printPurchases(purchases);

        Arrays.sort(purchases);
        printPurchases(purchases);

        System.out.println(purchases[purchases.length - 1].getCost());

        int index = Arrays.binarySearch(purchases, new LowPricePurchase(TOWEL, 1, new Byn(0)));
        if (index >= 0) {
            System.out.println("Required purchase is: " + purchases[index]);
        } else {
            System.out.println("Required purchase is not found");
        }


    }
}
