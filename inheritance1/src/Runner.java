import by.gsu.epamlab.*;

import java.io.File;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) throws FileNotFoundException {
        final int PURCHASES_NUMBER = 6;
        final String SEPARATOR = File.separator;
        final String PATH = "." + SEPARATOR + "src" + SEPARATOR + "in.txt";
        Purchase[] purchases = new Purchase[PURCHASES_NUMBER];
        Purchase maxCostPurchase = new Purchase();
        boolean isAllPurchasesEqual = true;
        try (Scanner sc = new Scanner(new FileReader(PATH))) {
            sc.useLocale(Locale.ENGLISH);
            for (int i = 0; i < purchases.length; i++) {
                purchases[i] = PurchasesFactory.getPurchaseFromFactory(sc);
                System.out.println(purchases[i]);
                if (purchases[i] != null) {
                    if (purchases[i].getCost().compareTo(maxCostPurchase.getCost()) > 0) {
                        maxCostPurchase = purchases[i];
                    }
                }
                if (i != 0 && !(purchases[i - 1].equals(purchases[i]))) {
                    isAllPurchasesEqual = false;
                }
            }
            if (isAllPurchasesEqual) {
                System.out.println("all purchases are equal.\n");
            } else {
                System.out.println("all purchases are not equal.\n");
            }
        }
    }
}
